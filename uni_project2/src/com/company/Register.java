package com.company;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;
import javax.swing.*;

public class Register {
    Repository repository = new Repository();
    Scanner scan = new Scanner(System.in);

    public Register() throws SQLException {
    }
    static void validate() throws WrongpassException{
        throw new WrongpassException("password is incorrect") ;   }
    public void reg_stdn(String a, String b, String c , String town , String number , String postal_code , String identification , int pass , int birth_year  ) throws SQLException {

        repository.insert("student", "(name , family , passC ,major)", "('" + a + "' , '" + b + "' , " + pass + "  , '" + c + " ' )");
        int ID = repository.read("student", "ID", "name ='" + a + "' AND family ='" + b + "'");
        repository.insert("financial", "(ID , salary )", "(" + ID + " , " + 0 + ")");
        repository.insert("info", " ( ID ,birthplace , identification , birth_year , phone_number , postal_code) ", "(  " + ID + " ,'" + town + "' , '" + identification + "' , " + birth_year + " , '" + number + "' , '" + postal_code + "'  )");
        repository.StudentCourseCreator(ID);
    }

    public void reg_tchr() throws SQLException {
        System.out.println("enter name");
        String a = scan.next();
        System.out.println("enter family name");
        String b = scan.next();
        System.out.println("enter password");
        int pass = scan.nextInt();
        repository.insert("teacher", "(name , family , passC)", "('" + a + "' , '" + b + "' , " + pass + ")");
        int ID = repository.read("teacher", "ID", "name ='" + a + "'");
        System.out.println(ID);
        repository.insert("financial", "(ID , salary )", "(" + ID + " , " + 20000000 + ")");
        System.out.println("enter course count");
        int c = scan.nextInt();
        for (int i = 0; i < c; i++) {
            System.out.println("enter course ID");
            int D = scan.nextInt();
            int E = repository.read("courses", "req", "ID =" + D);
            repository.insert(" teacher_course ", "( teacher_id , course_id , req_id )", " ( " + ID + " , " + D + " , " + E + " ) ");
        }
        System.out.println("enter birthplace");
        String town = scan.next();
        System.out.println("enter identification number");
        String  identification = scan.next();
        System.out.println("enter year of birth");
        int birth_year = scan.nextInt();
        System.out.println("enter phone number");
        String number = scan.next();
        System.out.println("enter postal code");
        String postal_code = scan.next();
        repository.insert("info", " ( ID ,birthplace , identification , birth_year , phone_number , postal_code) ", "(  " + ID + " ,'" + town + "' , '" + identification + "' , " + birth_year + " , '" + number + "' , '" + postal_code + "'  )");

    }

    public void reg_course() {
        System.out.println("enter course name");
        String a = scan.next();
        System.out.println("enter unit count");
        int b = scan.nextInt();
        System.out.println("enter requirement");
        int c = scan.nextInt();
        repository.insert("courses", "(Name , Units, req)", "('" + a + "' ," + b + "," + c + " )");
    }

    public void reg_emp(String a,String b, int pass , String town , String identification , int birth_year , String number , String postal_code) throws SQLException {



        repository.insert("staff", "(Name , Family , passC)", "('" + a + "' , '" + b + "' , " + pass + " )");
        int ID = repository.read("staff", "ID", "name ='" + a + "' AND family ='" + b + "'");
        repository.insert("financial", "(ID , salary )", "(" + ID + " , " + 10000000 + ")");
        repository.insert("info", " ( ID ,birthplace , identification , birth_year , phone_number , postal_code) ", "(  " + ID + " ,'" + town + "' , '" + identification + "' , " + birth_year + " , '" + number + "' , '" + postal_code + "'  )");

    }

    public void editS(int ID , int oldpass , int newpass , String number , String postal_code ) throws SQLException {
        System.out.println("enter old password");

        if (oldpass == repository.read(" student ", " passC ", " ID = " + ID)) {
            System.out.println("enter new pass");
        }
        else{
            System.out.println("password incorrect");
        }

        repository.change("student" , "passC = "+ newpass , "ID" , ID);
            System.out.println("password reset!");
        System.out.println("enter phone number");

        System.out.println("enter postal code");

        repository.change("info", " phone_number = '" + number + "' , postal_code = '" + postal_code + "'", "ID", ID);
    }
    public void editT(int ID , int oldpass , int newpass , String number , String postal_code ) throws SQLException {
        System.out.println("enter old password");

        if (oldpass == repository.read(" teacher ", " passC ", " ID = " + ID)) {
            System.out.println("enter new pass");
        }
        else{
            System.out.println("password incorrect");
        }

        repository.change("teacher" , "passC = "+ newpass , "ID" , ID);
        System.out.println("password reset!");
        System.out.println("enter phone number");

        System.out.println("enter postal code");

        repository.change("info", " phone_number = '" + number + "' , postal_code = '" + postal_code + "'", "ID", ID);
    }
    public void editE(int ID , int oldpass , int newpass , String number , String postal_code ) throws SQLException {
        System.out.println("enter old password");

        if (oldpass == repository.read(" staff ", " passC ", " ID = " + ID)) {
            System.out.println("enter new pass");
        }
        else{
            System.out.println("password incorrect");
        }

        repository.change("staff" , "passC = "+ newpass , "ID" , ID);
        System.out.println("password reset!");
        System.out.println("enter phone number");

        System.out.println("enter postal code");

        repository.change("info", " phone_number = '" + number + "' , postal_code = '" + postal_code + "'", "ID", ID);
    }
    JFrame mainFrame = new JFrame("main window");

    public void login(int ID, int Pass, int code) throws SQLException, WrongpassException {

        if (code == 1) {
            if (Pass == repository.read(" student ", " passC ", " ID = " + ID)) {
                System.out.println("login successful");

                functions.menuBar.student(mainFrame , ID);
                String name = repository.readname("student" , "name", "ID = "+ ID);

                functions.header.showHeader(mainFrame, name  , ID);
                teacherPages.mainPage(mainFrame,ID);

            } else {
                validate();
            }
        } else if (code == 2) {
            if (Pass == (repository.read("teacher", "passC", "ID = " + ID ))) {
                System.out.println("login successful");

                functions.menuBar.teacher(mainFrame , ID);
                String name = repository.readname("teacher" , "name", "ID = "+ ID);

                functions.header.showHeader(mainFrame, name, ID);
                teacherPages.mainPage(mainFrame,ID);
            } else {
                validate();
            }
        } else if (code == 3) {
            if (Pass == (repository.read("staff", "passC", "ID = " + ID ))) {
                System.out.println("login successful");
                functions.menuBar.staff(mainFrame , ID);
                String name = repository.readname("staff" , "name", "ID = "+ ID);

                functions.header.showHeader(mainFrame, name , ID);
                teacherPages.mainPage(mainFrame,ID);
            } else {
                validate();
            }
        }

    }
}
