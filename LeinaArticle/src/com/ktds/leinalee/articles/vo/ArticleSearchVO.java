package com.ktds.leinalee.articles.vo;

public class ArticleSearchVO {
	
	//검색만을 위한 VO
	private int pageNO;
	private int startIndex;
	private int endIndex;
//	private String searchKeyword;
	
	public int getPageNO() {
		return pageNO;
	}
	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
