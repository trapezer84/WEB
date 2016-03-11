package com.ktds.smahn.reply.biz;

import com.ktds.smahn.reply.dao.ReplyDAO;
import com.ktds.smahn.reply.vo.ReplyVO;

public class ReplyBiz {

	private ReplyDAO replyDAO;
	
	public ReplyBiz() {
		replyDAO = new ReplyDAO();
	}
	
	
	public boolean addNewReplyDepthOne(ReplyVO reply) {

		return replyDAO.addFile(reply) > 0;
		
	}
}
