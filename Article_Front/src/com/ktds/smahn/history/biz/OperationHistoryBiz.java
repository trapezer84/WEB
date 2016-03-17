package com.ktds.smahn.history.biz;

import com.ktds.smahn.history.dao.OperationHistoryDAO;
import com.ktds.smahn.history.vo.OperationHistoryVO;

public class OperationHistoryBiz {

	private OperationHistoryDAO dao;
	
	public OperationHistoryBiz() {
		dao = new OperationHistoryDAO();
	}
	
	public void addHistory(OperationHistoryVO historyVO) {
		dao.insertHistory(historyVO);
	}
}
