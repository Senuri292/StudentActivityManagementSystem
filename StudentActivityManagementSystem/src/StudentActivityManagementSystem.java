//Import java io and util packages
import java.io.*;
import java.util.*;

public class StudentActivityManagementSystem {
    static Scanner input = new Scanner(System.in);

    //create new 2D array registration to store student registration details
    static String[][] registration = new String[100][2];

    //create new 2D array studentMarks to store student marks
    static String[][] studentMarks = new String[100][5];

    public static void main(String[] args) {
        int choice;

        //call the createFile method to create a new .txt file to store student registration array
        createFile("studentDetails.txt");
        // load the existing array to registration variable
        registration = load();
        boolean value = true;
        while(value) {
            try {
                //do while loop to print the user interaction menu and call relevant methods based on users choice
                do {
                    // print menu after every function until user input "0"
                    System.out.println("Menu:");
                    System.out.println("1. Check available seats");
                    System.out.println("2. Register student (with ID)");
                    System.out.println("3. Delete student");
                    System.out.println("4. Find student (with student ID)");
                    System.out.println("5. Store student details into a file");
                    System.out.println("6. Load student details from the file to the system");
                    System.out.println("7. View the list of students based on their names");
                    System.out.println("8. Student marks related Options");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Number of available seats = " + seats());
                            value = false;
                            break;
                        case 2:
                            registration = register();
                            System.out.println("Student registered successfully!");
                            value = false;
                            break;
                        case 3:
                            registration = delete();
                            value = false;
                            break;
                        case 4:
                            find();
                            value = false;
                            break;
                        case 5:
                            store("studentDetails.txt", registration);
                            value = false;
                            break;
                        case 6:
                            registration = load();
                            System.out.println("Student Details:");
                            //print student details array in a presentable way
                            for (String[] row : registration) {
                                if (row[0] != null) {
                                    for (String element : row) {
                                        if (element != null) {
                                /*
                                for a pair of values in registration,
                                 if the values are not null print the two elements with a space in between.
                                 */
                                            System.out.println(element + " ");
                                        }
                                    }
                                    System.out.println(" ");
                                }
                            }
                            value = false;
                            break;
                        case 7:
                            System.out.println("Sorted student details: ");
                            sort();
                            value = false;
                            break;
                        case 8:
                            optionEight();
                            value = false;
                            break;
                        case 0:
                            System.out.println("Exiting the Program......");
                            value = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice != 0);
            }
            // if user inputs invalid data types
            catch (InputMismatchException e) {
                System.out.println("Invalid Data Type! Enter again.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("ERROR!");
            }
        }
    }

    //method to create new files
    public static void createFile(String fileName){
        try{
            File file = new File(fileName);

            //if the file got created successfully status will say True
            boolean status = file.createNewFile();

            if (status){
                System.out.println(file.getName() + " has been created successfully!");
            } else{

                //if file already exist let user know
                if (file.exists()){
                    System.out.println(file.getName() + " loaded......");
                } else{
                    System.out.println("ERROR!");
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // method to handle errors when getting a string input from the user
    public static String inputErrorHandleString(String message){
        // variable to store string input
        String text = "";
        // boolean variable to change while loop
        boolean value = true;

        // loop to get input again if there's an error
        while(value) {
            try {
                System.out.print(message);
                text = input.next();
                value = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Data Type! Enter again: ");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("ERROR!");
                break;
            }
        }
        return text;
    }

    // method to handle errors when getting a double input from the user
    public static double inputErrorHandleDouble(String message){
        double num = 0;
        boolean value = true;

        // loop to get input again if there's an error
        while(value) {
            try {
                System.out.print(message);
                num = input.nextDouble();

                // making sure marks are in between 0 and 100
                if((num >= 0) && (num <= 100)) {
                    value = false;
                }
                else{
                    System.out.println("ERROR!! Marks have to be between 0 and 100");
                    input.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid Data Type! Enter again: ");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("ERROR!");
                break;
            }
        }
        return num;
    }

    // method to calculate available space for new students
    public static int seats(){
        //variable to store available number of space
        int count = 0;

        // iterating through the array
        for (int i = 0; i < 100; i++) {
            // if the first value of the second array equals to null
            if (registration[i][0] == null) {
                count++;
            }
        }
        return count;
    }
    // method to create a new student and output the registration array with new student
    public static String[][] register(){
        // variable to store the number of students already registered
        int regNum = 100 - seats();
        // variables to store student ID
        String stuNo;
        String stuID  = "";
        boolean value = true;

        while(value) {
            //get 7 number ID from user
            stuNo = inputErrorHandleString("Enter Student ID(without 'w'): ");

            // check if the given ID has 7 numbers
            if(stuNo.length() == 7){
                // add "w" in front of ID given by user
                stuID = "w"+stuNo;

                // loop to check if the ID already exists
                for(int i = 0; i < 100; i++){
                    String id = registration[i][0];
                    // if the ID already exists inform the user
                    if(stuID.equalsIgnoreCase(id)){
                        System.out.println("Student ID already exist. Please Try again.");
                    }
                    //if ID has 7 numbers and doesn't exist end the while loop
                    else{
                        value = false;
                    }
                }
            }
            // if ID doesn't have 7 numbers inform user
            else{
                System.out.println("Student ID must have 7 Numbers! Please try again.");
            }
        }
        // getting Student name from the user
        String stuName = inputErrorHandleString("Enter Student Name: ");

        // storing student name and student ID in registration array
        registration[regNum][0] = stuID;
        registration[regNum][1] = stuName;

        return registration;
    }

    //method to delete a student from registration array and output the updated array
    public static String[][] delete(){
        registration = load();
        boolean found = false;
        boolean value = true;

        while (value) {
            // get ID of the student user wants to delete
            String delId = inputErrorHandleString("Enter Student ID you want to Delete: ");

            // search the given ID in the registration array
            for (int i = 0; i < 100; i++) {
                String stuID = registration[i][0];
                if (stuID != null ) {
                    if (stuID.equalsIgnoreCase(delId)) {
                        // if the given ID is in registration array change the ID and Name to null
                        registration[i][0] = null;
                        registration[i][1] = null;
                        System.out.println("Student Deleted Successfully " + stuID);
                        found = true;
                        //if the ID exist end the while loop
                        value = false;
                        break;
                    }
                }
            }
            // if ID was not found inform the user
            if (!found) {
                System.out.println("Student ID doesn't exist! Please try again");
            }
        }
        // remove the null values in the middle
        registration = removeNull();

        return registration;
    }
    // method to remove the null values in the middle
    public static String[][] removeNull(){

        int lastNonNull = 0;

        //loop to iterate through registration array
        for (int i = 0; i < 100; i++) {
            if (registration[i] != null && registration[i][1] != null) {
                if (i != lastNonNull) {
                    // if the value is not null and not the last nonnull value switch places with the value next to it
                    String[] temp = registration[lastNonNull];
                    registration[lastNonNull] = registration[i];
                    registration[i] = temp;
                }
                // if the value is not null add 1 to lastNonNull
                lastNonNull++;
            }
        }
        return registration;
    }

    // method to find the student details when the user entered the ID
    public static void find(){
        registration = load();
        boolean found = false;
        boolean value = true;

        while (value) {
            String findId = inputErrorHandleString("Enter Student ID you want to Find: ");
            for (int i = 0; i < 100; i++) {
                String stuID = registration[i][0];
                // this only check already registered values and skip through non values
                if (stuID != null) {
                    if (stuID.equalsIgnoreCase(findId)) {
                        // if the ID user gave is in the registration list print Student ID, Student Name and index
                        System.out.println("Student ID    : " + stuID);
                        System.out.println("Student Name  : " + registration[i][1]);
                        System.out.println("Student Index : " + (i + 1));
                        found = true;
                        //if the ID exist end the while loop
                        value = false;
                        break;
                    }
                }
            }
            // if ID was not found inform the user
            if (!found) {
                System.out.println("Student ID doesn't exist! Please try again");
            }
        }
    }

    // method to store registration array to a file
    public static void store(String fileName, String[][] array){
        try {
            FileWriter file = new FileWriter(fileName);
            // save the array to the file
            file.write(Arrays.deepToString(array));
            file.close();
            System.out.println("Student details stored successfully.");
        }
        catch (IOException e) {
                e.printStackTrace();
        }
    }

    // method to load registration array from the file and output the array
    public static String[][] load() {
        // new array to hold the values temporary
        String[][] array = new String[100][2];

        try {
            // read file using Scanner
            Scanner scanner = new Scanner(new File("studentDetails.txt"));
            // Read the entire content
            String content = scanner.nextLine();
            // Remove outer brackets of the array
            content = content.substring(1, content.length() - 1);
            // splits the content whenever there's a "[]" , "," or " "
            String[] pairs = content.split("\\],\\s*\\[");
            for (int i = 0; i < pairs.length; i++) {
                // filter only the student id and student number to the new array without "[]" and "," and " "
                String[] pair = pairs[i].replaceAll("\\[|\\]", "").split(",");
                array[i][0] = pair[0].trim();
                array[i][1] = pair.length > 1 ? pair[1].trim() : "";
            }
        // if the file not found inform the user
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        // loop to convert the null values that has converted to String back to null
        for (int i = 0; i < 100; i++) {
            if (array[i][0].equals("null")) {
                array[i][0] = null;
                array[i][1] = null;
            }
        }
        return array;
    }

    // method to sort the student Names in the alphabetical order
    public static void sort() {
        // new array to hold the values of registration array temporary
        String[][] sorted = new String[100][2];

        //copy the values of registration to the new sorted array
        for (int i = 0; i < 100; i++) {
            sorted[i] = registration[i].clone();
        }

        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 100 - i - 1; j++) {
                if (sorted[j][1] != null && sorted[j+1][1] != null) {
                    // if the values not equals to null compare values to the values next to it
                    if (sorted[j][1].compareToIgnoreCase(sorted[j + 1][1]) > 0) {
                        /*
                        if the left value should come after the right value,
                        store the left value to temp variable and switch the right value to left
                        */
                        String[] temp = sorted[j];
                        sorted[j] = sorted[j + 1];
                        sorted[j + 1] = temp;
                    }
                }
            }
        }
        // remove the null values and print the student details in the sorted order
        System.out.println("Student Sorted List:");
        for (int i = 0; i < 100; i++) {
            if(sorted[i][0] != null) {
                System.out.println(sorted[i][0] + "  " + sorted[i][1]);
            }
        }
    }

    //method to print the second menu and get the user's choice and run the relevant method
    public static void optionEight(){
        char choice;
        //create a new file to store Student details and marks
        createFile("studentMarks.txt");

        do {
            // print menu after every function until user input "e"
            System.out.println("a. Add student name");
            System.out.println("b. Add module marks");
            System.out.println("c. Generate summary");
            System.out.println("d. Generate report");
            System.out.println("e. Enter main menu");
            System.out.print("Enter your choice: ");
            // convert the users choice o lowercase
            choice = input.next().toLowerCase().charAt(0);

            switch (choice) {
                case 'a':
                    addStudentName();
                    break;
                case 'b':
                    addModuleMarks();
                    break;
                case 'c':
                    summary();
                    break;
                case 'd':
                    report();
                    break;
                case 'e':
                    System.out.println("Entering the main menu......");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'e');
    }

    // method to find the index of the name user entered from the registration array
    public static int findIndex(String name) {
        int index = 0;
        boolean found = true;

        for (int i = 0; i < 100; i++) {
            String regName = registration[i][1];
            if (name.equalsIgnoreCase(regName)) {
                // if the ID exist return the first index of the name
                index = i;
                found = false;
            }
        }
        if (found) {
            // if the name not found inform the user and set index to -1
            System.out.println("Incorrect Name! Please try again.");
            index = -1;
        }
        return index;
    }

    //method to load the studentMarks File into the array
    private static void loadFile(){
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("studentMarks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null && count < 100) {
                String[] parts = line.split(",");
                //while the values isn't null call the values from file to studentMarks array
                if (parts.length == 5) {
                    studentMarks[count][0] = parts[0];
                    studentMarks[count][1] = parts[1];
                    studentMarks[count][2] = parts[2];
                    studentMarks[count][3] = parts[3];
                    studentMarks[count][4] = parts[4];

                    count++;
                }
            }
        }
        // catch IOException
        catch (IOException e) {
            System.out.println("Error loading module marks: " + e.getMessage());
        }
    }

    // method to store studentMarks array to the file
    private static void storeFile() {
        try (FileWriter writer = new FileWriter("studentMarks.txt")) {
            for (int i = 0; i < 100; i++) {
                // write values one by one separated by a ","
                writer.write(studentMarks[i][0] + "," + studentMarks[i][1] + "," + studentMarks[i][2] + "," + studentMarks[i][3] + "," + studentMarks[i][4]);
                writer.write("\n");
            }
            System.out.println("Module marks saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving module marks: " + e.getMessage());
        }
    }
    // method to search student name from the registration array and add to the studentMarks array
    public static void addStudentName() {
        loadFile();
        int index = -1;
        String stuName = " ";

        boolean value = true;
        // loop to get the input again if an error happens
        while (value) {
            stuName = inputErrorHandleString("Enter Student Name(from the registration list): ");
            // get the index of the name
            index = findIndex(stuName);
            if(index != -1){
                // if the index exist end the loop
                value = false;
            }
        }
        String stuID = registration[index][0];
        // add the stuID and stuName to Student class
        Student student = new Student(stuID, stuName);
        // add the stuID and stuName to studentMark array
        studentMarks[index][0] = stuID;
        studentMarks[index][1] = stuName;

        System.out.println("System ready to store marks for the student. Head over to Option b");

        storeFile();
    }
    // method to add module marks to a selected students
    public static void addModuleMarks(){
        loadFile();
        int index = -1;
        boolean value = true;

        while (value) {
            // get the students name that the marks belong to
            String stuName = inputErrorHandleString("Enter Student Name(from the registration list): ");
            index = findIndex(stuName);
            if (index != -1) {
                value = false;
            }
        }

        // get the marks from the user
        double mark1 = inputErrorHandleDouble("Enter the Module Mark 1 for the student: ");
        double mark2 = inputErrorHandleDouble("Enter the Module Mark 2 for the student: ");
        double mark3 = inputErrorHandleDouble("Enter the Module Mark 3 for the student: ");

        // add the marks to the studentMarks array
        studentMarks[index][2] = String.valueOf(mark1);
        studentMarks[index][3] = String.valueOf(mark2);
        studentMarks[index][4] = String.valueOf(mark3);

        System.out.println("Marks added successfully!");

        storeFile();
    }
    // method to print the summary of the system
    public static void summary(){
        loadFile();
        // calculate the total number of the students registered
        int numOfReg = 100 - seats();

        int count = 0;
        for (int i = 0; i < numOfReg; i++) {
            if(studentMarks[i][0] != null && !(studentMarks[i][0]).isEmpty()) {
                // if the values are not null convert the marks again to double and count the number of passes
                double mark1 = Double.parseDouble(studentMarks[i][2]);
                double mark2 = Double.parseDouble(studentMarks[i][3]);
                double mark3 = Double.parseDouble(studentMarks[i][4]);
                if ((mark1 > 40) && (mark2 > 40) && (mark3 > 40)) {
                    count++;
                }
            }
        }
        // print the summary in a presentable way
        System.out.println("-----------------SUMMARY--------------------");
        System.out.println("Total Number of Registrations: "+numOfReg);
        System.out.println("Number of students who passed all modules: " + count);
        System.out.println("---------------------------------------------");

    }
    // method to print the student reports
    public static void report(){
        loadFile();
        int numOfStudents = 100 - seats();
        double mark1 = 0, mark2 = 0, mark3 = 0;
        System.out.println("----------------REPORT-----------------");

        try {
            // iterate over students one by one
            for (int i = 0; i < numOfStudents; i++) {
                // access the marks of the student
                if (studentMarks[i][2] != null && !studentMarks[i][2].trim().isEmpty()) {
                    mark1 = Double.parseDouble(studentMarks[i][2]);
                    mark2 = Double.parseDouble(studentMarks[i][3]);
                    mark3 = Double.parseDouble(studentMarks[i][4]);

                    // set the marks to the Module class
                    Double[] moduleMarks;
                    String moduleGrade = null;
                    Module marks = new Module(studentMarks, moduleGrade);
                    marks.setModuleMarks(studentMarks);

                    // calculate the total mark
                    double total = mark1 + mark2 + mark3;
                    // calculate the average
                    double avg = total / 3;
                    // calculate the grade using average
                    if (avg >= 80) {
                        // set the grade to the Module class
                        marks.setModuleGrade("Distinction");

                    } else if (avg >= 70) {
                        marks.setModuleGrade("Merit");

                    } else if (avg >= 40) {
                        marks.setModuleGrade("Pass");

                    } else {
                        marks.setModuleGrade("Fail");
                    }
                    // print the report to the user
                    System.out.println("Student ID: " + studentMarks[i][0]);
                    System.out.println("Student Name: " + studentMarks[i][1]);
                    System.out.println(" ");
                    System.out.println("Module 1 Mark: " + studentMarks[i][2]);
                    System.out.println("Module 2 Mark:" + studentMarks[i][3]);
                    System.out.println("Module 3 Mark:" + studentMarks[i][4]);
                    System.out.println(" ");
                    System.out.println("Total marks: " + total);
                    System.out.println("Average Marks: " + avg);
                    System.out.println("Grade: " + marks.getModuleGrade());
                    System.out.println("-------------------------------------");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("------------END OF REPORT------------");
    }
}