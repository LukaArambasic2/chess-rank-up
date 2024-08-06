package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.NewsDto;
import hr.fer.tzk.rankup.mapper.NewsMapper;
import hr.fer.tzk.rankup.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
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
        return newsRepository.findById(id).map(NewsMapper::toDto).orElse(null);
    }
}
