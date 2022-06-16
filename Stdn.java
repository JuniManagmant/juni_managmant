package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Scanner;

public class Stdn {

    Scanner scan = new Scanner(System.in);
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    Repository repository = new Repository();
     int ID = 14011023;

    public Stdn() throws SQLException {
    }


    public void unit_elimination(int X) throws SQLException {

        repository.update("main_table", "status", "ID", X, 1);
    }


    //Financial a = new Financial();

    public void Leave() throws SQLException {
        int leave_status;
        int a = 0;
        a = repository.read("student", "Tleave", "ID = "+ ID);
        if (a == 0);{
        repository.update("juni_project.student", "Tleave", "ID", 1, ID);
    }}
    public void manage_balance(){

    }

    public void req_loan(){
        System.out.println("enter loan amount");
        int loan_amount = scan.nextInt();
        /*loan amount check*/
        repository.change("financial", "debt = debt - "+ loan_amount , "ID" , ID);
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


}/*





    public void reportCard(){

    }




        catch(Exception e){
            System.out.println(e);
        }


    }}*/

