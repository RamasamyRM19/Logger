package com.ideas2it.model;

import com.ideas2it.model.Employee;

/**
 * Trainer POJO class is to get and set Trainer details
 *
 * @version 1.0 28 Jul 2022
 *
 * @author  Ramasamy R M
 */
public class Trainer extends Employee {

    private Long salary;

    public Trainer() {
    }

    public Trainer(Integer id, String firstName, String lastName, String designation, 
                    String department, Long phoneNumber, String emailId, String dateOfBirth, 
                    Float previousExperience, String dateOfJoining, Long salary) {
        super(id, firstName, lastName, designation, department, phoneNumber, emailId, dateOfBirth, 
              previousExperience, dateOfJoining);
        this.salary = salary;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String toString() {
        return (super.toString() + "\nSalary        : " + salary);
    }

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}