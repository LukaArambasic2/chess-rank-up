package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.NewsDto;
import hr.fer.tzk.rankup.form.NewsForm;
import hr.fer.tzk.rankup.mapper.NewsMapper;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.News;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.MemberRepository;
import hr.fer.tzk.rankup.repository.NewsRepository;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

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
        return newsRepository.findAllBySection_IdOrderByDateCreatedDesc(idSection).stream().map(NewsMapper::toDto).toList();
    }

    public NewsDto findById(Long id) {
        return newsRepository.findById(id).map(NewsMapper::toDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća obavijest s ID: " + id)
        );
    }

    public News createNews(NewsForm newsForm) {
        Section section = sectionRepository.findById(newsForm.getIdSection()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća sekcija s ID: " + newsForm.getIdSection())
        );
        Member member = memberRepository.findById(newsForm.getIdMember()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća sekcija s ID: " + newsForm.getIdMember())
        );

        News news = NewsMapper.fromForm(newsForm, section, member);
        news = newsRepository.save(news);

        return news;
    }

    public News updateNews(Long id, NewsForm newsForm) {
        newsRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća obavijest s ID: " + id)
        );
        Section section = sectionRepository.findById(newsForm.getIdSection()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća sekcija s ID: " + newsForm.getIdSection())
        );
        Member member = memberRepository.findById(newsForm.getIdMember()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća sekcija s ID: " + newsForm.getIdMember())
        );
        News news = NewsMapper.fromForm(newsForm, section, member);
        news.setId(id);
        news.setDateEdited(LocalDate.now());
        news = newsRepository.save(news);

        return news;
    }

    public void deleteNews(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepostojeća obavijest s ID: " + id);
        }
    }
}
