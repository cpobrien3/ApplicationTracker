package model;

public class Application {
	//Properties
	private Job job;
	private Candidate canidate;
	private int yearsExperience;
	private int yearsUpperEducation;
	
	//Default no-arg constructor
	public Application() {
		
	}

	//Non-default constructors
	public Application(Job job, Candidate canidate, int yearsExperience, int yearsUpperEducation) {
		super();
		this.job = job;
		this.canidate = canidate;
		this.yearsExperience = yearsExperience;
		this.yearsUpperEducation = yearsUpperEducation;
	}

	//Getters/Setters
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Candidate getCandidate() {
		return canidate;
	}

	public void setCandidate(Candidate canidate) {
		this.canidate = canidate;
	}

	public int getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(int yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

	public int getYearsUpperEducation() {
		return yearsUpperEducation;
	}

	public void setYearsUpperEducation(int yearsUpperEducation) {
		this.yearsUpperEducation = yearsUpperEducation;
	}

	//Override toString() Method
	@Override
	public String toString() {
		return "Application [job=" + job + ", canidate=" + canidate + ", yearsExperience=" + yearsExperience
				+ ", yearsUpperEducation=" + yearsUpperEducation + "]";
	}
	
	
}
