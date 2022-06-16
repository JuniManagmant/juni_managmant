package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Emp {
    Scanner scan = new Scanner(System.in);
    Repository repository = new Repository();
    int ID = 100003;
    public Emp() throws SQLException {
    }

    public void Leave() throws SQLException {
        int a = 0;
        a = repository.read("staff", "Tleave", "ID = "+ ID);
        if (a > 0)
        {
            repository.change("staff", "Tleave = Tleave - 1", "ID", ID);
        System.out.println(a);}
        else{/*error*/}
    }
    public void req_loan(){
        System.out.println("enter loan amount");
        int loan_amount = scan.nextInt();
        if(loan_amount < 1000000000){
        repository.change("financial", "debt = debt - "+ loan_amount*1.2 , "ID" , ID);
    }
        else {System.out.println("loan too big");}
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
    public void edit_profile() throws SQLException {
        Register register = new Register();
        register.edit(ID);
    }
}
