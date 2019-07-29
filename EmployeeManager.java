import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
    private static EmployeeNode tempEmployee;
    private static Scanner sc = new Scanner(System.in);
    private static boolean quitRunning = false;

    public static void main(String[] args) {
        System.out.println("Hello and welcome to the Employee Tree Script. Please select what you would like to do from the following menu.");

        while (!quitRunning) {
            menu();
        }
    }

    /**
     * The menu that presents options to manipulate the auction system.
     */
    private static void menu() {
        System.out.println(
                "\nMenu:\n" +
                        "\t(I) - Import Data from .csv file\n" +
                        "\t(F) - Find a employee\n" +
                        "\t(D) - Directory\n" +
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
                loadFile();
                break;

            case ('F'):
                findEmployee();
                break;

            case ('D'): {
                System.out.print("Please enter a name you would like to search for: ");

                lookForEmployee(sc.nextLine());
                break;
            }

            case ('P'): {
                for (EmployeeNode HPValue : employees_name.values()) {
                    if (HPValue.getManager().getName().contains("MANAGER")) {
                        print(HPValue, 4);
                        break;
                    }
                }

                menu();
                break;
            }

            case ('S'):
                saveToCSV();
                break;

            case ('V'):
                viewCSV();
                break;

//            case ('C'): {
//
//                try {
//                    File toMeet = new File("C:/Users/avikkada/Desktop/People to meet.csv");
//                    PrintWriter pw = new PrintWriter(toMeet);
//
//                    pw.println("Name, Email, Title, Department, Replied, When to Meet, How, Added to Calendar");
//
//                    for (EmployeeNode HPValue : employees_name.values()) {
//                        if (HPValue.getTitle().contains("Chief") || HPValue.getTitle().contains("President") || HPValue.getTitle().contains("COO")) {
//                            System.out.println(HPValue.info() + " - " + HPValue.getTitle());
//
//                            pw.println(HPValue.getName() + "," + HPValue.getEmail() + "," + HPValue.getTitle() + "," + HPValue.getDepartment_name());
//
//                            pw.flush();
//                        }
//                    }
//
//                    pw.close();
//                } catch (FileNotFoundException fnfe) {
//                    System.out.println("The file was not found: " + fnfe.toString());
//                }
//
//                menu();
//                break;
//            }

            case ('Q'):
                // determines if the code should keep running
                boolean quitRunning = true;
                System.out.println("Sorry to see you go. Until next time :)");
                break;

            default:
                System.out.println("The option selected is incorrect. Please try again.");
                menu();
                break;
        }
    }

    /**
     * Looks for the name passed in as a parameter within all the names.
     *
     * @param name The name of the person to look for
     * @return The name as it is in the system
     */
    private static String lookForEmployee(String name) {
        String first_name, last_name;

        ArrayList<String> names = new ArrayList<>();

        for (EmployeeNode HPValue : employees_name.values()) {
            if (HPValue.getName().equalsIgnoreCase(name)) {
                if (!names.contains(HPValue.getName())) {
                    names.add(HPValue.getName());
                }
            } else if (name.contains(" ")) {
                first_name = name.substring(0, name.indexOf(" "));
                last_name = name.substring(name.indexOf(" ") + 1);

                if (HPValue.getName().toLowerCase().contains(first_name.toLowerCase())) {
                    if (!names.contains(HPValue.getName())) {
                        names.add(HPValue.getName());
                    }
                } else if (HPValue.getName().toLowerCase().contains(last_name.toLowerCase())) {
                    if (!names.contains(HPValue.getName())) {
                        names.add(HPValue.getName());
                    }
                }
            } else {
                if (HPValue.getFirstName().trim().toLowerCase().contains(name.toLowerCase())) {
                    if (!names.contains(HPValue.getName())) {
                        names.add(HPValue.getName());
                    }
                }

                if (HPValue.getLastName().trim().toLowerCase().contains(name.toLowerCase())) {
                    if (!names.contains(HPValue.getName())) {
                        names.add(HPValue.getName());
                    }
                }
            }
        }

        Collections.sort(names);

        if (names.isEmpty()) {
            System.out.println("There were no names that were similar");
        } else {
            for (String x : names)
                System.out.println(x);
        }

        if (names.size() == 1) {
            return names.get(0);
        }

        menu();

        return "";
    }

    /**
     * Finds an employee and allows you to manage the employee and all of their employees
     */
    private static void findEmployee() {
        System.out.print("Please enter the name of the employee: ");
        String findEmployee = sc.nextLine();

        String findNewEmployee = lookForEmployee(findEmployee);

        if (!findNewEmployee.equals("")) {
            findEmployee = findNewEmployee;
        }

        for (EmployeeNode HPValue : employees_name.values()) {
            if (HPValue.getName().equalsIgnoreCase(findEmployee)) {
                tempEmployee = HPValue;

                System.out.print("Menu:\n" +
                        "\t(P) Print information about this employee\n" +
                        "\t(A) Print information about this employee and all it employees\n" +
                        "\t(C) Change department of this employee and everyone who reports to them\n" +
                        "\t(K) Change department of this employee only\n\n" +
                        "What would you like to do: ");

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
            }
        }

//        System.out.println(findEmployee + " is not in the system.");
    }

    /**
     * Loads the data present in the .csv file
     */
    private static void loadFile() {
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

                EmployeeNode tempManager = new EmployeeNode("TEMPORARY MANAGER", "TEMPORARY MANAGER", null, "-999999999");
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
    private static void print(EmployeeNode node, int spaces) {
        System.out.print("+ ");

        System.out.print(node.info() + "\n");

        for (int i = 0; i < node.getReporters().size(); i++) {
            for (int s = 0; s < spaces; s++) {
                System.out.print("  ");
            }
            print(node.getReporters().get(i), spaces + 4);
        }
    }

    /**
     * Changes the department of the employee and all the other employees reporting to this employee.
     *
     * @param node          The manager
     * @param newDepartment The new department
     */
    private static void changeDepartment(EmployeeNode node, String newDepartment) {

        node.setDepartment_name(newDepartment);

        System.out.println(node.getName() + " used to work for " + node.getOldDepartmentName() + " but now works for " + node.getDepartment_name());

        for (int i = 0; i < node.getReporters().size(); i++) {
            changeDepartment(node.getReporters().get(i), newDepartment);
        }
    }

    /**
     * Saves all the information about all the employees in a .csv file
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
    private static void viewCSV() {
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