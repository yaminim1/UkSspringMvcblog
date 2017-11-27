package com.uk.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uk.dao.ReplyDao;
import com.uk.model.Reply;
import com.uk.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyDao replyDao;

	@Override
	public Reply createReply(Integer articleTd, String userName, Reply reply) {

		return replyDao.createReply(articleTd, userName, reply);
	}

	@Override
	public Reply updateVerifyReply(Integer id, Integer replyId) {
		return replyDao.updateVerifyReply(id, replyId);
	}

	@Override
	public Reply updateDelFlgReply(Integer id, Integer replyId) {
		return replyDao.updateDelFlgReply(id, replyId);
	}

	@Override
	public List<Reply> getComments(int id) {
		return replyDao.getComments(id);
	}

}
