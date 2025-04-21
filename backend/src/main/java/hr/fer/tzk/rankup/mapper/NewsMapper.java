package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.BasicMemberDto;
import hr.fer.tzk.rankup.dto.NewsDto;
import hr.fer.tzk.rankup.form.NewsForm;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.News;
import hr.fer.tzk.rankup.model.Section;

import java.time.LocalDate;

public class NewsMapper {
    public static NewsDto toDto(News news) {
        if (news == null) {
            return null;
        }
        NewsDto newsDto = new NewsDto();
        BasicMemberDto basicMemberDto = MemberMapper.toBasicDto(news.getAuthor());
        newsDto.setId(news.getId());
        newsDto.setAuthor(basicMemberDto);
        newsDto.setContent(news.getContent());
        newsDto.setTitle(news.getTitle());
        newsDto.setImages(news.getImages());
        newsDto.setDateCreated(news.getDateCreated());
        newsDto.setDateEdited(news.getDateEdited());

        return newsDto;
    }

    public static News fromForm(NewsForm newsForm, Section section, Member member) {
        News news = new News();
        news.setTitle(newsForm.getTitle());
        news.setContent(newsForm.getContent());
        news.setSection(section);
        news.setAuthor(member);
        news.setImages(newsForm.getImages());
        news.setDateCreated(LocalDate.now());
        news.setDateEdited(null);

        return news;
    }
}
