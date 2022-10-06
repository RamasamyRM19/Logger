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
    
    public static void main(String[] args) {      
        EmployeeController employeeController = new EmployeeController();
        employeeController.initiate();
    }
}