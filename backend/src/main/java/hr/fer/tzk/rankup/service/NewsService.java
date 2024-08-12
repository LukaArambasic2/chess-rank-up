package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.NewsDto;
import hr.fer.tzk.rankup.exceptions.NonExistingEntityException;
import hr.fer.tzk.rankup.form.NewsForm;
import hr.fer.tzk.rankup.mapper.NewsMapper;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.News;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.MemberRepository;
import hr.fer.tzk.rankup.repository.NewsRepository;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final SectionRepository sectionRepository;
    private final MemberRepository memberRepository;

    public NewsService(NewsRepository newsRepository, SectionRepository sectionRepository, MemberRepository memberRepository) {
        this.newsRepository = newsRepository;
        this.sectionRepository = sectionRepository;
        this.memberRepository = memberRepository;
    }

    public List<NewsDto> findAll() {
        return newsRepository.findAllByOrderByDateCreatedAsc().stream().map(NewsMapper::toDto).toList();
    }

    public List<NewsDto> findAllByAuthor(Long idAuthor) {
        return newsRepository.findAllByAuthor_IdOrderByDateCreated(idAuthor).stream().map(NewsMapper::toDto).toList();
    }

    public List<NewsDto> findAllBySection(Long idSection) {
        return newsRepository.findAllBySection_IdOrderByDateCreated(idSection).stream().map(NewsMapper::toDto).toList();
    }

    public NewsDto findById(Long id) {
        return newsRepository.findById(id).map(NewsMapper::toDto).orElseThrow(
                () -> new NonExistingEntityException("Nepostojeća obavijest s ID: " + id)
        );
    }

    public News createNews(NewsForm newsForm) {
        Optional<Section> section = sectionRepository.findById(newsForm.getIdSection());
        Optional<Member> member = memberRepository.findById(newsForm.getIdMember());
        String checkData = checkSectionAndMember(section, member);
        if (checkData.length() > 1) {
            throw new NonExistingEntityException(checkData);
        }

        News news = NewsMapper.fromForm(newsForm, section.get(), member.get());
        news = newsRepository.save(news);

        return news;
    }

    private String checkSectionAndMember(Optional<Section> section, Optional<Member> member) {
        if (section.isEmpty()) {
            return "Nepostojeća sekcija!";
        }
        if (member.isEmpty()) {
            return "Nepostojeći korisnik";
        }
        return "";
    }
}
