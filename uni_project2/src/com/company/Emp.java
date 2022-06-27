package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Emp {
    Scanner scan = new Scanner(System.in);
    Repository repository = new Repository();
    int ID = 100003;
    public Emp() throws SQLException {
    }

    public void Leave(int ID) throws SQLException {
        int a = 0;
        a = repository.read("staff", "Tleave", "ID = "+ ID);
        if (a > 0)
        {
            repository.change("staff", "Tleave = Tleave - 1", "ID", ID);
        System.out.println(a);}
        else{/*error*/}
    }


}
