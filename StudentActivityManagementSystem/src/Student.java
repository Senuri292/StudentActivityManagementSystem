import java.util.Scanner;

public class Student {
    static Scanner input = new Scanner(System.in);

    public String getstuName;
    public String getstuID;

    // variable to store student ID and student name
    private String stuID;
    private String stuName;

    // constructor for the Student class
    public Student(String stuID, String stuName) {
        this.stuID = stuID;
        this.stuName = stuName;
    }

    // getter for stuID
    public String getstuID() {
        return stuID;
    }

    // setter for stuID
    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    // getter for stuName
    public String getstuName() {
        return stuName;
    }

    // setter for stuName
    public void setstuName(String stuName) {
        this.stuName = stuName;
    }


}
