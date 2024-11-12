import java.util.Scanner;

public class Module {
    static Scanner input = new Scanner(System.in);

    // 2D array to store module marks
    private String[][] moduleMarks;
    // variable to store module grade
    private String moduleGrade;

    // constructor for the Module class
    public Module(String[][] moduleMarks, String moduleGrade){
        this.moduleMarks = moduleMarks;
        this.moduleGrade = moduleGrade;
    }

    // getter for moduleMarks
    public String[][] getModuleMarks() {
        return moduleMarks;
    }

    // setter for moduleMarks
    public void setModuleMarks(String[][] moduleMarks) {
        this.moduleMarks = moduleMarks;
    }

    // getter for moduleGrade
    public String getModuleGrade() {
        return moduleGrade;
    }

    // setter for moduleGrade
    public void setModuleGrade(String moduleGrade) {
        this.moduleGrade = moduleGrade;
    }
}
