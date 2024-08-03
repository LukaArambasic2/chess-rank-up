package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Rank;
import hr.fer.tzk.rankup.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankService {
    private final RankRepository rankRepository;

    @Autowired
    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    public List<Rank> getRanks() {
        return rankRepository.findAll();
    }

    public Optional<Rank> findRankByName(String name) {
        return rankRepository.findByName(name);
    }
}
