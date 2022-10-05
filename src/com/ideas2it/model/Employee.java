package com.ideas2it.model;

/**
 * Employee POJO class is to get and set Employee details
 *
 * @version 1.0 28 Jul 2022
 *
 * @author  Ramasamy R M
 */
public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String designation;
    private String department;
    private Long phoneNumber;
    private String emailId;
    private String dateOfBirth;
    private Float previousExperience;
    private String dateOfJoining;

    public Employee() {
    }

    public Employee(Integer id, String firstName, String lastName, String designation, 
                    String department, Long phoneNumber, String emailId, String dateOfBirth, 
                    Float previousExperience, String dateOfJoining) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.department = department;
        this.phoneNumber = phoneNumber; 
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.previousExperience = previousExperience;
        this.dateOfJoining = dateOfJoining; 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) { 
        this.dateOfBirth = dateOfBirth;
    }

    public Float getPreviousExperience() {
        return previousExperience;
    }
 
    public void setPreviousExperience(Float previousExperience) {
        this.previousExperience = previousExperience;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) { 
        this.dateOfJoining = dateOfJoining;
    }
 
    public String toString() {
        return ("\nID            : " + id + "\nName          : " + firstName + " " + lastName
                + "\nDesignation   : " + designation + "\nDepartment    : " + department
                + "\nPhone Number  : " + phoneNumber + "\nEmail Id : " + emailId
                + "\nDate of Joining : " + dateOfJoining);
    }
}