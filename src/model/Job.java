package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Job {
	//Properties
	private String position;
	private String jobType;
	private LocalTime startTime;
	private LocalTime endTime;
	private double hourlywage;
	
	//No-arg constructor
	public Job() {
		super();
	}

	//Non-default constructor
	public Job (String position, String jobType, LocalTime startTime, LocalTime endTime,
			double hourlywage) {
		super();
		this.position = position;
		this.jobType = jobType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hourlywage = hourlywage;
	}

	//Getters/Setters
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public double getHourlywage() {
		return hourlywage;
	}

	public void setHourlywage(double hourlywage) {
		this.hourlywage = hourlywage;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	//Override toString() method
	@Override
	public String toString() {
		return "Job [position=" + position + ", jobType=" + jobType + ", startTime="
				+ startTime + ", endTime=" + endTime + ", hourlywage=" + hourlywage + "]";
	}

	

	
	
	
	
	
	
}
