package com.ktds.gmkim.vo;

public class DirectorVO extends MovieVO{
	
//	DIRECTOR_ID	NUMBER
//	DIRECTOR_NAME	VARCHAR2(300 BYTE)
	
	private int directorId;
	private String directorName;
	
	public int getDirectorId() {
		return directorId;
	}
	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
}
