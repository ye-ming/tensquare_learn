package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * article数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(value = "UPDATE tb_article set state = '1' where id = ?1",nativeQuery = true)
    public void updateState(String articleId);

    @Modifying
    @Query(value = "UPDATE tb_article set thumbup = thumbup+1 where id = ?1",nativeQuery = true)
    public void addthumbup(String articleId);
}
