package com.ideas2it.exception;

/**
 * User defined Exception class extends RuntimeException.
 *
 * @version 1.0 21 Sep 2022
 * 
 * @author Ramasamy R M 
 */
public class EmployeeNotFound extends RuntimeException{
    /**
     * <p>
     * Parameterized constructor that sent message through exception.
     * </p>
     *
     * @param {@link String} message.
     **/
    public EmployeeNotFound(String message) {
        super(message);
    }
}
