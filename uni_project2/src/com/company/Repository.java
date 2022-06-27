package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    public String  readname (String Tname , String Cname, String Condition) throws SQLException{
        String  a = "" ;
        preparedStatement = connection.prepareStatement("select "+ Cname + " from " + Tname + " WHERE " + Condition + ";" );
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            a = resultSet.getString(1);}
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
    public void req_loan(int loan_amount, int ID){
        change("financial", "debt = debt - "+ loan_amount , "ID" , ID);
    }
    public int financial(int ID) throws SQLException {
        int a = read("financial" , "debt" , "ID =" + ID);
        return a;

    }

    public void pay_debt(int loan_amount , int ID){
        change("financial", "debt = debt + "+ loan_amount , "ID" , ID);
    }

    //____________________________________________________________________________________________________________
    //____________________________________________________________________________________________________________
    //____________________________________________________________________________________________________________
    private PreparedStatement prestmt;

    private ResultSet resset;



    public int StudentCounter(int TeacherID,int CoID) throws SQLException{
        int a=0;
        preparedStatement=connection.prepareStatement("select count(student_id) from main_table mt where mt.teacher_id=" + Integer.toString(TeacherID)+" and mt.course_id=" + Integer.toString(CoID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;
    }
    public void ShowTeachersCourses( int ID) throws SQLException{

        prestmt=connection.prepareStatement("select  mt.course_id, co.name " +
                " from main_table mt " +
                " join courses co " +
                " on mt.course_id = co.id" +
                " AND mt.teacher_id=" + Long.toString(ID));
        resset=prestmt.executeQuery();
        while(resset.next()){


            String dataTable[]={String.valueOf(resset.getInt(1)), resset.getString(2),String.valueOf(StudentCounter(ID,resset.getInt(1)))};
            System.out.println(Arrays.toString(dataTable));

        }
    }public int ShowAvailableCoursesCount(int ID) throws SQLException{
        int a=0;
        preparedStatement = connection.prepareStatement( " select distinct count(tc.id)"
                +" from teacher_course tc"+
                " join student_course sc"+
                " on tc.req_id=sc.course_id"+
                " AND sc.status=1 AND sc.student_id="+ Integer.toString(ID)+
                " join student_course sc1"+
                " on tc.course_id=sc1.course_id AND sc1.status!=1 and sc1.student_id="+ Integer.toString(ID)+
                " join courses co"+
                " on tc.course_id=co.id"+
                " join teacher tea"+
                " on tc.teacher_id = tea.ID ");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;

    }
    public String [] ShowAvailableCoursesID(int ID) throws SQLException{
        String [] dataTable = new String[ShowAvailableCoursesCount(ID)];
        int i=0;
        preparedStatement = connection.prepareStatement( " select  tc.id "
                +" from teacher_course tc"+
                " join student_course sc"+
                " on tc.req_id=sc.course_id"+
                " AND sc.status=1 AND sc.student_id="+ Integer.toString(ID)+
                " join student_course sc1"+
                " on tc.course_id=sc1.course_id AND sc1.status!=1 and sc1.student_id="+ Integer.toString(ID)+
                " join courses co"+
                " on tc.course_id=co.id"+
                " join teacher tea"+
                " on tc.teacher_id = tea.ID ");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getInt(1));
            i++;
        }
        return dataTable;

    }
    public String[] ShowAvailableCoursesName(int ID) throws SQLException{
        String [] dataTable = new String[ShowAvailableCoursesCount(ID)];
        int i=0;
        preparedStatement = connection.prepareStatement( " select co.name"
                +" from teacher_course tc"+
                " join student_course sc"+
                " on tc.req_id=sc.course_id"+
                " AND sc.status=1 AND sc.student_id="+ Integer.toString(ID)+
                " join student_course sc1"+
                " on tc.course_id=sc1.course_id AND sc1.status!=1 and sc1.student_id="+ Integer.toString(ID)+
                " join courses co"+
                " on tc.course_id=co.id"+
                " join teacher tea"+
                " on tc.teacher_id = tea.ID ");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public String[] ShowAvailableCoursesTeacherName(int ID) throws SQLException{
        String [] dataTable = new String[ShowAvailableCoursesCount(ID)];
        int i=0;
        preparedStatement = connection.prepareStatement( " select tea.family"
                +" from teacher_course tc"+
                " join student_course sc"+
                " on tc.req_id=sc.course_id"+
                " AND sc.status=1 AND sc.student_id="+ Integer.toString(ID)+
                " join student_course sc1"+
                " on tc.course_id=sc1.course_id AND sc1.status!=1 and sc1.student_id="+ Integer.toString(ID)+
                " join courses co"+
                " on tc.course_id=co.id"+
                " join teacher tea"+
                " on tc.teacher_id = tea.ID ");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public int CourseCounter() throws SQLException{
        int a=0;
        preparedStatement=connection.prepareStatement("select count(ID) from courses");
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;
    }
    public int[] CourseCrawler() throws SQLException{
        int i=0;
        int crawler[]= new int[CourseCounter()];
        preparedStatement=connection.prepareStatement("select ID from courses");
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            crawler[i]=resultSet.getInt(1);
            i++;
        }
        return crawler;
    }
    public void StudentCourseCreator(int StudentID) throws SQLException{
        int a[]=CourseCrawler();
        int i=1;
        while(i<=CourseCounter()){
            preparedStatement=connection.prepareStatement("insert into student_course (student_id, course_id, status) values (" + Long.toString(StudentID) +","+ Integer.toString(a[i-1])+ ",0)");
            preparedStatement.executeUpdate();
            connection.commit();
            i++;
        }
        preparedStatement=connection.prepareStatement("update student_course set status=1 where course_id=0");
        preparedStatement.executeUpdate();
        connection.commit();

    }
    public void UnitSelection(int StID,int ID,int TermID) throws SQLException{
        preparedStatement=connection.prepareStatement(" select distinct co.id , tea.id "
                +" from teacher_course tc"+
                " join student_course sc"+
                " on tc.req_id=sc.course_id"+
                " AND sc.status=1 AND sc.student_id="+ Integer.toString(StID)+
                " join student_course sc1"+
                " on tc.course_id=sc1.course_id AND sc1.status!=1 and sc1.student_id="+ Integer.toString(StID)+
                " join courses co"+
                " on tc.course_id=co.id"+
                " join teacher tea"+
                " on tc.teacher_id = tea.ID "
                +" where tc.id="+ Integer.toString(ID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String dataTable[]={String.valueOf(resultSet.getInt(2)),String.valueOf(resultSet.getInt(1))};
            preparedStatement=connection.prepareStatement("insert into main_table (teacher_id , student_id, course_id, termID) values (" +dataTable[0]+","+Integer.toString(StID)+","+dataTable[1]+","+Integer.toString(TermID)+")");
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }
    public void TeacherGrading(int StID,int CoID,double Point) throws SQLException{
        preparedStatement=connection.prepareStatement("update main_table mt set point =" + Double.toString(Point)+ " where student_id =" +Integer.toString(StID)+" And course_id=" + Integer.toString(CoID) );
        preparedStatement.executeUpdate();
        connection.commit();
        if(Point>=10){
            preparedStatement=connection.prepareStatement("update student_course set status =1 where student_id="+Integer.toString(StID)+" And course_id="+Integer.toString(CoID));
        }
        else
            preparedStatement=connection.prepareStatement("update student_course set status =-1 where student_id="+Integer.toString(StID)+" And course_id="+Integer.toString(CoID));
        preparedStatement.executeUpdate();
        connection.commit();
    }/*
    public DefaultTableModel ReportEachTerm(int StID, int TermID) throws SQLException{
        preparedStatement=connection.prepareStatement("select mt.course_id , co.Name , tea.Name , tea.Family , mt.Point , mt.TermID\n" +
                                                          "from main_table mt\n" +
                                                          "join courses co\n" +
                                                          "on mt.course_id = co.ID\n" +
                                                          "join teacher tea\n" +
                                                          "on mt.teacher_id=tea.ID\n" +
                                                          "where mt.TermID="+ Integer.toString(TermID)+" AND mt.student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String dataTable[]={String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),String.valueOf(resultSet.getDouble(5)), String.valueOf(resultSet.getInt(6))};
            DefaultTableModel defaultTableModel= (DefaultTableModel)studentPages.removeUnit().;
            defaultTableModel.addRow(dataTable);
        }
    }
    public void ReportEachTerm(int StID) throws SQLException{
        preparedStatement=connection.prepareStatement("select mt.course_id , co.Name , tea.Name , tea.Family , mt.Point , mt.TermID\n" +
                "from main_table mt\n" +
                "join courses co\n" +
                "on mt.course_id = co.ID\n" +
                "join teacher tea\n" +
                "on mt.teacher_id=tea.ID\n" +
                "where mt.student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String dataTable[]={String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),String.valueOf(resultSet.getDouble(5)), String.valueOf(resultSet.getInt(6))};
            DefaultTableModel defaultTableModel= (DefaultTableModel)getModel();
            defaultTableModel.addRow(dataTable);
        }
    }*/
    public int StudentCourseCounter(int StID , int TermID) throws SQLException{
        int a=0;
        preparedStatement=connection.prepareStatement("select count(ID) from main_table mt where mt.student_id =" + Integer.toString(StID) + " and mt.termid=" + Integer.toString(TermID));
        resset=preparedStatement.executeQuery();
        while(resset.next()){
            a=resset.getInt(1);
        }
        return a;
    }
    public String [] CoursesNameList(int StID, int TermID) throws SQLException{
        String [] dataTable=new String[StudentCourseCounter(StID,TermID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select mt.course_id , co.Name , tea.Name , tea.Family , mt.Point , mt.TermID\n" +
                "from main_table mt\n" +
                "join courses co\n" +
                "on mt.course_id = co.ID\n" +
                "join teacher tea\n" +
                "on mt.teacher_id=tea.ID\n" +
                "where mt.TermID="+ Integer.toString(TermID)+" AND mt.student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(2);
            i++;
        }
        return dataTable;
    }
    public String [] TeachersNameList(int StID, int TermID) throws SQLException{
        String [] dataTable=new String[StudentCourseCounter(StID,TermID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select mt.course_id , co.Name , tea.Name , tea.Family , mt.Point , mt.TermID\n" +
                "from main_table mt\n" +
                "join courses co\n" +
                "on mt.course_id = co.ID\n" +
                "join teacher tea\n" +
                "on mt.teacher_id=tea.ID\n" +
                "where mt.TermID="+ Integer.toString(TermID)+" AND mt.student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(4);
            i++;
        }
        return dataTable;
    }
    public String [] CoursesIDList(int StID, int TermID) throws SQLException{
        String [] dataTable=new String[StudentCourseCounter(StID,TermID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select mt.course_id , co.Name , tea.Name , tea.Family , mt.Point , mt.TermID\n" +
                "from main_table mt\n" +
                "join courses co\n" +
                "on mt.course_id = co.ID\n" +
                "join teacher tea\n" +
                "on mt.teacher_id=tea.ID\n" +
                "where mt.TermID="+ Integer.toString(TermID)+" AND mt.student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getString(6));
            i++;
        }
        return dataTable;
    }
    public String [] CoursesPointList(int StID, int TermID) throws SQLException{
        String [] dataTable=new String[StudentCourseCounter(StID,TermID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select mt.course_id , co.Name , tea.Name , tea.Family , mt.Point , mt.TermID\n" +
                "from main_table mt\n" +
                "join courses co\n" +
                "on mt.course_id = co.ID\n" +
                "join teacher tea\n" +
                "on mt.teacher_id=tea.ID\n" +
                "where mt.TermID="+ Integer.toString(TermID)+" AND mt.student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getDouble(5));
            i++;
        }
        return dataTable;
    }
    public double AverageCalc(int StID,int TermID) throws SQLException{
        double a=1;
        preparedStatement=connection.prepareStatement(" select avg(Point) from main_table mt where student_id="+Integer.toString(StID)+" AND TermID = "+ Integer.toString(TermID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()) {
            a = resultSet.getDouble(1);
        }
        return a;
    }
    public double AverageCalc(int StID) throws SQLException{
        double a=1;
        preparedStatement=connection.prepareStatement(" select avg(Point) from main_table mt where student_id="+Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            a=resultSet.getDouble(1);
        }
        return a;
    }/*
    public void StudentsShowForTeacher(int TeacherID, int TermID) throws SQLException{
        preparedStatement=connection.prepareStatement("select st.id , st.name , st.family , st.major, st.EnterYear" +
                " from main_table mt " +
                " join student st" +
                " on mt.student_id=st.id" +
                " where mt.teacher_id=" + Integer.toString(TeacherID) + " AND mt.TermID= "+ Integer.toString(TermID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            String dataTable[]={String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),String.valueOf(resultSet.getInt(5))};
            DefaultTableModel defaultTableModel= (DefaultTableModel)getModel();
            defaultTableModel.addRow(dataTable);
        }

    }*/
    public void DropStudent(int StID,int CoID) throws SQLException{
        preparedStatement=connection.prepareStatement(" delete from main_table mt where mt.student_id=" + Integer.toString(StID)+ " AND mt.course_id=" + Integer.toString(CoID));
        preparedStatement.executeUpdate();
        connection.commit();
    }
    public void DropCourse(int StID,int CoID) throws SQLException{
        preparedStatement=connection.prepareStatement(" delete from main_table mt where mt.student_id="+ Integer.toString(StID)+ " and mt.course_id = " + Integer.toString(CoID));
        preparedStatement.executeUpdate();
        connection.commit();
    }
    public int CourseIDExtractor(String CoName) throws SQLException{
        int a=0;
        String sql= "select ID from courses where Name= '" + CoName + "'";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;
    }
    public void GradeTheTeachers(int TeaID, int StID , boolean point) throws SQLException{
        preparedStatement=connection.prepareStatement(" update main_table mt set GradeTeacher =" + Boolean.toString(point) + " where mt.teacher_id=" + Integer.toString(TeaID) + " and mt.student_id ="+ Integer.toString(StID));
        preparedStatement.executeUpdate();
        connection.commit();
    }
    public int TeacherIDExtractor(String TeaName) throws SQLException{
        int a=0;
        String sql= "select ID from teacher where family= '" + TeaName + "'";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;
    }
    ///////////////////////////////KARNAMEH///////////////////////////////////////////////////
    public int StudentRecordCounter(int StID) throws SQLException{
        int a=0;
        preparedStatement=connection.prepareStatement("select count(course_id) from main_table mt where mt.student_id = " + Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;
    }
    public String [] Coursename(int StID) throws SQLException{
        String [] dataTable=new String[StudentRecordCounter(StID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select courses.name" +
                " from main_table mt " +
                " join courses" +
                " on mt.course_id = courses.id" +
                " where mt.studenet_id=" + Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public String [] Teachername(int StID) throws SQLException{
        String [] dataTable=new String[StudentRecordCounter(StID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select teacher.family" +
                " from main_table mt " +
                " join teacher" +
                " on mt.teacher_id = teacher.id" +
                " where mt.studenet_id=" + Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public String [] PointList(int StID) throws SQLException{
        String [] dataTable=new String[StudentRecordCounter(StID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select mt.point" +
                " from main_table mt " +
                " where mt.student_id=" + Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getDouble(1));
            i++;
        }
        return dataTable;
    }
    public String [] TermIDList(int StID) throws SQLException{
        String [] dataTable=new String[StudentRecordCounter(StID)];
        int i=0;
        preparedStatement=connection.prepareStatement("select mt.TermID" +
                " from main_table mt " +
                " where mt.studenet_id=" + Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getInt(1));
            i++;
        }
        return dataTable;
    }
    public String StudentName(int StID) throws SQLException{
        String name="";
        preparedStatement=connection.prepareStatement("select name from student where student.id=" +Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            name = resultSet.getString(1);
        }
        return name;
    }
    public int MorrakhassiCheck(int StID) throws SQLException{
        int a=0;
        preparedStatement=connection.prepareStatement(" select Tleave from student where id=" + Integer.toString(StID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            a=resultSet.getInt(1);
        }
        return a;
    }
    public int StudentCountForEachTeacher(int teacherID) throws SQLException{
        int a=0;
        preparedStatement=connection.prepareStatement(" select distinct count(student_id)" +
                " from main_table mt where mt.teacher_id=  "+Integer.toString(teacherID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            a=resultSet.getInt(1);
        }
        return  a;
    }
    public String [] StudentNamesForEachTeacher(int TeacherID) throws SQLException{
        String [] dataTable=new String[StudentCountForEachTeacher(TeacherID)];
        int i=0;
        preparedStatement=connection.prepareStatement(" select student.family" +
                " from main_table mt " +
                " join student " +
                " on mt.student_id = student.id" +
                " where mt.teacher_id = " + Integer.toString(TeacherID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public String [] StudentFirstNamesForEachTeacher(int TeacherID) throws SQLException{
        String [] dataTable=new String[StudentCountForEachTeacher(TeacherID)];
        int i=0;
        preparedStatement=connection.prepareStatement(" select student.name" +
                " from main_table mt " +
                " join student " +
                " on mt.student_id = student.id" +
                " where mt.teacher_id = " + Integer.toString(TeacherID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public String [] StudentIDsForEachTeacher(int TeacherID) throws SQLException{
        String [] dataTable=new String[StudentCountForEachTeacher(TeacherID)];
        int i=0;
        preparedStatement=connection.prepareStatement(" select student.id" +
                " from main_table mt " +
                " join student " +
                " on mt.student_id = student.id" +
                " where mt.teacher_id = " + Integer.toString(TeacherID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getInt(1));
            i++;
        }
        return dataTable;
    }
    public String [] StudentsCourseNamesForEachTeacher(int TeacherID) throws SQLException{
        String [] dataTable=new String[StudentCountForEachTeacher(TeacherID)];
        int i=0;
        preparedStatement=connection.prepareStatement(" select co.Name" +
                " from main_table mt " +
                " join courses co " +
                " on mt.course_id = co.id" +
                " where mt.teacher_id = " + Integer.toString(TeacherID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }
    public String [] StudentsCourseIDsForEachTeacher(int TeacherID) throws SQLException{
        String [] dataTable=new String[StudentCountForEachTeacher(TeacherID)];
        int i=0;
        preparedStatement=connection.prepareStatement(" select distinct co.id" +
                " from main_table mt " +
                " join courses co " +
                " on mt.course_id = co.id" +
                " where mt.teacher_id = " + Integer.toString(TeacherID));
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            dataTable[i]=String.valueOf(resultSet.getInt(1));
            i++;
        }
        return dataTable;
    }
    public String [] StudentMajorForEachTeacher(int ID) throws SQLException{
        String [] dataTable= new String[StudentCountForEachTeacher(ID)];
        int i=0;
        preparedStatement=connection.prepareStatement(" select st.major" +
                " from main_table mt " +
                " join student st" +
                " on mt.student_id = st.id" +
                " where mt.teacher_id=" + Integer.toString(ID));
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            dataTable[i]=resultSet.getString(1);
            i++;
        }
        return dataTable;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
