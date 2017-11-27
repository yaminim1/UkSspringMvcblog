package com.uk.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uk.dao.ArticleDao;
import com.uk.model.Article;
import com.uk.model.Users;

@Repository
@Transactional
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	SessionFactory session;

	@SuppressWarnings("unchecked")
	public Article create(String userName, Article article) {

		List<Users> users = new ArrayList<Users>();

		article.setCreatedBy(userName);
		article.setCreatedDate(new Date());
		article.setReplyCount(0);
		article.setVerifyFlag(false);
		article.setMarkAsDeleted(false);
		users = session.getCurrentSession().createQuery("from Users where name=:username")
				.setParameter("username", userName).list();
		article.setUser(users.get(0));

		session.getCurrentSession().save(article);
		return article;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Article editArticle(String userName, Article article) {

		List<Users> users = new ArrayList<Users>();

		String title = article.getTitle();
		String description = article.getDescription();
		article = findById(article.getId());

		article.setTitle(title);
		article.setDescription(description);
		article.setLastUpdatedBy(userName);
		article.setLastUpdatedDate(new Date());
		users = session.getCurrentSession().createQuery("from Users where name=:username")
				.setParameter("username", userName).list();
		article.setUser(users.get(0));

		session.getCurrentSession().update(article);
		return article;
	}

	@SuppressWarnings("unchecked")
	public List<Article> findAll() {

		return session.getCurrentSession().createQuery("from Article a where a.markAsDeleted=false").list();

	}

	public Article findById(Integer id) {

		return (Article) session.getCurrentSession().get(Article.class, id);
	}

	public void removeArticle(Integer id) {

		Article article = (Article) session.getCurrentSession().load(Article.class, id);
		if (null != article) {
			this.session.getCurrentSession().delete(article);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Article> findByTitle(String title) {

		return session.getCurrentSession()
				.createQuery(
						"from Article a where a.title Like :title or a.description like :title and a.markAsDeleted=false")
				.setParameter("title", "%" + title + "%").list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findAllByVerify() {
		return session.getCurrentSession()
				.createQuery("from Article a where a.verifyFlag=true and a.markAsDeleted=false").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findAllByCreatedBy(String createdBy) {
		return session.getCurrentSession()
				.createQuery("from Article a where a.createdBy =:createdBy and a.markAsDeleted=false")
				.setParameter("createdBy", createdBy).list();
	}

	@Override
	public Article updateVerify(Integer id) {
		Article article = findById(id);
		article.setVerifyFlag(true);
		session.getCurrentSession().save(article);
		return article;
	}

	@Override
	public Article updateDelFlg(Integer id) {
		Article article = findById(id);
		article.setMarkAsDeleted(true);
		session.getCurrentSession().save(article);
		return article;
	}

}
