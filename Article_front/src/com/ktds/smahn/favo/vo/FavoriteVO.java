package com.ktds.smahn.favo.vo;

import com.ktds.smahn.article.vo.ArticleVO;

public class FavoriteVO extends ArticleVO{
	
	private int favoriteId;
	private String createdDate;
	
	public int getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
