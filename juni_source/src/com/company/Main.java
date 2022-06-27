package com.company;
import javax.swing.*;
import java.sql.SQLException;
public class Main {
    public Main() {}
    public static void main(String[] args) throws SQLException {
        JFrame mainFrame = new JFrame("mainWindow");
        CommonPages.login(mainFrame);


    }
}