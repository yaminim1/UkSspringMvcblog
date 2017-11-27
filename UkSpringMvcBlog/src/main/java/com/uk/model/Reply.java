package com.uk.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reply")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reply_id")
	private Integer replyId;
	@Column(name = "description")
	private String description;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "verify_flg")
	private boolean verifyFlag;
	@Column(name = "del_flg")
	private boolean markAsDeleted;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private Article article;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;

	public Reply(Integer replyId, String description, String createdBy, Date createdDate, boolean verifyFlag,
			boolean markAsDeleted, Article article) {
		super();
		this.replyId = replyId;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.verifyFlag = verifyFlag;
		this.markAsDeleted = markAsDeleted;
		this.article = article;
	}

	public Reply() {
		super();
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(boolean verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public Article getArticle() {
		return article;
	}

	public boolean isMarkAsDeleted() {
		return markAsDeleted;
	}

	public void setMarkAsDeleted(boolean markAsDeleted) {
		this.markAsDeleted = markAsDeleted;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
