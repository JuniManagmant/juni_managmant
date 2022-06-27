package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class studentPages {
    private static int TermID=14011;
    public studentPages() throws SQLException {
    }

    public static class unitForm {
        String unitName,unitTeacher,unitCode,unitPoint,StudentName,Major; //take from db
        JCheckBox unitCb;
        JLabel unitLabe,teacherLabel,unitCodeLabel,unitPointLabel,StudentLabel,MajorLabel;}
    public static class teacherGradingForm {
        String teacherName;
        JLabel teacherLabel,goodL,badL;
        JCheckBox goodC,badC;
    }




    public static void payment(JFrame mainFrame,int ID) throws SQLException {

        JLabel titleLabel = new JLabel("پرداخت شهریه");
        JLabel deptNote = new JLabel();
        JLabel deptStatus = new JLabel();
        JButton payButton = new JButton("پرداخت");
        JLabel countity = new JLabel("مبلغ");
        JTextField moneyField = new JTextField();
        Repository repository = new Repository();
        int debt = repository.financial(ID);

        if(debt > 0){
            deptStatus.setText("بستانکار");
        }else if (debt < 0){
            deptStatus.setText(" بده کار");
        }else{
            deptStatus.setText("بی حساب");
        }

        deptNote.setText("تراز مالی: "+ debt);

        //xy
        titleLabel.setBounds(900,200,100,30);
        deptNote.setBounds(400,300,200,30);
        deptStatus.setBounds(550,300,100,30);
        moneyField.setBounds(400,380,100,30);
        countity.setBounds(550,380,100,30);
        payButton.setBounds(400,450,100,40);

        //add
        //
        mainFrame.add(titleLabel);
        mainFrame.add(deptNote);
        mainFrame.add(deptStatus);
        mainFrame.add(moneyField);
        mainFrame.add(countity);
        mainFrame.add(payButton);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int payment = Integer.parseInt(moneyField.getText());
                try {
                    Repository repository = new Repository();
                    repository.pay_debt(payment , ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
    public static void eshteghal(JFrame mainFrame,String nameStr){

        JLabel eshteghalNote = new JLabel();
        eshteghalNote.setText("گواهی میشود دانشجو"+ nameStr + "در حال اشتغال به تحصیل میباشد");

        eshteghalNote.setBounds(500,250,400,400);

        mainFrame.add(eshteghalNote);
    }
//____________________________________________________________________________________________________________

    public static void unitSelect(JFrame mainFrame,int id){
        studentPages.ShowAvailableCourses(mainFrame,id);
        //texfield form
        JLabel unitSelectByCodeLabel = new JLabel("انتخاب واحد با کد درس");
        JTextField unitCodePrompt = new JTextField();
        JLabel unitCodeLabel = new JLabel("کد:");
        JButton submitByCodeButton = new JButton("ثبت درس");
        JLabel submitted= new JLabel("OK");

        // action listener for unit select with unit code
        submitByCodeButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Repository repository=new Repository();
                    repository.UnitSelection(id,Integer.parseInt(unitCodePrompt.getText()),TermID);
                    JOptionPane.showMessageDialog(null,"درس با موفقیت اضافه شد");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        unitSelectByCodeLabel.setBounds(770,160,200,30);
        unitCodeLabel.setBounds(770,190,100,30);
        unitCodePrompt.setBounds(600,190,150,30);
        submitByCodeButton.setBounds(600,260,130,40);

        mainFrame.add(unitSelectByCodeLabel);
        mainFrame.add(unitCodeLabel);
        mainFrame.add(unitCodePrompt);
        mainFrame.add(submitByCodeButton);


    }

    public static void removeUnit(JFrame mainFrame,int id){
        //title
        try {
            Repository repository=new Repository();
            JLabel titleLabel = new JLabel("حذف واحد درسی");
            titleLabel.setBounds(800,150,100,30);
            mainFrame.add(titleLabel);

            String[] units = repository.CoursesNameList(id,14011)/*store from DB*/ ;
            int lxoff=100,cxoff=65,yoff=200;
            unitForm[] unitToRemove = new unitForm[units.length];
            for(int i=0;i<unitToRemove.length;i++){
                unitToRemove[i] = new unitForm();
                unitToRemove[i].unitLabe = new JLabel();
                unitToRemove[i].unitCb = new JCheckBox();

                unitToRemove[i].unitLabe.setText(units[i]);

                unitToRemove[i].unitLabe.setBounds(lxoff,yoff,200,30);
                unitToRemove[i].unitCb.setBounds(cxoff,yoff,25,25);

                mainFrame.add(unitToRemove[i].unitLabe);
                mainFrame.add(unitToRemove[i].unitCb);

                yoff = yoff + 50;
            }

            //remove button
            JButton removeButton = new JButton("حذف");

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<unitToRemove.length;i++){
                        if(unitToRemove[i].unitCb.isSelected()){
                            try {
                                repository.DropCourse(id,repository.CourseIDExtractor(unitToRemove[i].unitLabe.getText()));

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                    JOptionPane.showMessageDialog(null,"Removed Successfully");
                    mainFrame.dispose();
                }
            });
            removeButton.setBounds(700,yoff,100,30);
            mainFrame.add(removeButton);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewUnits(JFrame mainFrame,int id){
        try {
            Repository repository= new Repository();
            String[] currentUnits = repository.CoursesNameList(id,TermID)/*FROM DB*/;
            String[] teachersList = repository.TeachersNameList(id,TermID);
            String[] unitsCode = repository.CoursesIDList(id,TermID);

            unitForm[] unitsList = new unitForm[currentUnits.length];

            int unitLabelx=100,teacherLabelx=230,codeLabelx=360,yoffset=140;

            for (int i=0;i<currentUnits.length;i++){
                unitsList[i] = new unitForm();

                unitsList[i].unitName = currentUnits[i];
                unitsList[i].unitTeacher = teachersList[i];
                unitsList[i].unitCode = unitsCode[i];

                unitsList[i].unitLabe = new JLabel(unitsList[i].unitName);
                unitsList[i].teacherLabel = new JLabel(unitsList[i].unitTeacher);
                unitsList[i].unitCodeLabel = new JLabel(unitsList[i].unitCode);

                unitsList[i].unitLabe.setBounds(unitLabelx,yoffset,100,30);
                unitsList[i].teacherLabel.setBounds(teacherLabelx,yoffset,100,30);
                unitsList[i].unitCodeLabel.setBounds(codeLabelx,yoffset,200,30);

                mainFrame.add(unitsList[i].unitLabe);
                mainFrame.add(unitsList[i].teacherLabel);
                mainFrame.add(unitsList[i].unitCodeLabel);

                yoffset = yoffset + 40;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void teacherGrading(JFrame mainFrame,int id){
        try {
            Repository repository=new Repository();
            JLabel titleLabel = new JLabel("ارزشیابی اساتید");
            JButton submitButton = new JButton("ثبت نظر");

            titleLabel.setBounds(900,60,100,30);
            mainFrame.add(titleLabel);

            int goodCheckboxXoffset=500;
            int goodLabelXoffset=530;
            int badChechboxXoffset=700;
            int badLabelXoffset=730;
            int yoffset=100;
            int teacherNameLabelXoffset=100;


            String[] teachersList = repository.TeachersNameList(id,TermID) /*DB*/;
            teacherGradingForm[] tg = new teacherGradingForm[teachersList.length];
            for(int i=0;i<teachersList.length;i++){
                tg[i] = new teacherGradingForm();
                //System.out.println(teachersList[i]);
                tg[i].teacherName = teachersList[i];
                //tg[i].teacherName = "helllo";
                tg[i].teacherLabel = new JLabel();
                tg[i].goodL = new JLabel("خوب");
                tg[i].badL = new JLabel("بد");
                tg[i].badC = new JCheckBox();
                tg[i].goodC = new JCheckBox();

                tg[i].teacherLabel.setText(tg[i].teacherName);

                tg[i].teacherLabel.setBounds(teacherNameLabelXoffset,yoffset,200,30);
                tg[i].goodC.setBounds(goodCheckboxXoffset,yoffset,30,30);
                tg[i].goodL.setBounds(goodLabelXoffset,yoffset,100,30);
                tg[i].badC.setBounds(badChechboxXoffset,yoffset,30,30);
                tg[i].badL.setBounds(badLabelXoffset,yoffset,100,30);

                yoffset = yoffset + 65;

                mainFrame.add(tg[i].teacherLabel);
                mainFrame.add(tg[i].goodC);
                mainFrame.add(tg[i].goodL);
                mainFrame.add(tg[i].badC);
                mainFrame.add(tg[i].badL);
            }
            submitButton.setBounds(200,yoffset,140,40);
            mainFrame.add(submitButton);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<teachersList.length;i++){


                        try {
                            repository.GradeTheTeachers(repository.TeacherIDExtractor(tg[i].teacherLabel.getText()),id,tg[i].goodC.isSelected());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                    JOptionPane.showMessageDialog(null, "OK");
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ShowAvailableCourses(JFrame mainFrame,int id){
        try {
            Repository repository=new Repository();
            String[] ID = repository.ShowAvailableCoursesID(id);
            String[] avaUname = repository.ShowAvailableCoursesName(id);
            String[] avaUteacher = repository.ShowAvailableCoursesTeacherName(id);

            unitForm[] unitsList = new unitForm[repository.ShowAvailableCoursesCount(id)];

            int unitLabelx=100,teacherLabelx=230,codeLabelx=360,yoffset=140;

            for (int i=0;i< repository.ShowAvailableCoursesCount(id);i++){
                unitsList[i] = new unitForm();

                unitsList[i].unitName = avaUname[i];
                unitsList[i].unitTeacher = avaUteacher[i];
                unitsList[i].unitCode = ID[i];

                unitsList[i].unitLabe = new JLabel(unitsList[i].unitName);
                unitsList[i].teacherLabel = new JLabel(unitsList[i].unitTeacher);
                unitsList[i].unitCodeLabel = new JLabel(unitsList[i].unitCode);

                unitsList[i].unitLabe.setBounds(unitLabelx,yoffset,100,30);
                unitsList[i].teacherLabel.setBounds(teacherLabelx,yoffset,100,30);
                unitsList[i].unitCodeLabel.setBounds(codeLabelx,yoffset,200,30);

                mainFrame.add(unitsList[i].unitLabe);
                mainFrame.add(unitsList[i].teacherLabel);
                mainFrame.add(unitsList[i].unitCodeLabel);

                yoffset = yoffset + 40;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void eshteghal(JFrame mainFrame,int ID){
        try {
            Repository repository=new Repository();
            JLabel eshteghalNote = new JLabel();
            if(repository.MorrakhassiCheck(ID)==1){
                eshteghalNote.setText("گواهی میشود دانشجو"+ repository.StudentName(ID) + "در حال فراغت به تحصیل میباشد");
            }
            else
                eshteghalNote.setText("گواهی میشود دانشجو"+ repository.StudentName(ID) + "در حال اشتغال به تحصیل میباشد");

            eshteghalNote.setBounds(500,250,400,400);

            mainFrame.add(eshteghalNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void showKarname(JFrame mainFrame,int id){
        try {
            Repository repository= new Repository();
            String[] currentUnits = repository.CoursesNameList(id,TermID)/*FROM DB*/;
            String[] teachersList = repository.TeachersNameList(id,TermID);
            String[] unitsCode = repository.CoursesIDList(id,TermID);
            String[] points = repository.PointList(id);

            unitForm[] unitsList = new unitForm[currentUnits.length];

            int unitLabelx=100,teacherLabelx=230,codeLabelx=360,pointlabelx=490,yoffset=140;

            for (int i=0;i<currentUnits.length;i++){
                unitsList[i] = new unitForm();

                unitsList[i].unitName = currentUnits[i];
                unitsList[i].unitTeacher = teachersList[i];
                unitsList[i].unitCode = unitsCode[i];
                unitsList[i].unitPoint = points[i];

                unitsList[i].unitLabe = new JLabel(unitsList[i].unitName);
                unitsList[i].teacherLabel = new JLabel(unitsList[i].unitTeacher);
                unitsList[i].unitCodeLabel = new JLabel(unitsList[i].unitCode);
                unitsList[i].unitPointLabel = new JLabel(unitsList[i].unitPoint);

                unitsList[i].unitLabe.setBounds(unitLabelx,yoffset,100,30);
                unitsList[i].teacherLabel.setBounds(teacherLabelx,yoffset,100,30);
                unitsList[i].unitCodeLabel.setBounds(codeLabelx,yoffset,200,30);
                unitsList[i].unitPointLabel.setBounds(pointlabelx,yoffset,50,30);

                mainFrame.add(unitsList[i].unitLabe);
                mainFrame.add(unitsList[i].teacherLabel);
                mainFrame.add(unitsList[i].unitCodeLabel);
                mainFrame.add(unitsList[i].unitPointLabel);

                yoffset = yoffset + 40;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}