package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 검색하면 게시글 리스트를 반환")
    @Test
    void given_whenSearchParameter_thenReturnList() {

        Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "search keyword");

        assertThat(articles).isNotNull();

    }

    @DisplayName("게시글 검색하면 게시글 반환")
    @Test
    void given_whenSearchParameter_thenReturn() {

        ArticleDto articles = articleService.searchArticle(1L);

        assertThat(articles).isNotNull();

    }

}