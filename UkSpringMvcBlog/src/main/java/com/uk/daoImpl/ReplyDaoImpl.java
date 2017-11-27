package com.uk.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uk.dao.ReplyDao;
import com.uk.model.Article;
import com.uk.model.Reply;
import com.uk.model.Users;
import com.uk.service.ArticleService;

@Repository
@Transactional
public class ReplyDaoImpl implements ReplyDao {

	@Autowired
	SessionFactory session;

	@Autowired
	ArticleService articleService;

	@SuppressWarnings("unchecked")
	public Reply createReply(Integer articleId, String userName, Reply reply) {

		List<Users> users = new ArrayList<Users>();
		Article article = articleService.findById(articleId);
		reply.setCreatedBy(userName);
		reply.setCreatedDate(new Date());
		reply.setMarkAsDeleted(false);
		reply.setVerifyFlag(false);
		article.setreplyCount(article.getReplyCount() + 1);
		reply.setArticle(article);
		users = session.getCurrentSession().createQuery("from Users where name=:username")
				.setParameter("username", userName).list();
		reply.setUser(users.get(0));
		session.getCurrentSession().save(reply);
		return reply;
	}

	public Reply updateVerifyReply(Integer id, Integer replyId) {
		Reply reply = (Reply) session.getCurrentSession().get(Reply.class, replyId);
		reply.setVerifyFlag(true);
		session.getCurrentSession().save(reply);
		return reply;
	}

	public Reply updateDelFlgReply(Integer id, Integer replyId) {
		Reply reply = (Reply) session.getCurrentSession().get(Reply.class, replyId);
		reply.setMarkAsDeleted(true);
		Article article = (Article) session.getCurrentSession().get(Article.class, id);
		article.setreplyCount(article.getreplyCount() - 1);
		session.getCurrentSession().save(reply);
		return reply;
	}

	@SuppressWarnings("unchecked")
	public List<Reply> getComments(int id) {
		Article article = (Article) session.getCurrentSession().get(Article.class, id);
		System.out.println("get comments" + article);
		List<Reply> replies = new ArrayList<Reply>();
		replies = session.getCurrentSession()
				.createQuery("from Reply r where r.article.id=? and r.markAsDeleted  ='true'").setParameter(0, id)
				.list();
		System.out.println("get comments" + replies.size());
		return replies;
	}

}
