package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.BasicMemberDto;
import hr.fer.tzk.rankup.dto.NewsDto;
import hr.fer.tzk.rankup.model.News;

public class NewsMapper {
    public static NewsDto toDto(News news) {
        if (news == null) {
            return null;
        }
        NewsDto newsDto = new NewsDto();
        BasicMemberDto basicMemberDto = MemberMapper.toBasicDto(news.getAuthor());
        newsDto.setAuthor(basicMemberDto);
        newsDto.setContent(news.getContent());
        newsDto.setTitle(news.getTitle());
        newsDto.setImages(news.getImages());
        newsDto.setDateCreated(news.getDateCreated());
        newsDto.setDateEdited(news.getDateEdited());

        return newsDto;
    }
}
