package model;
import java.util.Date;

public class Candidate {
	//Properties
	private String fName;
	private String lName;
	private int age; 
	private String address;

	//no-arg constructor
	public Candidate() {
		
	}

	//Non-default constructor 
	public Candidate(String fName, String lName, int age, String address) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.address = address;
	}

	//Getters/Setters
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAdrress(String address) {
		this.address = address;
	}

	//Override toString() method
	@Override
	public String toString() {
		return "Canidate [fName=" + fName + ", lName=" + lName + ", age=" + age + ", adrress=" + address + "]";
	}

	

	
	
	
}
