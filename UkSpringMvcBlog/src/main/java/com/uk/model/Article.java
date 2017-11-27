package com.uk.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "reply_count")
	private Integer replyCount;
	@Column(name = "reply_flg")
	private boolean repliedFlag;
	@Column(name = "verify_flg")
	private boolean verifyFlag;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.ALL)
	private List<Reply> reply;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;

	@Column(name = "del_flg")
	private boolean markAsDeleted;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "last_update_by")
	private String lastUpdatedBy;
	@Column(name = "last_update_date")
	private Date lastUpdatedDate;

	public Article() {
		super();
	}

	public Article(Integer id, String title, String description, Integer replyCount, boolean repliedFlag,
			boolean verifyFlag, List<Reply> reply, boolean markAsDeleted, String createdBy, Date createdDate,
			String lastUpdatedBy, Date lastUpdatedDate, Users user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.replyCount = replyCount;
		this.repliedFlag = repliedFlag;
		this.verifyFlag = verifyFlag;
		this.reply = reply;
		this.markAsDeleted = markAsDeleted;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.user = user;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getreplyCount() {
		return replyCount;
	}

	public void setreplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public boolean isRepliedFlag() {
		return repliedFlag;
	}

	public void setRepliedFlag(boolean repliedFlag) {
		this.repliedFlag = repliedFlag;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public boolean isVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(boolean verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	public boolean isMarkAsDeleted() {
		return markAsDeleted;
	}

	public void setMarkAsDeleted(boolean markAsDeleted) {
		this.markAsDeleted = markAsDeleted;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void addReply(Reply r) {
		reply.add(r);
		r.setArticle(this);
	}

	public void removeReply(Reply r) {
		r.setArticle(null);
		this.reply.remove(reply);
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
