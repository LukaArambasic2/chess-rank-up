package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Semester;
import hr.fer.tzk.rankup.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<Semester> findAllSemesters() {
        return semesterRepository.findAll();
    }

    public Optional<Semester> findSemesterById(Long id) {
        return semesterRepository.findById(id);
    }

    public Optional<Semester> findSemesterByName(String name) {
        return semesterRepository.findByName(name);
    }

    public Optional<Semester> findCurrentSemester() {
        return semesterRepository.findSemesterByDate(LocalDate.now());
    }

    public Optional<Semester> findSemesterByDate(LocalDate date) {
        return semesterRepository.findSemesterByDate(date);
    }

    public Optional<Semester> findLatestSemester() {
        List<Semester> semesters = semesterRepository.findAllSemestersOrderedByDateToDesc();
        return semesters.isEmpty() ? Optional.empty() : Optional.of(semesters.get(0));
    }

    public List<Semester> findLatestNSemesters(int n) {
        List<Semester> semesters = semesterRepository.findAllSemestersOrderedByDateToDesc();
        return semesters.stream()
                .limit(n)
                .toList();
    }
}
