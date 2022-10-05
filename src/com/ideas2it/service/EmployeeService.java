package com.ideas2it.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import org.hibernate.HibernateException;

import java.util.Set;

import com.ideas2it.dao.EmployeeDAO;
import com.ideas2it.model.Skills;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
	
/**
 * EmployeeService class has the control to
 * display Trainer & Trainee Details
 *
 * @version 1.0 08 Aug 2022
 *
 * @author  Ramasamy R M
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * <p>
     * Create New Trainer by passing object trainer for Trainer.
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation,
     *        String department, Long phoneNumber, String emailId, String dateOfBirth, 
     *        Float previousExperience, String dateOfJoining, Long salary
     * @return Integer trainer
     */
    public Integer addTrainer(Integer id, String firstName, String lastName, String designation,
                              String department, Long phoneNumber, String emailId,  
                              String dateOfBirth, Float previousExperience, String dateOfJoining, 
                              Long salary) throws HibernateException {
        Integer rowsAffected = null;
        Trainer trainer = new Trainer(id, firstName, lastName, designation, department, 
                                      phoneNumber, emailId, dateOfBirth, previousExperience, 
                                      dateOfJoining, salary);
        rowsAffected = employeeDAO.insertTrainer(trainer);
        return rowsAffected;
    }

    /**
     * <p>
     * Display All Trainers details 
     * </p>
     *
     * @param 
     * @return List<Trainer> trainers
     */
    public List<Trainer> getAllTrainers() throws HibernateException {
        List<Trainer> trainers = new ArrayList<Trainer>();
        trainers = employeeDAO.retrieveAllTrainers();
        return trainers;
    }

    /**
     * <p>
     * checkTrainerById is a method, checks whether the employee id
     * exists or not in the trainers
     * </p>
     *
     * @param Integer id
     * @return Boolean true, boolean false
     */
    public boolean checkTrainerById(Integer id) throws HibernateException {
        for (Trainer trainer : employeeDAO.retrieveAllTrainers()) {
            if (trainer.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Display specific Trainer by getting Id from the user. Which displays Id, 
     * name, designation, department,phone number, salary & experience. 
     * </p>
     *
     * @param Integer id 
     * @return List<Trainer> trainers
     */
    public Trainer getTrainerById(Integer id) throws HibernateException { 
        Trainer trainer = new Trainer();
        trainer = employeeDAO.retrieveTrainerById(id); 
        return trainer;
    }

    /**
     * <p>
     * Update specific Trainer by getting Id from the user. Which updates 
     * name, designation, department,phone number, salary & experience for the particular id. 
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation, String department, 
     *        Long phoneNumber, String emailId, String dateOfBirth, Float previousExperience, 
     *        String dateOfJoining, Long salary
     * @return String
     */
    public void updateTrainerById(Integer id, String firstName, String lastName, String designation, 
                                    String department, String phoneNumber, String emailId, 
                                    String dateOfBirth, String previousExperience, 
                                    String dateOfJoining, String salary) throws HibernateException {
        List<Trainer> trainers = employeeDAO.retrieveAllTrainers();
    	for (Trainer trainer : trainers) {
    		if ((trainer.getId()).equals(id)) {
    			if (!firstName.isEmpty()) {
    				trainer.setFirstName(firstName);
    			}
    			if (!lastName.isEmpty()) {
    				trainer.setLastName(lastName);
    			}
    			if (!designation.isEmpty()) {
    				trainer.setDesignation(designation);
    			}
    			if (!department.isEmpty()) {
    				trainer.setDepartment(department);
    			}
    			if (!phoneNumber.isEmpty()) {
    				trainer.setPhoneNumber(Long.parseLong(phoneNumber));
    			}
    			if (!emailId.isEmpty()) {
    				trainer.setEmailId(emailId);
    			}
    			if (!dateOfBirth.isEmpty()) {
    				trainer.setDateOfBirth(dateOfBirth);
    			}
    			if (!previousExperience.isEmpty()) {
    				trainer.setPreviousExperience(Float.parseFloat(previousExperience));
    			}
    			if (!dateOfJoining.isEmpty()) {
    				trainer.setDateOfJoining(dateOfJoining);
    			}
    			if (!salary.isEmpty()) {
    				trainer.setSalary(Long.parseLong(salary));
    			}   
    			employeeDAO.updateTrainerById(trainer);
    		}
    	}
    }

    /**
     * <p>
     * Delete specific Trainer by getting Id from the user. Which deletes 
     * all the details for the particular id.  
     * </p>
     *
     * @param Integer id
     * @return 
     */
    public void deleteTrainerById(Integer id) throws HibernateException {
        employeeDAO.deleteTrainerById(id);
    }

    /**
     * <p>
     * Create New Trainee by passing object trainee for Trainee.
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation,
     *        String department, long phoneNumber, String dateOfBirth, 
     *        String dateOfJoining, int passedOutYear, String listOfSkills 
     * @return void
     */
    public Integer addTrainee(Integer id, String firstName, String lastName, String designation,
                              String department, Long phoneNumber, String emailId,  
                              String dateOfBirth, Float previousExperience, String dateOfJoining, 
                              Integer passedOutYear, Set<Skills> skills) throws HibernateException {
        Integer rowsAffected = null;
        Trainee trainee = new Trainee(id, firstName, lastName, designation, department, phoneNumber, emailId,
                                      dateOfBirth, previousExperience, dateOfJoining, passedOutYear, skills);
        rowsAffected = employeeDAO.insertTrainee(trainee);
        return rowsAffected;
    }
    
    /**
     * <p>
     * Display All Trainees details  
     * </p>
     *
     * @param 
     * @return List<Trainee> trainees
     */
    public List<Trainee> getAllTrainees() throws HibernateException {
        List<Trainee> trainees = new ArrayList<Trainee>();
        trainees = employeeDAO.retrieveAllTrainees();
        return trainees;
    }

    /**
     * <p>
     * checkTraineeById is a method, checks whether the employee id
     * exists or not in the trainees
     * </p>
     *
     * @param Integer id
     * @return boolean true, boolean false
     */
    public Boolean checkTraineeById(Integer id) throws HibernateException {
        Boolean isValidTraineeId = false;
        for (Trainee trainee : employeeDAO.retrieveAllTrainees()) { 
            if (trainee.getId().equals(id)) {
                isValidTraineeId = true;
            }  
        } 
        return isValidTraineeId;
    }

    /**
     * <p>
     * Display specific Trainee by getting Id from the user. Which displays Id, 
     * name, designation, department, phone number, passed out year & skills. 
     * </p>
     *
     * @param Integer id - employee id value for the Trainee display operation
     * @return Trainee trainees
     */
    public Trainee getTraineeById(Integer id) throws HibernateException { 
        Trainee trainee = new Trainee();
        trainee = employeeDAO.retrieveTraineeById(id); 
        return trainee;
    }

    /**
     * <p>
     * Update specific Trainee by getting Id from the user, which updates Trainee's
     * details for the particular key. 
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation, String department, 
              long phoneNumber, String dateOfBirth, String dateOfJoining, Integer passedOutYear, 
              String listOfSkills
     * @return String
     */
    public void updateTraineeById(Integer id, String firstName, String lastName, String designation, 
            String department, String phoneNumber, String emailId, 
            String dateOfBirth, String previousExperience, 
            String dateOfJoining, String passedOutYear) throws HibernateException {
        List<Trainee> trainees = employeeDAO.retrieveAllTrainees();
        for (Trainee trainee : trainees) {
            if ((trainee.getId()).equals(id)) {
                if (!firstName.isEmpty()) {
                    trainee.setFirstName(firstName);
                }
                if (!lastName.isEmpty()) {
                    trainee.setLastName(lastName);
                }
                if (!designation.isEmpty()) {
                    trainee.setDesignation(designation);
                }
                if (!department.isEmpty()) {
                    trainee.setDepartment(department);
                }
                if (!phoneNumber.isEmpty()) {
                    trainee.setPhoneNumber(Long.parseLong(phoneNumber));
                }
                if (!emailId.isEmpty()) {
                    trainee.setEmailId(emailId);
                }
                if (!dateOfBirth.isEmpty()) {
                    trainee.setDateOfBirth(dateOfBirth);
                }
                if (!previousExperience.isEmpty()) {
                    trainee.setPreviousExperience(Float.parseFloat(previousExperience));
                }
                if (!dateOfJoining.isEmpty()) {
                    trainee.setDateOfJoining(dateOfJoining);
                }
                if (!passedOutYear.isEmpty()) {
                    trainee.setPassedOutYear(Integer.parseInt(passedOutYear));
                }
                employeeDAO.updateTraineeById(trainee);
            }
        }
    }  

    /**
     * <p>
     * Delete specific Trainer by getting Id from the user. Which deletes 
     * all the details for the particular id.  
     * </p>
     *
     * @param Integer id
     * @return 
     */
    public void deleteTraineeById(Integer id) throws HibernateException {
        employeeDAO.deleteTraineeById(id);
    }

    /**
     * <p>
     * Search All Employee details in Trainer 
     * based Firstname
     * </p>
     *
     * @param String option
     * @return List<Trainer> trainers
     */
    public List<Trainer> searchTrainerByFirstName(String firstName) throws HibernateException {
        List<Trainer> trainers = new ArrayList<Trainer>();
        trainers = employeeDAO.searchTrainerByFirstName(firstName);
        return trainers;
    }

    /**
     * <p>
     * Search All Employee details in Trainer 
     * based Lastname.
     * </p>
     *
     * @param String option
     * @return List<Trainer> trainers
     */
    public List<Trainer> searchTrainerByLastName(String lastName) throws HibernateException {
        List<Trainer> trainers = new ArrayList<Trainer>();
        trainers = employeeDAO.searchTrainerByLastName(lastName);
        return trainers;
    }

    /**
     * <p>
     * Search All Employee details in Trainee
     * based Firstname.
     * </p>
     *
     * @param String option
     * @return List<Trainee> trainees
     */
    public List<Trainee> searchTraineeByFirstName(String firstName) throws HibernateException {
    	List<Trainee> trainees = new ArrayList<Trainee>();
        trainees = employeeDAO.searchTraineeByFirstName(firstName);
        return trainees;
    }

    /**
     * <p>
     * Search All Employee details in Trainee
     * based on Lastname.
     * </p>
     *
     * @param String option
     * @return List<Trainee> trainees
     */
    public List<Trainee> searchTraineeByLastName(String lastName) throws HibernateException {
    	List<Trainee> trainees = new ArrayList<Trainee>();
        trainees = employeeDAO.searchTraineeByLastName(lastName); 
        return trainees;
    }  

    /**
     * <p>
     * Function to check if the First name is valid or not.
     * the matcher() method creates a matcher that will match the given input against this pattern
     * </p>
     *
     * @param String firstName
     * @return Boolean true, Boolean false 
     */   
    public Boolean isValidFirstName(String firstName) {
       return firstName.matches("[A-Z][a-zA-Z]*");
    } 

    /**
     * <p>
     * Function to check if the Last name is valid or not.
     * the matcher() method creates a matcher that will match the given input against this pattern
     * </p>
     *
     * @param String lastName
     * @return Boolean true, Boolean false 
     */   
    public Boolean isValidLastName(String lastName) {
       return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
    } 

    /**
     * <p>
     * Function to check if the mobile number is valid or not.
     * the matcher() method creates a matcher that will match the given input against this pattern
     * </p>
     *
     * @param String str
     * @return Boolean true, Boolean false
     */    
    public Boolean isValidPhoneNumber(String str) {  
        Pattern pattern = Pattern.compile("[6-9][0-9]{9}");  
        Matcher match = pattern.matcher(str);  
        return (match.find() && match.group().equals(str));  
    }
    
    /**
     * <p>
     * Function to check if the email id is valid or not.
     * the matcher() method creates a matcher that will match the given input against this pattern
     * </p>
     *
     * @param String emailId
     * @return Boolean true, Boolean false
     */   
    public Boolean isValidEmailId(String emailId) {
       String regex = "^[a-z][a-z0-9.]{4,}@[a-z0-9.]{5,}(com|in|co.in|org|edu)$";
       Pattern pattern = Pattern.compile(regex);  
       Matcher matcher = pattern.matcher(emailId);  
       return matcher.matches();  
    }  
    
    /**
     * <p>
     * Function to check if the employee's age is valid or not.
     * It calculates the age of an employee based on current date & birthday date
     * & checks the age should be greater than 18 and lesser than 60 to be a 
     * eligible employee
     * </p>
     *
     * @param LocalDate birthDate
     * @return Boolean true, Boolean false
     */   
    public Boolean isValidEmployee(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();   
        if ((birthDate != null) && (currentDate != null)) {  
            Integer age = Period.between(birthDate, currentDate).getYears();
            if ((age > 18 ) && (age < 60)) {
                return true;
            } else {
                return false;
            }
        } else {  
            return false;  
        }  
    }
    
    /**
     * <p>
     * Function to check if the employee's joining date is valid or not.
     * It compares the current date & joining date
     * </p>
     *
     * @param LocalDate date
     * @return Boolean true, Boolean false
     */   
    public Boolean isFutureDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();  
        if ((date != null) && (currentDate != null)) { 
            if (date.compareTo(currentDate) > 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    /**
     * <p>
     * It check the string that check the valid input of user menu.
     * </p>
     * @param {@link String} option
     * @return {@link boolean} If the string in correct format return true else false.
     **/
    public Boolean isvalidNumberInput(String string) {
        String validIdPatten = "[0-9]*";
        return string.matches(validIdPatten);
    } 
}