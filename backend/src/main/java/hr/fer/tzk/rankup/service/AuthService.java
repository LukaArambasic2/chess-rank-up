package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.MemberLoginDto;
import hr.fer.tzk.rankup.dto.MemberRegisterDto;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.security.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Optional;

@Service
public class AuthService {

    private final PasswordHasher passwordHasher;
    private final MemberService memberService;

    @Autowired
    public AuthService(@Qualifier("argon2idHasher") PasswordHasher passwordHasher, MemberService memberService) {
        this.passwordHasher = passwordHasher;
        this.memberService = memberService;
    }

    private boolean isValidPassword(String password) {
        final String PASSWORD_REGEX = "^[A-Za-z0-9!?._-]+$";
        final int MIN_PASSWORD_LENGTH = 8;
        final int MAX_PASSWORD_LENGTH = 30;
        return password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH && password.matches(PASSWORD_REGEX);
    }

    private String checkForAllAttrsLogin(MemberLoginDto memberLogin) {
        if (memberLogin.getEmail() == null || memberLogin.getEmail().isBlank() ||
                memberLogin.getPassword() == null || memberLogin.getPassword().isBlank() ||
                !isValidPassword(memberLogin.getPassword())) {
            return "Invalid email or password";
        }
        return null;
    }

    private String checkForAllAttrsRegister(MemberRegisterDto memberRegister) {
        if (memberRegister.getFirstName() == null || memberRegister.getFirstName().isBlank()) {
            return "Missing first name";
        }

        if (memberRegister.getFirstName().length() > 30) {
            return "First name must be 30 characters or less";
        }

        if (memberRegister.getLastName() == null || memberRegister.getLastName().isBlank()) {
            return "Missing last name";
        }

        if (memberRegister.getLastName().length() > 30) {
            return "Last name must be 30 characters or less";
        }

        if (memberRegister.getJmbag() == null || memberRegister.getJmbag().isBlank()) {
            return "Missing JMBAG";
        }

        if (memberRegister.getEmail() == null || memberRegister.getEmail().isBlank()) {
            return "Missing email";
        }

        if (memberRegister.getPassword() == null || memberRegister.getPassword().isBlank()) {
            return "Missing password";
        }

        if (memberRegister.getRepeatPassword() == null || memberRegister.getRepeatPassword().isBlank()) {
            return "Missing repeat password";
        }

        if (!memberRegister.getPassword().equals(memberRegister.getRepeatPassword())) {
            return "Passwords do not match";
        }

        if (!isValidPassword(memberRegister.getPassword())) {
            return "Password can contain only: uppercase and lowercase letters, numbers and special characters '.', '?', '!', '_' and '-'";
        }
        return null;
    }

    private String checkForConflictRegister(MemberRegisterDto memberRegister) {
        Optional<Member> memberByEmailOpt = memberService.findMemberByEmail(memberRegister.getEmail());
        if (memberByEmailOpt.isPresent()) {
            return "Email already in use";
        }
        Optional<Member> memberByJmbagOpt = memberService.findMemberByJmbag(memberRegister.getJmbag());
        if (memberByJmbagOpt.isPresent()) {
            return "JMBAG already in use";
        }
        return null;
    }

    public AbstractMap.SimpleEntry<HttpStatus, String> login(MemberLoginDto memberLogin) {
        String checkAllAttrsRes = checkForAllAttrsLogin(memberLogin);
        if (checkAllAttrsRes != null) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, checkAllAttrsRes);
        }

        Optional<Member> memberOpt = memberService.findMemberByEmail(memberLogin.getEmail());
        if (memberOpt.isEmpty()) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, "Invalid email or password");
        }

        Member member = memberOpt.get();
        String storedHash = member.getPasswordHash();
        String salt = member.getSalt();

        if (!passwordHasher.checkPassword(memberLogin.getPassword(), salt, storedHash)) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, "Invalid email or password");
        }

        if (!member.isVerified()) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, "Invalid email or password");
        }

        return new AbstractMap.SimpleEntry<>(HttpStatus.OK, null);
    }

    public AbstractMap.SimpleEntry<HttpStatus, String> register(MemberRegisterDto memberRegister) {
        String checkAllAttrsRes = checkForAllAttrsRegister(memberRegister);
        if (checkAllAttrsRes != null) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, checkAllAttrsRes);
        }

        String checkForConflictRes = checkForConflictRegister(memberRegister);
        if (checkForConflictRes != null) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.CONFLICT, checkForConflictRes);
        }

        String salt = passwordHasher.generateSalt();
        String passwordHash = passwordHasher.hashPassword(memberRegister.getPassword(), salt);

        Member newMember;
        try {
            newMember = new Member(memberRegister.getFirstName(), memberRegister.getLastName(), memberRegister.getJmbag(), memberRegister.getEmail(), passwordHash, salt);
            memberService.createMember(newMember);
        } catch (IllegalArgumentException exception) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        return new AbstractMap.SimpleEntry<>(HttpStatus.OK, null);
    }
}
