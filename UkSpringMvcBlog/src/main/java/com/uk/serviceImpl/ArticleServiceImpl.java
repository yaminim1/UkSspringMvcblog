package com.uk.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uk.dao.ArticleDao;
import com.uk.model.Article;
import com.uk.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao articleDao;

	public Article create(String userName, Article article) {

		return articleDao.create(userName, article);

	}

	public List<Article> findAll() {

		return articleDao.findAll();
	}

	public Article findById(Integer id) {

		return articleDao.findById(id);
	}

	public void removeArticle(Integer id) {
		articleDao.removeArticle(id);

	}

	public List<Article> findByTitle(String title) {
		return articleDao.findByTitle(title);
	}

	@Override
	public List<Article> findAllByVerify() {
		return articleDao.findAllByVerify();
	}

	@Override
	public List<Article> findAllByCreatedBy(String createdBy) {
		return articleDao.findAllByCreatedBy(createdBy);
	}

	@Override
	public Article updateVerify(Integer id) {

		return articleDao.updateVerify(id);
	}

	@Override
	public Article updateDelFlg(Integer id) {

		return articleDao.updateDelFlg(id);
	}

	@Override
	public Article editArticle(String userName, Article article) {

		return articleDao.editArticle(userName, article);
	}

}
