package com.ideas2it.controller;

import java.lang.System;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import com.ideas2it.model.Skills;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.service.EmployeeService;

/**
 * EmployeeController class has the control to display
 * Trainer & Trainee Details using model, service & dao
 *
 * @version 1.0 29 Aug 2022
 *
 * @author  Ramasamy R M
 */
public class EmployeeController { 

    private EmployeeService employeeService = new EmployeeService();

    private Scanner scanner = new Scanner(System.in);
    
    /* Get the class name to be printed on */  
    private static Logger log = LogManager.getLogger(EmployeeController.class.getName());
    
    /**
     * <p>
     * Initial level of the application
     * </p>
     *
     * @param
     * @return void
     */
    public void initiate() {

      //BasicConfigurator.configure();
        Boolean choice = true;
        Integer option = 0; 
        while (choice) {
            log.info("\n\t\t\t\tWelcome to Ideas2IT!"
                    + "\n\t\t\t\t--------------------");
            log.info("\n\t\t\tPlease Select your option from the list");
            log.info("\n\tTrainer\t\t\t\tTrainee\n"
                    + "\t-------\t\t\t\t-------\n"
                    + "1. Create New Trainer\t\t| 6. Create New Trainee\t\t | "
                    + "11. Search Employee\n2. Display All Trainer Details\t| "
                    + "7. Display All Trainee Details | 12. Exit\n"
                    + "3. Display Trainer Based on ID\t| 8. Display Trainee Based on ID |\n"
                    + "4. Update Trainer Based on ID\t| 9. Update Trainee Based on ID\t |\n"
                    + "5. Delete Trainer Based on ID\t| 10. Delete Trainee Based on ID |\n");
            log.info("Enter your option : ");
            String userInput = scanner.next();
            option = (employeeService.isvalidNumberInput(userInput)) 
                    ? Integer.parseInt(userInput) : 0; 
            listOfOptions(option);
        }
    }

    /**
     * <p>
     * Contains the create, displayAll, displayById, updateById, deleteById
     * method for both Trainer & Trainee.
     * </p>
     *
     * @param int option
     * @return void
     */
    public void listOfOptions(int option) {
        switch(option) {
        case 1, 6:
            createNewEmployee(option);
        break;
        case 2:
            displayAllTrainers();
            break;
        case 3:
            log.info("Enter the Employee Id: ");
            Integer id = scanner.nextInt();
            displayTrainerById(id);
            break;
        case 4:
            log.info("Enter the Employee Id: ");
            id = scanner.nextInt();
            updateTrainerById(id);
            break;
        case 5: 
            log.info("Enter the Employee Id: ");
            id = scanner.nextInt();
            deleteTrainerById(id);
            break;
        case 7:
            displayAllTrainees();
            break;		
        case 8:
            log.info("Enter the Employee Id: ");
            id = scanner.nextInt();
            displayTraineeById(id);
            break;
        case 9: 
            log.info("Enter the Employee Id: ");
            id = scanner.nextInt();
            updateTraineeById(id);
            break;
        case 10:
            log.info("Enter the Employee Id: ");
            id = scanner.nextInt();
            deleteTraineeById(id);
            break;
        case 11:
            searchAnEmployee();
            break;
        case 12:
            log.info("Thank You!!!");
            System.exit(0);
            break;
        default:
            log.info("PLEASE ENTER CORRECT OPTION!!!");
            break;
        }
    }

    /**
     * <p>
     * Create New Employee by collecting their name, designation, department,
     * phone number. Here, we generate Employee Id by providing some statements.
     * For both Trainer & Trainee, these are common attributes.
     * </p>
     *
     * @param int option
     * @return void
     */
    public void createNewEmployee(int option) {
        
        try {
            log.info("\nCreate New Employee");
            log.info("--------------------");

            Integer employeeId = 0;

            String firstName;
            Boolean isValidFirstName;
            do {
                log.info("First Name        : ");
                firstName = scanner.next();
                isValidFirstName = employeeService.isValidFirstName(firstName);
                if (!isValidFirstName) { 
                    log.warn("Entered First Name is Invalid");
                }
            } while (!isValidFirstName);

            String lastName;
            Boolean isValidLastName;
            do {
                log.info("Last Name         : ");
                lastName = scanner.next();
                isValidLastName = employeeService.isValidLastName(lastName);
                if (!isValidLastName) {
                    log.warn("Entered Last Name is Invalid");
                }
            } while (!isValidLastName);

            log.info("Designation       : ");
            String designation = scanner.next();

            log.info("Department        : ");
            String department = scanner.next();

            String phoneNumber;
            Boolean isValidPhoneNumber;
            do {
                log.info("Phone Number      : ");
                phoneNumber = scanner.next();
                isValidPhoneNumber = employeeService.isValidPhoneNumber(phoneNumber);
                if (!isValidPhoneNumber) {
                    log.warn("Entered Phone number is invalid.");
                }
            } while (!isValidPhoneNumber);

            String emailId;
            Boolean isValidEmailId;
            do {
                log.info("Email ID : ");
                emailId = scanner.next();
                isValidEmailId = employeeService.isValidEmailId(emailId);
                if (!isValidEmailId) {
                    log.warn("Enter Correct Email Id");
                }
            } while (!isValidEmailId);

            String dateOfBirth;
            Boolean isValidAge;
            do {
                log.info("Date of birth in DD/MM/YYYY : ");
                dateOfBirth = scanner.next();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
                LocalDate birthDate = LocalDate.parse(dateOfBirth, dateFormat);
                isValidAge = employeeService.isValidEmployee(birthDate);
                if (!isValidAge) {
                    log.error("Not Eligibile to be an Employee!!!");
                }
            } while (!isValidAge);

            log.info("Previous Experience in yrs : ");
            String previousExperience = scanner.next();

            String dateOfJoining;
            Boolean isValidJoining;
            do {
                log.info("Date of Joining in DD/MM/YYYY : ");
                dateOfJoining = scanner.next();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
                LocalDate joiningDate = LocalDate.parse(dateOfJoining, dateFormat);
                isValidJoining = employeeService.isFutureDate(joiningDate);
                if (!isValidJoining) {
                    log.warn("Date of joining must not be a future Date");
                }
            } while (!isValidJoining);

            if (option == 1) {
                createNewTrainer(employeeId, firstName, lastName, designation, department, 
                        Long.parseLong(phoneNumber), emailId, dateOfBirth, 
                        Float.parseFloat(previousExperience), dateOfJoining);
            } else {
                createNewTrainee(employeeId, firstName, lastName, designation, department, 
                        Long.parseLong(phoneNumber), emailId, dateOfBirth, 
                        Float.parseFloat(previousExperience), dateOfJoining);
            } 
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
        }  
    }

    /**
     * <p>
     * Create New Trainer by collecting details from employee method and also collecting
     * their salary and we store the details in employeeService.
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation,
     *        String department, long phoneNumber, String emailId, String dateOfBirth, 
     *        float previousExperience, String dateOfJoining
     * @return void
     */
    public void createNewTrainer(Integer id, String firstName, String lastName, String designation,
            String department, Long phoneNumber, String emailId,  
            String dateOfBirth, Float previousExperience, 
            String dateOfJoining) { 

        log.info("Salary            : ");
        Long salary = scanner.nextLong();  

        Integer employeeId = employeeService.addTrainer(id, firstName, lastName, designation, department,
                phoneNumber, emailId, dateOfBirth, 
                previousExperience, dateOfJoining, salary);
        log.info("Employee Id : " + employeeId);
    }

    /**
     * <p>
     * Display All Trainers details which contains Id, name, designation, department,
     * phone number, date of birth, previous experience, date of joining, salary. 
     * </p>
     *
     * @param 
     * @return void
     */
    public void displayAllTrainers() {
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            log.warn("No records!");
        } else {
            Integer iterate = 1;
            for (Trainer trainer : trainers) {
                log.info("\nTrainer Detail" + " (" + iterate + "):");
                log.info("-------------------");
                log.info(trainer);
                iterate++;
            }
        }
    }

    /**
     * <p>
     * Display specific Trainer by getting Id from the user. Which displays Id, 
     * name, designation, department,phone number, salary & experience. 
     * </p>
     *
     * @param Integer employeeId
     * @return 
     */
    public void displayTrainerById(Integer employeeId) {
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            log.warn("No records!");
        } else {
            Boolean checkById = employeeService.checkTrainerById(employeeId); 
            if (checkById == true) {
                Trainer trainer = employeeService.getTrainerById(employeeId);
                log.info(trainer);
            } else {
                log.warn("There is no existing record for the given ID!");
            }
        }
    } 

    /**
     * <p>
     * Update specific Trainer by getting Id from the user. Which updates 
     * name, designation, department, phone number, salary & experience for the particular id. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void updateTrainerById(Integer employeeId) {
        int updateOption = 0;
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            log.warn("No records!");
        } else {
            Boolean checkById = employeeService.checkTrainerById(employeeId);
            if (checkById == true) {
                do {
                    displayTrainerById(employeeId);
                    log.info("Press 1. Confirm Update All Details\t2. Back");
                    updateOption = scanner.nextInt();
                    switch (updateOption) {
                    case 1:
                        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                        log.info("First Name        : ");
                        String firstName = scanner.nextLine();
                        log.info("Last Name         : ");
                        String lastName = scanner.nextLine();
                        log.info("Designation           : ");
                        String designation = scanner.nextLine();
                        log.info("Department            : ");
                        String department = scanner.nextLine();
                        log.info("Phone Number      : ");
                        String phoneNumber = scanner.nextLine();
                        log.info("Email ID : ");
                        String emailId = scanner.nextLine();
                        log.info("Date of Birth(DD/MM/YYYY): ");
                        String dateOfBirth = scanner.nextLine();
                        log.info("Previous Experience in yrs : ");
                        String previousExperience = scanner.nextLine();
                        log.info("Date of Join (DD/MM/YYYY): ");
                        String dateOfJoining = scanner.nextLine();
                        log.info("Salary            : ");
                        String salary = scanner.nextLine();

                        employeeService.updateTrainerById(employeeId, firstName, lastName, 
                                designation, department, phoneNumber, emailId, dateOfBirth,
                                previousExperience, dateOfJoining, salary);
                        log.info("Record Updated Successfully!");
                        break;
                    case 2: 
                        log.warn("No Record has been Updated!");
                        break;
                    default:
                        log.warn("Choose correct option!!!");
                        break;
                    }
                } while (updateOption != 2);
            } else {
                log.warn("There is no existing record for the given ID!");
            }
        }
    }

    /**
     * <p>
     * Delete specific Trainer by getting Id from the user. Which deletes 
     * all the details for the particular id.  
     * </p>
     *
     * @param Integer employeeId - employee id value for the Trainer delete operation
     * @return void
     */
    public void deleteTrainerById(Integer employeeId) {
        Integer updateOption = 0;
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            log.warn("No records!");
        } else {
            Boolean checkById = employeeService.checkTrainerById(employeeId);
            if (checkById == true) {
                displayTrainerById(employeeId);
                log.info("1. Confirm Delete Details\t2. Back");
                updateOption = scanner.nextInt();
                switch (updateOption) {
                case 1:
                    employeeService.deleteTrainerById(employeeId);
                    log.info("Employee ID : " + employeeId + " Deleted Successfully!");
                    break;
                case 2:
                    log.warn("No Record has been Deleted!");
                    break;
                default:
                    log.warn("Choose correct option!!!");
                    break;
                }
            } else {
                log.warn("There is no existing record for the given ID!");
            }
        }
    }

    /**
     * <p>
     * Create New Trainee by collecting details from employee method and also collecting
     * their passed out year & list of skills and we store the details in employeeService.
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation,
     *        String department, long phoneNumber, String emailId, String dateOfBirth, 
     *        float previousExperience, String dateOfJoining
     * @return void
     */
    public void createNewTrainee(Integer id, String firstName, String lastName, 
            String designation, String department, Long phoneNumber,   
            String emailId, String dateOfBirth, Float previousExperience,
            String dateOfJoining) { 
        log.info("Passed Out Year     : ");
        Integer passedOutYear = scanner.nextInt();
        Set<Skills> skillSet = new LinkedHashSet<Skills>();
        log.info("Known Skills      : ");
        log.info("Enter Total no. of Skills : ");
        int numberOfSkills = scanner.nextInt();
        for (int listIndex = 1; listIndex <= numberOfSkills; listIndex++) {
            Skills skills = new Skills();
            log.info("Enter the Skill " + listIndex + " : ");
            String skillName = scanner.next();
            skills.setSkillName(skillName);
            log.info("Enter Version : ");
            String skillVersion =  scanner.next();
            skills.setSkillVersion(skillVersion);
            log.info("Enter Last Used Year : ");
            Integer lastUsedYear = scanner.nextInt();
            skills.setLastUsedYear(lastUsedYear);
            log.info("Enter Skill Experience : ");
            Float skillExperience = scanner.nextFloat();
            skills.setSkillExperience(skillExperience);
            skillSet.add(skills);
        }
        log.info("\nTrainee created Successfully!!!");
        Integer employeeId = employeeService.addTrainee(id, firstName, lastName, designation, 
                department, phoneNumber, emailId, dateOfBirth, previousExperience, dateOfJoining,
                passedOutYear, skillSet);
        log.info("Employee Id : " + employeeId);
    }

    /**
     * <p>
     * Display All Trainees details which contains Id, name, designation, department,
     * phone number, pass out year & skill. 
     * </p>
     *
     * @param 
     * @return void
     */    
    public void displayAllTrainees() {
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()) {
            log.warn("No records!");
        } else {
            Integer iterate = 1;
            for (Trainee trainee : trainees) {
                log.info("\nTrainee Detail" + " (" + iterate + "):");
                log.info("-------------------");
                log.info(trainee);
                iterate++;
            }
        }
    }

    /**
     * <p>
     * Display specific Trainee by getting Id from the user, which displays Id, 
     * name, designation, department, phone number, date of birth, previous experience,
     * date of joining, passed out year & skill. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void displayTraineeById(Integer employeeId) {
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()){
            log.warn("No records!");
        } else {
            Boolean checkById = employeeService.checkTraineeById(employeeId);
            if (checkById == true) {
                Trainee trainee = employeeService.getTraineeById(employeeId); 
                log.info(trainee);
            } else {
                log.info("There is no existing record for the given ID!");
            }
        }
    }    

    /**
     * <p>
     * Update specific Trainee by getting Id from the user, which updates 
     * name, designation, department, phone number, date of birth, previous experience,
     * date of joining, pass out year & skill for the particular id. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void updateTraineeById(Integer employeeId) {
        Integer updateOption = 0;
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()) {
            log.warn("No records!");
        } else {
            Boolean checkById = employeeService.checkTraineeById(employeeId);
            if (checkById == true) {
                do {
                    displayTraineeById(employeeId);
                    log.info("1. Confirm Update All Details\t2. Back");
                    updateOption = scanner.nextInt();
                    switch (updateOption) {
                    case 1:
                        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                        log.info("First Name        : ");
                        String firstName = scanner.nextLine();
                        log.info("Last Name         : ");
                        String lastName = scanner.nextLine();
                        log.info("Designation           : ");
                        String designation = scanner.nextLine();
                        log.info("Department            : ");
                        String department = scanner.nextLine();
                        log.info("Phone Number      : ");
                        String phoneNumber = scanner.nextLine();
                        log.info("Email ID : ");
                        String emailId = scanner.nextLine();
                        log.info("Date of Birth(DD/MM/YYYY): ");
                        String dateOfBirth = scanner.nextLine();
                        log.info("Previous Experience : ");
                        String previousExperience = scanner.nextLine();
                        log.info("Date of Join (DD/MM/YYYY): ");
                        String dateOfJoining = scanner.nextLine();
                        log.info("Passed Out Year     : ");
                        String passedOutYear = scanner.nextLine();
                        employeeService.updateTraineeById(employeeId, firstName, lastName, 
                                designation, department, phoneNumber, emailId, dateOfBirth, 
                                previousExperience, dateOfJoining, passedOutYear);
                        log.info("Record has been Updated Successfully!");
                        break;
                    case 2:
                        log.info("No Record has bee Updated!");
                        break;
                    default:
                        log.warn("Choose correct option!!!");
                        break;
                    }
                } while (updateOption != 2);
            } else {
                log.warn("There is no existing record for the given ID!");
            } 
        }
    }

    /**
     * <p>
     * Delete specific Trainee by getting Id from the user. Which deletes 
     * all the details for the particular id. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void deleteTraineeById(Integer employeeId) {
        Integer updateOption = 0;
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()){
            log.warn("No records!");
        } else {
            Boolean checkById = employeeService.checkTraineeById(employeeId);
            if (checkById == true) {
                displayTraineeById(employeeId);
                log.info("1. Confirm Delete Details\t2. Back");
                updateOption = scanner.nextInt();
                switch (updateOption) {
                case 1:
                    employeeService.deleteTraineeById(employeeId);
                    log.info("Employee ID : " + employeeId + " Deleted Successfully!");
                    break;
                case 2:
                    log.warn("No Record has been Deleted!");
                    break;
                default:
                    log.warn("Choose correct option!!!");
                    break;
                }
            } else {
                log.warn("There is no existing record for the given ID!");
            }
        }
    }

    /**
     * <p>
     * Search All Employee details - both Trainer & Trainee 
     * based on Firstname & Lastname of an Employee. 
     * </p>
     *
     * @param 
     * @return void
     */
    public void searchAnEmployee() {
        List<Trainer> trainers = new ArrayList<Trainer>();
        List<Trainee> trainees = new ArrayList<Trainee>();
        log.info("Search an Employee based on following ways");
        log.info("1. First Name\t2. Last Name");
        log.info("Enter your way : ");
        Integer searchWay = scanner.nextInt();
        switch (searchWay) {
        case 1: 
            log.info("Searching based on First Name");
            log.info("Enter Employee First Name : ");
            String firstName = scanner.next();
            trainers = employeeService.searchTrainerByFirstName(firstName);
            trainees = employeeService.searchTraineeByFirstName(firstName);
            if (trainers != null) {
                log.info(trainers); 
            }
            if (trainees != null) {
                log.info(trainees);
            }
            break;
        case 2: 
            log.info("Searching based on Last Name");
            log.info("Enter Employee Last Name : ");
            String lastName = scanner.next();
            trainers = employeeService.searchTrainerByLastName(lastName); 
            trainees = employeeService.searchTraineeByLastName(lastName);
            if (trainers != null) {
                log.info(trainers); 
            }
            if (trainees != null) {
                log.info(trainees);
            }
            break;
        default:
            log.warn("Enter Correct Option!");
            break;
        }
    }
}
