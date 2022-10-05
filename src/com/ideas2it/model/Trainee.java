package com.ideas2it.model;

import java.util.Set;

import com.ideas2it.model.Employee;

/**
 * Trainee POJO class is to get and set Trainee details
 *
 * @version 1.0 28 Jul 2022
 *
 * @author  Ramasamy R M
 */
public class Trainee extends Employee {

    private Integer passedOutYear;
    private Set<Skills> skills;

    public Trainee() {
    }

    public Trainee(Integer id, String firstName, String lastName, String designation, 
                   String department, Long phoneNumber, String emailId, String dateOfBirth, 
                   Float previousExperience, String dateOfJoining, Integer passedOutYear, 
                   Set<Skills> skills) {
        super(id, firstName, lastName, designation, department, phoneNumber, emailId, dateOfBirth, 
              previousExperience, dateOfJoining);
        this.passedOutYear = passedOutYear;
        this.skills = skills;
    }

    public Trainee(Integer id, String firstName, String lastName, String designation, 
                   String department, Long phoneNumber, String emailId, String dateOfBirth, 
                   Float previousExperience, String dateOfJoining, Integer passedOutYear) {
        super(id, firstName, lastName, designation, department, phoneNumber, emailId, dateOfBirth, 
              previousExperience, dateOfJoining);
        this.passedOutYear = passedOutYear;
    }

    public Integer getPassedOutYear() {
        return passedOutYear;
    }

    public void setPassedOutYear(Integer passedOutYear) {
        this.passedOutYear = passedOutYear;
    }
   
    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
     }

    public String toString() {
        return (super.toString() + "\nPassed Out Year : " + passedOutYear  
                + "\nSkills : " 
                + skills.toString().replace("[", "").replace(",", "\n").replace("]", ""));
    }

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}