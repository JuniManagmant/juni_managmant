package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Tchr {
    Repository repository = new Repository();
    Scanner scan = new Scanner(System.in);
    public Tchr() throws SQLException {
    }
    int ID ;
    public void Leave(int ID) throws SQLException {
        int a = 0;
        a = repository.read("teacher", "Tleave", "ID = "+ ID);
        if (a > 0)
        {
            repository.change("teacher", "Tleave = Tleave - 1", "ID", ID);
            System.out.println(a-1);}
        else{/*error*/}
    }

    public void financial() throws SQLException {
        int a = repository.read("financial" , "debt" , "ID =" + ID);
        System.out.println("your balance is :" + a);
    }
    public void pay_debt(){
        System.out.println("enter loan amount");
        int loan_amount = scan.nextInt();
        repository.change("financial", "debt = debt + "+ loan_amount , "ID" , ID);
    }

    public void show_courses() throws SQLException {
    System.out.println(repository.intoarray(" course_id ", " teacher_course " , " teacher_id " , ID));
    }
    public int student_counter() throws SQLException {
        return(repository.counter("student_id" , "main_table" , "teacher_id", ID));
    }
    public void show_students() throws SQLException {

    }

}
