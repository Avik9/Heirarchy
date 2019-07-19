import java.util.ArrayList;

/**
 * Defines the object <code>EmployeeNode</code> as all the information of the employee.
 *
 * @author Avik Kadakia
 **/
public class EmployeeNode {
    private String employee_number, first_name, last_name, original_name, deconstructed_name, middle_name, former_name,
            preferred_name, email, title, company, department_name, old_department_name, payroll_department, location,
            month, day, gender, manager_employee_number, original_manager_name;
    private EmployeeNode manager;
    private ArrayList<EmployeeNode> reporters;

    /**
     * Consists of all the  parameters of this class to create a full Employee Node object
     *
     * @param first_name             First name of the employee
     * @param last_name              Last name of the employee
     * @param name                   Deconstructed name of the employee from the EmployeeManager.java
     * @param middle_name            Middle name of the employee
     * @param former_name            Former name of the employee
     * @param preferred_name         Preferred name of the employee
     * @param email                  Business email of the employee
     * @param title                  Current position of the employee
     * @param company                Company the employee currently works at
     * @param department_name        Department name of the employee
     * @param payroll_department     Payroll department of the employee
     * @param location               Where the employee is currently located
     * @param month                  Birth month of the employee
     * @param day                    Birth day of the employee
     * @param gender                 Sex of the employee
     * @param newManager             Manager object of the employee
     * @param new_employee_number    Employee Id of the employee
     * @param manager_employeeNumber Employee's manager's employee Id
     * @param initial_manager_name   Initial name of the employee's manager
     */
    EmployeeNode(String first_name, String last_name, String name, String middle_name, String former_name,
                 String preferred_name, String email, String title, String company, String department_name,
                 String payroll_department, String location, String month, String day, String gender,
                 EmployeeNode newManager, String new_employee_number, String manager_employeeNumber, String initial_manager_name) {
        this.original_name = first_name + last_name;
        this.deconstructed_name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.former_name = former_name;
        this.preferred_name = preferred_name;
        this.email = email;
        this.title = title;
        this.company = company;
        this.department_name = department_name;
        this.old_department_name = department_name;
        this.payroll_department = payroll_department;
        this.location = location;
        this.month = month;
        this.day = day;
        this.gender = gender;
        this.manager = newManager;
        this.employee_number = new_employee_number;
        this.manager_employee_number = manager_employeeNumber;
        this.original_manager_name = initial_manager_name.replace("\"", "");
        this.reporters = new ArrayList<>();
    }

    /**
     * An EmployeeNode object with only some of its attributes. Mainly used to create temperory manager or temperory employee objects
     *
     * @param name                Deconstructed name of the employee from the EmployeeManager.java
     * @param department_name     Department name of the employee
     * @param newManager          Manager object of the employee
     * @param new_employee_number Employee Id of the employee
     */
    EmployeeNode(String name, String department_name, EmployeeNode newManager, String new_employee_number) {
        this.first_name = name.substring(0, name.indexOf(" "));
        this.last_name = name.substring(name.indexOf(" ") + 1);
        this.department_name = department_name;
        this.old_department_name = department_name;
        this.manager = newManager;
        this.employee_number = new_employee_number;
        this.reporters = new ArrayList<>();
    }

    /**
     * Returns the name of the employee
     *
     * @return getFirstName() + " " + getLastName()
     */
    String getName() {
        return getFirstName().trim() + " " + getLastName().trim();
    }

    /**
     * Returns the first name of the employee
     *
     * @return first_name
     */
    String getFirstName() {
        return first_name;
    }

    /**
     * Returns the department name of the employee
     *
     * @return department_name
     */
    String getDepartment_name() {
        return department_name;
    }

    /**
     * Changes the department to a different one
     *
     * @param department_name New department name
     */
    void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    /**
     * Returns the manager object of the employee
     *
     * @return manager
     */
    EmployeeNode getManager() {
        return manager;
    }

    /**
     * Changes the manager object of the employee
     *
     * @param manager
     *      The new manager object
     */
    void setManager(EmployeeNode manager) {
        this.manager = manager;
    }

    /**
     * Returns an Array List of employees reporting to this employee
     *
     * @return reporters
     */
    ArrayList<EmployeeNode> getReporters() {
        return reporters;
    }

    /**
     * Returns the employee Id of the employee
     *
     * @return employee_number
     */
    private String getEmployee_number() {
        return employee_number;
    }

    /**
     * Returns the employee's manager's employee Id
     *
     * @return manager_employee_number
     */
    String getManager_employee_number() {
        return manager_employee_number;
    }

    /**
     * Changes the employee's manager's employee Id
     *
     * @param manager_employee_number
     *      The new manager_employee_number
     */
    void setManager_employee_number(String manager_employee_number) {
        this.manager_employee_number = manager_employee_number;
    }

    /**
     * Returns the business email of the employee
     *
     * @return email
     */
    private String getEmail() {
        return email;
    }

    /**
     * Returns the former name of the employee
     *
     * @return former_name
     */
    private String getFormerName() {
        return former_name;
    }

    /**
     * Returns the last name of the employee
     *
     * @return last_name
     */
    String getLastName() {
        return last_name;
    }

    /**
     * Returns the middle name  of the employee
     *
     * @return middle_name
     */
    private String getMiddleName() {
        return middle_name;
    }

    /**
     * Returns the preferred name of the employee
     *
     * @return preferred_name
     */
    private String getPreferredName() {
        return preferred_name;
    }

    /**
     * Returns the company of the employee
     *
     * @return company
     */
    private String getCompany() {
        return company;
    }

    /**
     * Returns the title of the employee
     *
     * @return title
     */
    private String getTitle() {
        return title;
    }

    /**
     * Returns the birth day of the employee
     *
     * @return day
     */
    private String getDay() {
        return day;
    }

    /**
     * Returns the sex of the employee
     *
     * @return gender
     */
    private String getGender() {
        return gender;
    }

    /**
     * Returns the location of the employee
     *
     * @return location
     */
    private String getLocation() {
        return location;
    }

    /**
     * Returns the birth month of the employee
     *
     * @return month
     */
    private String getMonth() {
        return month;
    }

    /**
     * Returns the old department of the employee
     *
     * @return old_department_name
     */
    String getOldDepartmentName() {
        return old_department_name;
    }

    /**
     * Returns the payroll department of the employee
     *
     * @return payroll_department
     */
    private String getPayrollDepartment() {
        return payroll_department;
    }

    /**
     * Returns the employee's manager's name as read in the original file
     *
     * @return original_manager_name
     */
    private String getOriginal_manager_name() {
        return original_manager_name;
    }

    /**
     * Returns the name of the employee and their department name for the <code>changeDepartment()</code> method from EmployeeManager class
     *
     * @return String consisting first name, last name and department name
     */
    String info() {
        return this.getFirstName() + " " + this.getLastName() + " (" + this.getDepartment_name() + ")";
    }

    /**
     * Returns basic information about the employee
     *
     * @return Name, Department, Manager's name
     */
    String about()
    {
        return this.getFirstName() + " " + this.getLastName() + " works in " + this.getDepartment_name() + " under " + this.getManager().getName();
    }

    /**
     * Returns all the information about the employee as read in the original file in addition to the old department name.
     *
     * @return String consisting all the attributes
     */
    @Override
    public String toString() {
        if (this.getManager().getName().contains("MANAGER")) {
            return this.getEmployee_number() + "," + this.getFirstName() + "," + this.getLastName() + "," +
                    this.getMiddleName() + "," + this.getFormerName() + ",\"" + this.getPreferredName() + "\"," +
                    this.getEmail() + "," + this.getTitle() + "," + this.getCompany() + "," + this.getPayrollDepartment()
                    + "," + this.getDepartment_name() + "," + this.getOldDepartmentName() + "," + this.getLocation() + "," +
                    this.getMonth() + "," + this.getDay() + "," + this.getGender() + "," + "" + "," + "";
        } else {
            return this.getEmployee_number() + "," + this.getFirstName() + "," + this.getLastName() + "," +
                    this.getMiddleName() + "," + this.getFormerName() + ",\"" + this.getPreferredName() + "\"," +
                    this.getEmail() + "," + this.getTitle() + "," + this.getCompany() + "," + this.getPayrollDepartment()
                    + "," + this.getDepartment_name() + "," + this.getOldDepartmentName() + "," + this.getLocation() + "," +
                    this.getMonth() + "," + this.getDay() + "," + this.getGender() + "," + "\"" + this.getOriginal_manager_name() + "\""
                    + "," + this.getManager_employee_number();
        }
    }
}