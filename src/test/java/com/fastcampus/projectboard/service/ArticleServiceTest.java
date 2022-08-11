package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@DisplayName("비즈니스 로직 - 게시글")
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

    @DisplayName("게시글 정보를 입력하면 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {

        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content", "#hash");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        articleService.saveArticle(dto);

        BDDMockito.then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면 수정한다")
    @Test
    void givenArticleInfo_whenModifyArticle_thenModifyArticle() {

        ArticleUpdateDto dto = ArticleUpdateDto.of("title", "content", "#hash");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        articleService.updateArticle(1L, dto);

        BDDMockito.then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID 정보를 입력하면 삭제한다")
    @Test
    void givenArticleInfo_whenDeleteArticle_thenDeleteArticle() {

        willDoNothing().given(articleRepository).delete(any(Article.class));

        articleService.deleteArticle(1L);

        BDDMockito.then(articleRepository).should().delete(any(Article.class));

    }

}