package com.company;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Register {
    Repository repository = new Repository();
    Scanner scan = new Scanner(System.in);

    public Register() throws SQLException {
    }

    public void reg_stdn() throws SQLException {
        System.out.println("enter name");
        String a = scan.next();
        System.out.println("enter family name");
        String b = scan.next();
        System.out.println("enter password");
        String pass = scan.next();
        System.out.println("enter major");
        String c = scan.next();
        repository.insert("student", "(name , family , password ,major)", "('" + a + "' , '" + b + "' , '" + pass + " ' , '" + c + " ' )");
        int ID = repository.read("student", "ID", "name ='" + a + "'");
        System.out.println(ID);
        repository.insert("financial", "(ID , salary )", "(" + ID + " , " + 0 + ")");
        System.out.println("enter birthplace");
        String town = scan.next();
        System.out.println("enter identification number");
        int identification = scan.nextInt();
        System.out.println("enter year of birth");
        int birth_year = scan.nextInt();
        System.out.println("enter phone number");
        String number = scan.next();
        System.out.println("enter postal code");
        String postal_code = scan.next();
        repository.insert("info", " ( ID ,birthplace , identification , birth_year , phone_number , postal_code) ", "(  " + ID + " ,'" + town + "' , " + identification + " , " + birth_year + " , '" + number + "' , '" + postal_code + "'  )");
    }

    public void reg_tchr() throws SQLException {
        System.out.println("enter name");
        String a = scan.next();
        System.out.println("enter family name");
        String b = scan.next();
        System.out.println("enter password");
        String pass = scan.next();
        repository.insert("teacher", "(name , family , password)", "('" + a + "' , '" + b + "' , '" + pass + "')");
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
        int identification = scan.nextInt();
        System.out.println("enter year of birth");
        int birth_year = scan.nextInt();
        System.out.println("enter phone number");
        String number = scan.next();
        System.out.println("enter postal code");
        String postal_code = scan.next();
        repository.insert("info", " ( ID ,birthplace , identification , birth_year , phone_number , postal_code) ", "(  " + ID + " ,'" + town + "' , " + identification + " , " + birth_year + " , '" + number + "' , '" + postal_code + "'  )");

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

    public void reg_emp() throws SQLException {
        System.out.println("enter name");
        String a = scan.next();
        System.out.println("enter family name");
        String b = scan.next();
        System.out.println("enter password");
        String pass = scan.next();
        repository.insert("staff", "(Name , Family , password)", "('" + a + "' , '" + b + "' , '" + pass + " ')");
        int ID = repository.read("staff", "ID", "name ='" + a + "'");
        System.out.println(ID);
        repository.insert("financial", "(ID , salary )", "(" + ID + " , " + 10000000 + ")");
        System.out.println("enter birthplace");
        String town = scan.next();
        System.out.println("enter identification number");
        int identification = scan.nextInt();
        System.out.println("enter year of birth");
        int birth_year = scan.nextInt();
        System.out.println("enter phone number");
        String number = scan.next();
        System.out.println("enter postal code");
        String postal_code = scan.next();
        repository.insert("info", " ( ID ,birthplace , identification , birth_year , phone_number , postal_code) ", "(  " + ID + " ,'" + town + "' , " + identification + " , " + birth_year + " , '" + number + "' , '" + postal_code + "'  )");

    }

    public void edit(int ID) throws SQLException {
        System.out.println("enter phone number");
        String number = scan.next();
        System.out.println("enter postal code");
        String postal_code = scan.next();
        repository.change("info", " phone_number = '" + number + "' , postal_code = '" + postal_code + "'", "ID", ID);
    }

    public void login(int ID, String Pass, int code) throws SQLException {
        if (code == 1) {
            if (Pass.equals(repository.read("student", "password", "ID ='" + ID + "'"))) {
                System.out.println("login successful");
            } else {
                System.out.println("username or password is incorrect");
            }
        } else if (code == 2) {
            if (Pass.equals(repository.read("teacher", "password", "ID ='" + ID + "'"))) {
                System.out.println("login successful");
            } else {
                System.out.println("username or password is incorrect");
            }
        } else if (code == 3) {
            if (Pass.equals(repository.read("staff", "password", "ID ='" + ID + "'"))) {
                System.out.println("login successful");
            } else {
                System.out.println("username or password is incorrect");
            }
        }

    }
}
