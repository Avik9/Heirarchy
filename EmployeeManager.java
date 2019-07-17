import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reads a given file and manages all the employees read from the file.
 *
 * @author Avik Kadakia
 **/

public class EmployeeManager {
    private static HashMap<String, EmployeeNode> employees_name = new HashMap<>();
    private static HashMap<String, String> relations = new HashMap<>();
    private static HashMap<String, EmployeeNode> employees_number = new HashMap<>();
    private static EmployeeNode tempEmployee, tempManager;
    private static Scanner sc = new Scanner(System.in);
    private static boolean quitRunning = false; // determines if the code should keep running

    public static void main(String[] args) {
        System.out.println("Hello and welcome to the Employee Tree Script. Please select what you would like to do from the following menu.");

        while (!quitRunning) {
            menu();
        }
    }

    /**
     * The menu that presents options to manipulate the auction system.
     */
    public static void menu() {
        System.out.println(
                "\nMenu:\n" +
                        "\t(I) - Import Data from .csv file\n" +
                        "\t(F) - Find a employee\n" +
                        "\t(P) - Print All Employees\n" +
                        "\t(S) - Save to .csv file\n" +
                        "\t(V) - View the .csv file\n" +
                        "\t(Q) - Quit");
        runMenu();
    }

    /**
     * Runs the main menu.
     */
    private static void runMenu() {
        sc = new Scanner(System.in);

        System.out.print("\nPlease select an option: ");
        char letter = sc.nextLine().toUpperCase().charAt(0);

        switch (letter) {
            case ('I'):
                loadfile();
                break;

            case ('F'):
                findEmployee();
                break;

            case ('P'):
                print(employees_name.get("Edward Rosenfeld"), 4);
                break;

            case ('S'):
                saveToCSV();
                break;

            case 'V': viewCSV();
                break;

            case ('Q'):
                quitRunning = true;
                System.out.println("Sorry to see you go. Until next time :)");
                break;

            default:
                System.out.println("The option selected is incorrect. Please try again.");
                menu();
                break;
        }
    }

    /**
     * Finds an employee and allows you to manage the employee and all of their employees
     */
    private static void findEmployee() {
        System.out.println("Please enter the name of the employee:");
        String findEmployee = sc.nextLine();

        if (employees_name.containsKey(findEmployee)) {
            tempEmployee = employees_name.get(findEmployee);

            System.out.println("What would you like to do:\n" +
                    "\t(P) Print information about this employee\n" +
                    "\t(A) Print information about this employee and all it employees\n" +
                    "\t(C) Change department of this employee and everyone who reports to them\n" +
                    "\t(K) Change department of this employee only");

            char option = sc.nextLine().toUpperCase().charAt(0);

            switch (option) {

                case 'P':
                    System.out.print(tempEmployee.about());
                    break;

                case 'A':
                    print(tempEmployee, 4);
                    break;

                case 'K': {
                    System.out.println("What do you want as his/her new department?");
                    tempEmployee.setDepartment_name(sc.nextLine());
                }
                break;

                case 'C': {
                    System.out.println("What do you want as their new department?");
                    changeDepartment(tempEmployee, sc.nextLine());
                }
                break;

                default:
                    System.out.println("There is no such option. Please try again");
                    runMenu();
                    break;
            }
        } else {
            System.out.println("This employee is not in the data base. Please try again");

            menu();
        }
    }

    /**
     * Loads the data present in the .csv file
     */
    private static void loadfile() {
        int x = 0;

        try {
            System.out.print("Please write the name of the file: ");
            String filename = sc.nextLine();

            File f = new File(filename);
            sc = new Scanner(f);

            sc.nextLine();
            String currentline, employeeNumber, first_name, last_name, name, middle_name, former_name, preferred_name, email, title, company, department, payroll_department, location, month, day, gender, manager_name, original_manager_name, manager_employeeNumber;

            while (sc.hasNextLine()) {
                x++;

                currentline = sc.nextLine();

                // Getting the employee number

                employeeNumber = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the employee's name

                first_name = name = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                last_name = currentline.substring(0, currentline.indexOf(","));

                name += " " + currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the middle name

                middle_name = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the former name

                former_name = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the preferred name

                if (currentline.substring(0, 5).contains("\"")) {
                    preferred_name = currentline.substring(currentline.indexOf("\""), currentline.indexOf(","));
                    currentline = currentline.substring(currentline.indexOf(",") + 1);
                    preferred_name += "," + currentline.substring(0, currentline.indexOf(","));
                    preferred_name = preferred_name.trim().replace("\"", "");

                    currentline = currentline.substring(2);
                } else {
                    preferred_name = currentline.substring(0, currentline.indexOf(","));
                }

                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the email

                email = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the title

                title = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the company

                company = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the payroll department

                payroll_department = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the department name

                department = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the location name

                location = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the birth month

                month = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the birth day

                day = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the gender

                gender = currentline.substring(0, currentline.indexOf(","));
                currentline = currentline.substring(currentline.indexOf(",") + 1);

                // Getting the manager's name

                if (currentline.substring(currentline.lastIndexOf(",") + 1).length() > 2) {
                    manager_employeeNumber = currentline.substring(currentline.lastIndexOf(",") + 1);
                    original_manager_name = manager_name = currentline.substring(currentline.indexOf("\""), currentline.lastIndexOf("\""));
                    manager_name = manager_name.substring(manager_name.indexOf("\"") + 1);
                    manager_name += " " + manager_name.substring(0, manager_name.indexOf(","));
                    manager_name = manager_name.substring(manager_name.indexOf(",") + 1);

                    if (manager_name.contains("\"")) {

                        currentline = currentline.substring(currentline.indexOf("\"") + 1);
                        name = currentline.substring(0, currentline.indexOf("\""));
                        name += " " + name.substring(0, name.indexOf(","));
                        name = name.substring(name.indexOf(",") + 1);

                        manager_name = manager_name.substring(manager_name.lastIndexOf("\"") + 1);
                        manager_name += " " + manager_name.substring(0, manager_name.indexOf(","));
                        manager_name = manager_name.substring(manager_name.indexOf(",") + 1);
                        manager_name = manager_name.substring(0, manager_name.indexOf(" ")) + manager_name.substring(manager_name.lastIndexOf(" "));
                    }

                    if (!relations.containsKey(manager_name)) {
                        manager_name = manager_name.substring(0, manager_name.indexOf(" ")) + manager_name.substring(manager_name.lastIndexOf(" "));
                    }
                } else {
                    manager_employeeNumber = "-99999999";
                    manager_name = "NO MANAGER";
                    original_manager_name = "";
                }

                tempManager = new EmployeeNode("TEMPORARY MANAGER", "TEMPORARY MANAGER", null, "-999999999");
                tempEmployee = (new EmployeeNode(first_name, last_name, name, middle_name, former_name, preferred_name,
                        email, title, company, department, payroll_department, location, month, day, gender, tempManager,
                        employeeNumber, manager_employeeNumber, original_manager_name));
                tempEmployee.setManager_employee_number(manager_employeeNumber);

                if (employees_name.containsKey(name) && (employees_name.containsKey(manager_name))) {
                    employees_name.get(name).setManager(employees_name.get(manager_name));
                } else {
                    employees_name.put(name, tempEmployee);
                    employees_number.put(employeeNumber, tempEmployee);

                }
                if (employees_name.containsKey(manager_name)) {
                    employees_name.get(manager_name).getReporters().add(tempEmployee);
                    employees_name.get(name).setManager(employees_name.get(manager_name));
                }

                relations.put(name, manager_name);
            }


            int y = 0;

            for (EmployeeNode HPValue : employees_name.values()) {
                if (HPValue.getManager().getName().equals("TEMPORARY MANAGER")) {
                    y++;
                }
            }

            for (EmployeeNode HPValue : employees_name.values()) {
                if (HPValue.getManager().getName().equals("TEMPORARY MANAGER")) {
                    if (relations.containsKey(HPValue.getName())) {
                        if (employees_name.containsKey(relations.get(HPValue.getName()))) {
                            if (relations.get(HPValue.getName()).equals("NO MANAGER")) {
                                tempEmployee = new EmployeeNode("NO MANAGER", "NO MANAGER", null, "-99999999");
                                HPValue.setManager(tempEmployee);
                                tempEmployee.getReporters().add(HPValue);
                                employees_number.put("-99999999", tempEmployee);
                            }

                            HPValue.setManager(employees_number.get(HPValue.getManager_employee_number()));
                            employees_number.get(HPValue.getManager_employee_number()).getReporters().add(HPValue);
                            y--;
                        }

                        if (HPValue.getManager().getName().equals("TEMPORARY MANAGER")) {
                            if (employees_number.containsKey(HPValue.getManager_employee_number())) {
                                HPValue.setManager(employees_number.get(HPValue.getManager_employee_number()));
                                y--;
                            }
                        }
                    }
                }
            }

            if (y > 1)
                System.out.println("Y = " + y);

            System.out.println("The file has been loaded. There are a total of " + x + " employees.");
            relations = null;
        } catch (FileNotFoundException fnfe) {
            System.out.println("The file was not found." + fnfe.toString() + ". Please try again");
            menu();
        }
    }

    /**
     * Prints the tree at the node provided.
     *
     * @param node   The node to print the tree from.
     * @param spaces Spaces to leave horizontally before printing the next leaf
     */
    private static void print(@NotNull EmployeeNode node, int spaces) {
        System.out.print("+ ");

        System.out.print(node.info() + "\n");

        for (int i = 0; i < node.getReporters().size(); i++) {
            for (int s = 0; s < spaces; s++) {
                System.out.print("  ");
            }
            print((EmployeeNode) node.getReporters().get(i), spaces + 4);
        }
    }

    /**
     * Changes the department of the employee and all the other employees reporting to this employee.
     *
     * @param node          The manager
     * @param newDepartment The new department
     */
    private static void changeDepartment(@NotNull EmployeeNode node, String newDepartment) {

        node.setDepartment_name(newDepartment);

        System.out.println(node.getName() + " used to work for " + node.getOldDepartmentName() + " but now works for " + node.getDepartment_name());

        for (int i = 0; i < node.getReporters().size(); i++) {
            changeDepartment((EmployeeNode) node.getReporters().get(i), newDepartment);
        }
    }

    /**
     *  Saves all the information about all the employees in a .csv file
     */
    private static void saveToCSV() {
        System.out.print("Please write the path of the file: ");
        String pathName = sc.nextLine();

        System.out.println("Saving to a .csv file ...");

        File f = new File(pathName);

        try {
            PrintWriter pw = new PrintWriter(f);

            pw.println("Empl ID,First Name,Last Name,Middle Name,Former Name,Preferred Name,Business Email Address,Title,Company,Payroll Department Number,Department,Old Department,Location,Birth Month,Birth Day,Gender,Manager Name,Reports To Manager ID - Job Dta");

            for (String HPKey : employees_name.keySet()) {
                pw.println(employees_name.get(HPKey).toString());
            }
            pw.flush();
            pw.close();

        } catch (FileNotFoundException fe) {
            System.out.println("The file was not found. " + fe.toString());
        }
    }

    /**
     * Opens the .csv file to view.
     */
    public static void viewCSV() {
        System.out.print("Please write the path of the file: ");
        String pathName = sc.nextLine();

        System.out.println("Opening the .csv file ...");

        File f = new File(pathName);

        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }

        try {
            Desktop desktop = Desktop.getDesktop();
            if (f.exists()) {
                desktop.open(f);
            }
        } catch (IOException io) {
            System.out.println("The file could not be opened. " + io.toString());
        }
    }
}