package com.ktds.gmkim.vo;

public class ActorVO extends MovieVO{
//	ACTOR_ID	NUMBER
//	ACTOR_NAME	VARCHAR2(300 BYTE)
	
	private int actorId;
	private String actorName;
	
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	
}
