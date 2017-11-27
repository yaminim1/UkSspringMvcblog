package com.uk.dao;

import java.util.List;

import com.uk.model.Article;

public interface ArticleDao {

	public Article create(String userName, Article article);

	public List<Article> findAll();

	public Article findById(Integer id);

	public void removeArticle(Integer id);

	public List<Article> findByTitle(String title);

	public List<Article> findAllByVerify();

	public List<Article> findAllByCreatedBy(String createdBy);

	public Article updateVerify(Integer id);

	public Article updateDelFlg(Integer id);

	public Article editArticle(String userName, Article article);

}
