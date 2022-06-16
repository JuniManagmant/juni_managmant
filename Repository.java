package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

public class Repository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Repository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:1522/juni_project" , "root", "12345679");
        connection.setAutoCommit(false);
    }

    public void showName(String A , String B) throws SQLException{
        preparedStatement = connection.prepareStatement("select "+ A + " from " + B + ";" );
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
    public void change(String Tname , String Cname_command ,String IDname ,  int C )  {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE "+ Tname + " SET " + Cname_command + " WHERE "+ IDname + " = ? ;");
            preparedStatement.setInt(1,C);
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            System.out.println( " Could not update data to the database " + e.getMessage());
        }}
    public void update(String Tname , String Cname ,String IDname ,  int C , int D)  {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE "+ Tname + " SET " + Cname + " = ? WHERE "+ IDname + " = ? ;");
            preparedStatement.setInt(1,C);
            preparedStatement.setInt(2, D);
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            System.out.println( " Could not update data to the database " + e.getMessage());
        }}
    public void insert (String Tname , String Cnames ,String Cvalues )  {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+ Tname + Cnames + " VALUES " + Cvalues + " ;");
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            System.out.println( " Could not update data to the database " + e.getMessage());
        }}
    public int read (String Tname , String Cname, String Condition) throws SQLException{
        int a = 0 ;
        preparedStatement = connection.prepareStatement("select "+ Cname + " from " + Tname + " WHERE " + Condition + ";" );
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
         a = resultSet.getInt(1);}
        return(a);
    }
    public ArrayList<String> intoarray(String Cname , String Tname, String idname , int id) throws SQLException{
        ArrayList<String> count = new ArrayList<String>();
        preparedStatement = connection.prepareStatement("select "+ Cname + " from " + Tname + " WHERE " + idname +" = "+ id +" ;" );
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
          count.add(resultSet.getString(1));
        }
        return(count);
    }
    public int counter(String Cname , String Tname, String idname , int id) throws SQLException{
        int count = 0;
        preparedStatement = connection.prepareStatement("select "+ Cname + " from " + Tname + " WHERE " + idname +" = "+ id +" ;" );
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            count+=1;
        }
        return(count);
    }
    //____________________________________________________________________________________________________________
    //____________________________________________________________________________________________________________
    //____________________________________________________________________________________________________________

    public int CourseCounter() throws SQLException {
        int a = 0;
        this.preparedStatement = this.connection.prepareStatement("select count(ID) from courses");

        for(this.resultSet = this.preparedStatement.executeQuery(); this.resultSet.next(); a = this.resultSet.getInt(1)) {
        }

        return a;
    }
    public int[] CourseCrawler() throws SQLException {
        int i = 0;
        int[] crawler = new int[this.CourseCounter()];
        this.preparedStatement = this.connection.prepareStatement("select ID from courses");

        for(this.resultSet = this.preparedStatement.executeQuery(); this.resultSet.next(); ++i) {
            crawler[i] = this.resultSet.getInt(1);
        }

        return crawler;
    }

    public void StudentCourseCreator(int StudentID) throws SQLException {
        int[] a = this.CourseCrawler();

        for(int i = 1; i <= this.CourseCounter(); ++i) {
            Connection var10001 = this.connection;
            String var10002 = Long.toString((long)StudentID);
            this.preparedStatement = var10001.prepareStatement("insert into student_course (student_id, course_id, status) values (" + var10002 + "," + Integer.toString(a[i - 1]) + ",0)");
            this.preparedStatement.executeUpdate();
            this.connection.commit();
        }

        this.preparedStatement = this.connection.prepareStatement("update student_course set status=1 where course_id=0");
        this.preparedStatement.executeUpdate();
        this.connection.commit();
    }

    public void UnitSelection(int StID, int ID, int TermID) throws SQLException {
        Connection var10001 = this.connection;
        String var10002 = Integer.toString(StID);
        this.preparedStatement = var10001.prepareStatement(" select distinct co.id , tea.id  from teacher_course tc join student_course sc on tc.req_id=sc.course_id AND sc.status=1 AND sc.student_id=" + var10002 + " join student_course sc1 on tc.course_id=sc1.course_id AND sc1.status!=1 and sc1.student_id=" + Integer.toString(StID) + " join courses co on tc.course_id=co.id join teacher tea on tc.teacher_id = tea.ID  where tc.id=" + Integer.toString(ID));
        this.resultSet = this.preparedStatement.executeQuery();

        while(this.resultSet.next()) {
            String[] dataTable = new String[]{String.valueOf(this.resultSet.getInt(2)), String.valueOf(this.resultSet.getInt(1))};
            this.preparedStatement = this.connection.prepareStatement("insert into main_table (teacher_id , student_id, course_id, termID) values (" + dataTable[0] + "," + Integer.toString(StID) + "," + dataTable[1] + "," + Integer.toString(TermID) + ")");
            this.preparedStatement.executeUpdate();
            this.connection.commit();
        }

    }

    public void TeacherGrading(int StID, int CoID, double Point) throws SQLException {
        Connection var10001 = this.connection;
        String var10002 = Double.toString(Point);
        this.preparedStatement = var10001.prepareStatement("update main_table mt set point =" + var10002 + " where student_id =" + Integer.toString(StID) + " And course_id=" + Integer.toString(CoID));
        this.preparedStatement.executeUpdate();
        this.connection.commit();
        if (Point >= 10.0D) {
            var10001 = this.connection;
            var10002 = Integer.toString(StID);
            this.preparedStatement = var10001.prepareStatement("update student_course set status =1 where student_id=" + var10002 + " And course_id=" + Integer.toString(CoID));
        } else {
            var10001 = this.connection;
            var10002 = Integer.toString(StID);
            this.preparedStatement = var10001.prepareStatement("update student_course set status =-1 where student_id=" + var10002 + " And course_id=" + Integer.toString(CoID));
        }

        this.preparedStatement.executeUpdate();
        this.connection.commit();
    }

    public double AverageCalc(int StID, int TermID) throws SQLException {
        double a = 1.0D;
        Connection var10001 = this.connection;
        String var10002 = Integer.toString(StID);
        this.preparedStatement = var10001.prepareStatement(" select avg(Point) from main_table mt where student_id=" + var10002 + " AND TermID = " + Integer.toString(TermID));

        for(this.resultSet = this.preparedStatement.executeQuery(); this.resultSet.next(); a = this.resultSet.getDouble(1)) {
        }

        return a;
    }

    public double AverageCalc(int StID) throws SQLException {
        double a = 1.0D;
        this.preparedStatement = this.connection.prepareStatement(" select avg(Point) from main_table mt where student_id=" + Integer.toString(StID));

        for(this.resultSet = this.preparedStatement.executeQuery(); this.resultSet.next(); a = this.resultSet.getDouble(1)) {
        }

        return a;
    }

    public void StudentsShowForTeacher(int TeacherID, int TermID) throws SQLException {
        Connection var10001 = this.connection;
        String var10002 = Integer.toString(TeacherID);
        this.preparedStatement = var10001.prepareStatement("select st.id , st.name , st.family , st.major, st.EnterYear from main_table mt  join student st on mt.student_id=st.id where mt.teacher_id=" + var10002 + " AND mt.TermID= " + Integer.toString(TermID));
        this.resultSet = this.preparedStatement.executeQuery();

        while(this.resultSet.next()) {
            System.out.println(this.resultSet.getInt(1));
            System.out.println(this.resultSet.getString(2));
            System.out.println(this.resultSet.getString(3));
            System.out.println(this.resultSet.getString(4));
            System.out.println(this.resultSet.getString(5));
        }

    }

    public void DropStudent(int StID, int CoID) throws SQLException {
        Connection var10001 = this.connection;
        String var10002 = Integer.toString(StID);
        this.preparedStatement = var10001.prepareStatement(" delete from main_table mt where mt.student_id=" + var10002 + " AND mt.course_id=" + Integer.toString(CoID));
        this.preparedStatement.executeUpdate();
        this.connection.commit();
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
