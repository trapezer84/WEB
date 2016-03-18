package com.ktds.smahn.favo.biz;

import com.ktds.smahn.favo.dao.FavoriteDAO;
import com.ktds.smahn.favo.vo.FavoriteVO;

public class FavoriteBiz {

	private FavoriteDAO favoriteDAO;
	
	public FavoriteBiz() {
		favoriteDAO = new FavoriteDAO();
	}
	
	public boolean isExistFavoriteData(FavoriteVO favoriteVO) {
		// 따로 만든 이유는 즐겨찾기의 여부를 표기해주기 위해서 따로 만듬 
		return favoriteDAO.selectFavoriteCount(favoriteVO) > 0;
	}
	
	public void insertOrDeleteFavoriteData(FavoriteVO favoriteVO) {
		if (isExistFavoriteData(favoriteVO)) {
			favoriteDAO.deleteFavorite(favoriteVO);
		} else {
			favoriteDAO.insertFavorite(favoriteVO);
		}
	}
}
