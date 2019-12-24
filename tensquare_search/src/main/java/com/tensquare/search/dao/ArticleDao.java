package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yeming
 * @date 2019/7/28 17:11
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
