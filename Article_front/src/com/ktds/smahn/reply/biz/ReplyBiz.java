package com.ktds.smahn.reply.biz;

import com.ktds.smahn.reply.dao.ReplyDAO;
import com.ktds.smahn.reply.vo.ReplyVO;

public class ReplyBiz {

	private ReplyDAO replyDAO;
	
	public ReplyBiz() {
		replyDAO = new ReplyDAO();
	}
	
	
	public boolean addNewReplyDepthOne(ReplyVO reply) {

		// 우리가 댓글을 달 동안 다른 사람들이 또 달 수 있기 때문에 orderNo은 등록시 다시 받아와야한다. 
		// 계속 사용할 수 없다 
		// depth 부모글의 depth + 1 (근데 이미 받아올 때부터 했음) 
		// orderNo 부모글의 orderNo + 1
		// depth 부모글의 depth + 1 
		// orderNo 현재 parentReplyId 값의 max + 1
		
		int orderNo = this.getNewOrderNo(reply);
		reply.setOrderNo(orderNo);
		
		// 일반 댓글이 아니라면
		if ( orderNo > 0 ) { 
			replyDAO.updateOrderNobyGroupId(reply);
		}
		
		return replyDAO.addFile(reply) > 0;
		
	}


	private int getNewOrderNo(ReplyVO reply) {

		// 0 보다 크다면 대댓글을 달고 있다는 것 
		if ( replyDAO.selectCountParentReplyId(reply) > 0 ) {
			int newOrderNo = replyDAO.selectMaxOrderNoByParentReplyId(reply);
			return newOrderNo + 1;
		} else {
			if ( reply.getParentReplyId() != 0 ) { //일반 댓글인 경우 = 0 , 일반 댓글 아닌 경우 != 0  
				int newOrderNo = replyDAO.selectLatestOrderNoByParentReplyId(reply);
				return newOrderNo + 1;
			}
		}
		
		return 0;
	}
}