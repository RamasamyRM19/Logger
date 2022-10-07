import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.controller.EmployeeController;

/**
 * EmployeeManagement class has manage all the
 * Controller, model, service, dao & db classes
 *
 * @version 1.0 29 Aug 2022
 *
 * @author  Ramasamy R M
 */
public class EmployeeManagement {
    
    private static Logger log = LogManager.getLogger(EmployeeManagement.class.getName());
    
    public static void main(String[] args) {      
        log.info("Logger Started Successfully!");
        EmployeeController employeeController = new EmployeeController();
        employeeController.initiate();
    }
}