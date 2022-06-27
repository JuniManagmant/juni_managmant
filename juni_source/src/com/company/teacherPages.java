package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class teacherPages {
    public static class unitForm {
        String unitName,unitTeacher,unitCode,unitPoint,StudentName,Major; //take from db
        JCheckBox unitCb;
        JLabel unitLabe,teacherLabel,unitCodeLabel,unitPointLabel,StudentLabel,MajorLabel;
    }

    private static int TermID=14011;
    public static class gradingForm {
        String nameStr,studentCodeStr;
        JLabel nameCodeLabel;
        JTextField markField;
    }
    public static void mainPage(JFrame mainFrame ,int ID){

        JLabel mainPageLabel = new JLabel("جونی منیجمنت، زیبا پیشرو مطمئن");

        mainPageLabel.setBounds(450,450,200,30);

        mainFrame.add(mainPageLabel);
    }
    public static void grading (JFrame mainFrame,int id) {
        try {
            Repository repository= new Repository();
            String[] studentNames = repository.StudentNamesForEachTeacher(id);
            String[] studentCodes = repository.StudentIDsForEachTeacher(id);
            String[] courseNames = repository.StudentsCourseNamesForEachTeacher(id);
            String[] courseIDs = repository.StudentsCourseIDsForEachTeacher(id);

            JButton submitMarksButton = new JButton("ثبت نمرات");

            int nameLabelx=50,markFieldx=250,yoffset=130;

            gradingForm[] marking = new gradingForm[studentNames.length];

            for (int i=0;i<marking.length;i++){
                marking[i] = new gradingForm();

                marking[i].nameCodeLabel = new JLabel(studentNames[i] +"         "+ studentCodes[i] +"         "+  courseNames[i]);
                marking[i].markField = new JTextField();

                marking[i].nameCodeLabel.setBounds(nameLabelx,yoffset,200,30);
                marking[i].markField.setBounds(markFieldx,yoffset,80,30);

                mainFrame.add(marking[i].nameCodeLabel);
                mainFrame.add(marking[i].markField);

                yoffset = yoffset + 40;
            }
            yoffset = yoffset + 20;

            submitMarksButton.setBounds(nameLabelx,yoffset,130,40);
            mainFrame.add(submitMarksButton);

            submitMarksButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<studentNames.length;i++){
                        try {
                            repository.TeacherGrading(Integer.parseInt(studentCodes[i]), Integer.parseInt(courseIDs[i]),Double.parseDouble(marking[i].markField.getText()));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(null , "Grades Entered Successfully!");
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewCourses(JFrame mainFrame,int id){
        try {
            Repository repository= new Repository();
            String[] coursesNameList = repository.StudentsCourseNamesForEachTeacher(id)/*FROM DB*/;

            String[] unitsCode = repository.StudentsCourseIDsForEachTeacher(id);

            unitForm[] unitsList = new unitForm[coursesNameList.length];

            int unitLabelx=100,teacherLabelx=230,codeLabelx=360,yoffset=140;

            for (int i=0;i<coursesNameList.length;i++){
                unitsList[i] = new unitForm();

                unitsList[i].unitName = coursesNameList[i];
                unitsList[i].unitCode = unitsCode[i];

                unitsList[i].unitLabe = new JLabel(unitsList[i].unitName);
                unitsList[i].unitCodeLabel = new JLabel(unitsList[i].unitCode);

                unitsList[i].unitLabe.setBounds(unitLabelx,yoffset,100,30);
                unitsList[i].unitCodeLabel.setBounds(codeLabelx,yoffset,200,30);

                mainFrame.add(unitsList[i].unitLabe);
                mainFrame.add(unitsList[i].unitCodeLabel);

                yoffset = yoffset + 40;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void DropStudent(JFrame mainFrame, int id){
        JLabel course = new JLabel("Course ID");
        JLabel student = new JLabel("Student ID");
        TextField CourseID = new TextField();
        TextField StudentID = new TextField();
        JButton submitDrop = new JButton("Drop Student");
        int StudentIDx=50,CourseIDx=250,yoffset=130;
        course.setBounds(CourseIDx,yoffset,100,30);
        student.setBounds(StudentIDx,yoffset,100,30);
        StudentID.setBounds(StudentIDx,yoffset+50,70,30);
        CourseID.setBounds(CourseIDx,yoffset+50,70,30);
        submitDrop.setBounds(StudentIDx+130, yoffset+100,150,50);
        mainFrame.add(course);
        mainFrame.add(student);
        mainFrame.add(StudentID);
        mainFrame.add(CourseID);
        mainFrame.add(submitDrop);

        submitDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Repository repository= new Repository();
                    repository.DropStudent(Integer.parseInt(StudentID.getText()),Integer.parseInt(CourseID.getText()));
                    JOptionPane.showMessageDialog(null,"Student Dropped Successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    public static void viewStudents(JFrame mainFrame,int id){

        try {
            Repository repository= new Repository();
            JLabel titleLabel = new JLabel("مشاهده لیست دانشجویان");
            titleLabel.setBounds(900,100,100,30);
            mainFrame.add(titleLabel);

            JLabel nameCourseMajor = new JLabel();
            int namex=60,yoffset=170;
            String[] studentsFirst = repository.StudentFirstNamesForEachTeacher(id) /*DB*/;
            String[] studentLast = repository.StudentNamesForEachTeacher(id) /*DB*/;
            String[] courseName = repository.StudentsCourseNamesForEachTeacher(id) /*DB*/;
            String[] studentMajor = repository.StudentMajorForEachTeacher(id) /*DB*/;
            unitForm[] unitsList = new unitForm[courseName.length];
            int unitLabelx=260,teacherLabelx=230,codeLabelx=360;
            for (int i=0;i<courseName.length;i++){
                unitsList[i] = new unitForm();

                unitsList[i].unitName = courseName[i];
                unitsList[i].StudentName = studentsFirst[i] + "    "+studentLast[i];
                unitsList[i].Major= studentMajor[i];

                unitsList[i].unitLabe = new JLabel(unitsList[i].unitName);
                unitsList[i].StudentLabel = new JLabel(unitsList[i].StudentName);
                unitsList[i].MajorLabel = new JLabel(unitsList[i].Major);

                unitsList[i].unitLabe.setBounds(unitLabelx,yoffset,100,30);
                unitsList[i].StudentLabel.setBounds(codeLabelx,yoffset,200,30);
                unitsList[i].MajorLabel.setBounds(codeLabelx+230, yoffset,200,30);

                mainFrame.add(unitsList[i].unitLabe);
                mainFrame.add(unitsList[i].StudentLabel);
                mainFrame.add(unitsList[i].MajorLabel);

                yoffset = yoffset + 100;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void dept(JFrame mainFrame,int id) {
        JLabel titleLabel = new JLabel("مشاهده تراز مالی");
        titleLabel.setBounds(900, 150, 100, 30);
        mainFrame.add(titleLabel);

    }
}