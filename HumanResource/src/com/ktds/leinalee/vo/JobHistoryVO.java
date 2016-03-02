package com.ktds.leinalee.vo;

public class JobHistoryVO {

	private int employeeId;
	private String startDate;
	private String endDate;
	private String jobId;
	private int departmentId;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getStartDate() {
		return startDate.replace("00:00:00.0", "");
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate.replace("00:00:00.0", "");
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
}
