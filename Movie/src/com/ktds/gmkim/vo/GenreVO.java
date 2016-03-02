package com.ktds.gmkim.vo;

public class GenreVO extends MovieVO{
//	GENRE_ID	NUMBER
//	GENRE_TITLE	VARCHAR2(16 BYTE)
	
	private int genreId;
	private String genreTitle;
	
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getGenreTitle() {
		return genreTitle;
	}
	public void setGenreTitle(String genreTitle) {
		this.genreTitle = genreTitle;
	}
	
	
}
