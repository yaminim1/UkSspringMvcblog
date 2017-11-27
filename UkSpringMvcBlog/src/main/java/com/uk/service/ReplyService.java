package com.uk.service;

import java.util.List;

import com.uk.model.Reply;

public interface ReplyService {

	public Reply createReply(Integer articleTd, String userName, Reply reply);

	public Reply updateVerifyReply(Integer id, Integer replyId);

	public Reply updateDelFlgReply(Integer id, Integer replyId);

	public List<Reply> getComments(int id);

}
