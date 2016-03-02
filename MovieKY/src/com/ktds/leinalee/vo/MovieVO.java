package com.ktds.leinalee.vo;

public class MovieVO extends GradeVO{

	private int movieId;
	private String title;
	private double rate;
	private String runningTime;
	private String openDate;
	private int gradeId;
	private int actorCount;
	
	public int getActorCount() {
		return actorCount;
	}
	public void setActorCount(int actorCount) {
		this.actorCount = actorCount;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	public String getOpenDate() {
		return openDate.replace("00:00:00.0", "");
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	
}
