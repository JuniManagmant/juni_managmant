package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CommonPages {

    public CommonPages() throws SQLException {
    }

    public static class loanDetail {
        String money;
        JLabel loanText;
        JCheckBox loanCheck;
    }

    Stdn stdn = new Stdn();
    public static void registerS(JFrame mainFrame) {
        JLabel titleLabel = new JLabel("ثبت نام");
        titleLabel.setBounds(900, 150, 150, 30);
        mainFrame.add(titleLabel);

        //back
        JButton backButton = new JButton(" بازگشت ");
        backButton.setBounds(60, 10 ,120,30);
        mainFrame.add(backButton);

        //name
        JLabel nameLabel = new JLabel("نام");
        nameLabel.setBounds(50, 200, 50, 30);
        mainFrame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 200, 80, 30);
        mainFrame.add(nameField);

        //lastname
        JLabel lastnameLabel = new JLabel("نام خانوادگی");
        lastnameLabel.setBounds(50, 250, 100, 30);
        mainFrame.add(lastnameLabel);

        JTextField lastnameField = new JTextField();
        lastnameField.setBounds(150, 250, 80, 30);
        mainFrame.add(lastnameField);

        //codemelli
        JLabel codeLabel = new JLabel("کد ملی");
        codeLabel.setBounds(50, 300, 100, 30);
        mainFrame.add(codeLabel);

        JTextField codeField = new JTextField();
        codeField.setBounds(150, 300, 80, 30);
        mainFrame.add(codeField);

        //phone
        JLabel phoneLabel = new JLabel("شماره تلفن");
        phoneLabel.setBounds(50, 350, 100, 30);
        mainFrame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 350, 80, 30);
        mainFrame.add(phoneField);

        //password
        JLabel passwordLabel = new JLabel("ایجاد رمز");
        passwordLabel.setBounds(50, 450, 100, 30);
        mainFrame.add(passwordLabel);

        JPasswordField passwordFiled = new JPasswordField();
        passwordFiled.setBounds(150, 450, 80, 30);
        mainFrame.add(passwordFiled);

        //branch code
        JLabel branchCodeLabel = new JLabel("رشته");
        branchCodeLabel.setBounds(50, 400, 100, 30);
        mainFrame.add(branchCodeLabel);

        JTextField branchCodeField = new JTextField();
        branchCodeField.setBounds(150, 400, 80, 30);
        mainFrame.add(branchCodeField);

        //birthyear
        JLabel birthyearLabel = new JLabel("سال تولد");
        birthyearLabel.setBounds(50, 500, 100, 30);
        mainFrame.add(birthyearLabel);

        JTextField birthyearField = new JTextField();
        birthyearField.setBounds(150, 500, 80, 30);
        mainFrame.add(birthyearField);

        //postcode
        JLabel postcodeLabel = new JLabel("کد پستی");
        postcodeLabel.setBounds(50, 550, 100, 30);
        mainFrame.add(postcodeLabel);

        JTextField postcodedField = new JTextField();
        postcodedField.setBounds(150, 550, 80, 30);
        mainFrame.add(postcodedField);

        //town
        JLabel townLabel = new JLabel("شهر تولد");
        townLabel.setBounds(50, 600, 100, 30);
        mainFrame.add(townLabel);

        JTextField townField = new JTextField();
        townField.setBounds(150, 600, 80, 30);
        mainFrame.add(townField);


        //sub butt
        JButton regButt = new JButton("ثبت");
        regButt.setBounds(90, 650, 50, 40);
        mainFrame.add(regButt);

        mainFrame.setSize(1650,1080);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        regButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String family = lastnameField.getText();
            String num = codeField.getText();
            String phone = phoneField.getText();
            String major = branchCodeField.getText();
                int birth = Integer.parseInt(birthyearField.getText());
            String post = postcodedField.getText();
            String town = townField.getText();
            int password = Integer.parseInt(passwordFiled.getText());
                try {
                    Register register = new Register();
                     register.reg_stdn(name , family , major , town , phone ,post ,num ,password ,birth);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Repository repository = null;
                try {
                    repository = new Repository();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int ID = 0;
                try {
                    ID = repository.read("student", "ID", "name ='" + name + "' AND family ='" + family + "'");
                    mainFrame.getContentPane().removeAll();
                    CommonPages.login(mainFrame);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                JOptionPane.showMessageDialog(null ,  "شماره دانشجویی :" + ID);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.login(mainFrame);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

//    public static void registerT (JFrame mainFrame) {
//        JLabel titleLabel = new JLabel(" کد درس مودر نظر را وارد کنید");
//        titleLabel.setBounds(900, 150, 150, 30);
//        mainFrame.add(titleLabel);
//
//        //name
//        JLabel nameLabel = new JLabel("نام");
//        nameLabel.setBounds(50, 200, 50, 30);
//        mainFrame.add(nameLabel);
//
//        JTextField nameField = new JTextField();
//        nameField.setBounds(150, 200, 80, 30);
//        mainFrame.add(nameField);
//
//        //sub butt
//        JButton regButt = new JButton("ثبت");
//        regButt.setBounds(90, 500, 50, 40);
//        mainFrame.add(regButt);
//
//        mainFrame.setSize(1650,1080);
//        mainFrame.setLayout(null);
//        mainFrame.setVisible(true);
//        regButt.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//    }

    public static void registerE(JFrame mainFrame) {
        JLabel titleLabel = new JLabel("ثبت نام");
        titleLabel.setBounds(900, 150, 150, 30);
        mainFrame.add(titleLabel);

        //back
        JButton backButton = new JButton(" بازگشت ");
        backButton.setBounds(60, 10 ,120,30);
        mainFrame.add(backButton);

        //name
        JLabel nameLabel = new JLabel("نام");
        nameLabel.setBounds(50, 200, 50, 30);
        mainFrame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 200, 80, 30);
        mainFrame.add(nameField);

        //lastname
        JLabel lastnameLabel = new JLabel("نام خانوادگی");
        lastnameLabel.setBounds(50, 250, 100, 30);
        mainFrame.add(lastnameLabel);

        JTextField lastnameField = new JTextField();
        lastnameField.setBounds(150, 250, 80, 30);
        mainFrame.add(lastnameField);

        //codemelli
        JLabel codeLabel = new JLabel("کد ملی");
        codeLabel.setBounds(50, 300, 100, 30);
        mainFrame.add(codeLabel);

        JTextField codeField = new JTextField();
        codeField.setBounds(150, 300, 80, 30);
        mainFrame.add(codeField);

        //phone
        JLabel phoneLabel = new JLabel("شماره تلفن");
        phoneLabel.setBounds(50, 350, 100, 30);
        mainFrame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 350, 80, 30);
        mainFrame.add(phoneField);

        //password
        JLabel passwordLabel = new JLabel("ایجاد رمز");
        passwordLabel.setBounds(50, 400, 100, 30);
        mainFrame.add(passwordLabel);

        JPasswordField passwordFiled = new JPasswordField();
        passwordFiled.setBounds(150, 400, 80, 30);
        mainFrame.add(passwordFiled);

        //birthyear
        JLabel birthyearLabel = new JLabel("سال تولد");
        birthyearLabel.setBounds(50, 450, 100, 30);
        mainFrame.add(birthyearLabel);

        JTextField birthyearField = new JTextField();
        birthyearField.setBounds(150, 450, 80, 30);
        mainFrame.add(birthyearField);

        //postcode
        JLabel postcodeLabel = new JLabel("کد پستی");
        postcodeLabel.setBounds(50, 500, 100, 30);
        mainFrame.add(postcodeLabel);

        JTextField postcodedField = new JTextField();
        postcodedField.setBounds(150, 500, 80, 30);
        mainFrame.add(postcodedField);

        //town
        JLabel townLabel = new JLabel("شهر تولد");
        townLabel.setBounds(50, 550, 100, 30);
        mainFrame.add(townLabel);

        JTextField townField = new JTextField();
        townField.setBounds(150, 550, 80, 30);
        mainFrame.add(townField);


        //sub butt
        JButton regButt = new JButton("ثبت");
        regButt.setBounds(90, 650, 50, 40);
        mainFrame.add(regButt);

        mainFrame.setSize(1650,1080);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        regButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String family = lastnameField.getText();
                String num = codeField.getText();
                String phone = phoneField.getText();
                int birth = Integer.parseInt(birthyearField.getText());
                String post = postcodedField.getText();
                String town = townField.getText();
                int password = Integer.parseInt(passwordFiled.getText());
                try {
                    Register register = new Register();
                    register.reg_emp(name , family  , password ,town , num , birth , phone , post);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Repository repository = null;
                try {
                    repository = new Repository();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int ID=0;
                try {
                     ID = repository.read("staff", "ID", "name ='" + name + "' AND family ='" + family + "'");
                    CommonPages.login(mainFrame);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                JOptionPane.showMessageDialog(null ,  "کد کارمندی :" + ID);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.login(mainFrame);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

        public static void login(JFrame mainFrame) throws SQLException {

        Register register = new Register();

        JLabel user_id = new JLabel("شماره کاربری:");
        JLabel user_pass = new JLabel("رمز عبور:");

        JTextField usernameField = new JTextField(8);
        JPasswordField passwordFiled = new JPasswordField(10);


        JRadioButton staffButton = new JRadioButton("کارکنان");
        JRadioButton studentButton = new JRadioButton("دانشجویان");
        JRadioButton teacherButton = new JRadioButton("اساتید");

        JButton loginButton = new JButton("ورود");
        JButton guideButton = new JButton("راهنما");
        JButton regSButton = new JButton("ثبت دانشجو");
        JButton regEButton = new JButton("ثبت کارمند ");

        user_id.setBounds(100, 75, 100, 40); //username label
        user_pass.setBounds(100, 135, 100, 40); //password label
        usernameField.setBounds(200, 80, 200, 30); //username field
        passwordFiled.setBounds(200, 140, 200, 30); //password field
        staffButton.setBounds(120, 205, 80, 30); //staff button
        teacherButton.setBounds(200, 205, 80, 30); //teacher button
        studentButton.setBounds(300, 205, 80, 30); //student button
        loginButton.setBounds(200, 300, 120, 30); //login button
        guideButton.setBounds(420, 10, 120, 27); //guide button
        regEButton.setBounds(370,300,120,30);
        regSButton.setBounds(490,300,120,30);

        mainFrame.add(user_id);
        mainFrame.add(user_pass);
        mainFrame.add(usernameField);
        mainFrame.add(passwordFiled);
        mainFrame.add(staffButton);
        mainFrame.add(studentButton);
        mainFrame.add(teacherButton);
        mainFrame.add(loginButton);
        mainFrame.add(guideButton);
        mainFrame.add(regEButton);
        mainFrame.add(regSButton);

        mainFrame.setSize(1650, 1080);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int password = Integer.parseInt(passwordFiled.getText());
                    int ID = Integer.parseInt(usernameField.getText());
                    int form = 0;
                    if (staffButton.isSelected()) {
                        form = 3;
                    } else if (studentButton.isSelected()) {
                        form = 1;
                    } else if (teacherButton.isSelected()) {
                        form = 2;
                    }
                    register.login(ID, password, form);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.dispose();
                } catch (SQLException | WrongpassException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        regEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                CommonPages.registerE(mainFrame);
            }
        });
        regSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                CommonPages.registerS(mainFrame);
            }
        });
    }

    public static void profileEdit(JFrame profPanel, int ID) {
        String notice = "*موارد ستاره دار ضروری بوده و خالی گذاشتن فیلد های غیر ضروری به منزله عدم تغییر در آنهاست";

        JLabel usernameLabel = new JLabel("رمز عبور قدیمی*"); //user label
        JPasswordField usernameField = new JPasswordField(11); //username prompt
        JLabel oldpassLabel = new JLabel("رمز عبور جدید*");    //old password label
        JPasswordField oldpasswordFiled = new JPasswordField(11); //old password prompt
        JLabel newpassLable = new JLabel(" تکرار رمز عبور جدید");    //new password label
        JPasswordField newpasswordFiled = new JPasswordField(10); //new password prompt
        JLabel newpassrepLable = new JLabel("شماره تلفن جدید"); //new pass repeat
        JTextField newpassrepField = new JTextField(11); //new pass repeat
        JLabel newphoneLable = new JLabel("کد پستی جدید");  //new phone number label
        JTextField newphoneField = new JTextField(11); //new phone prompt
        JLabel noticeLabel = new JLabel(notice); //notice label
        JButton subButton = new JButton("اعمال تغییرات"); //Done button
        JButton cancelButton = new JButton("انصراف"); //cancel button

        //xy
        usernameLabel.setBounds(100, 100, 100, 40);
        usernameField.setBounds(250, 100, 200, 30);
        oldpassLabel.setBounds(100, 150, 100, 40);
        oldpasswordFiled.setBounds(250, 150, 200, 30);
        newpassLable.setBounds(100, 200, 100, 40);
        newpasswordFiled.setBounds(250, 200, 200, 30);
        newpassrepLable.setBounds(100, 250, 100, 40);
        newpassrepField.setBounds(250, 250, 200, 30);
        newphoneLable.setBounds(100, 300, 100, 40);
        newphoneField.setBounds(250, 300, 200, 30);
        noticeLabel.setBounds(50, 270, 600, 300);
        subButton.setBounds(340, 510, 120, 30);
        cancelButton.setBounds(120, 510, 120, 30);

        //adding elements
        profPanel.add(usernameLabel);
        profPanel.add(usernameField);
        profPanel.add(oldpassLabel);
        profPanel.add(oldpasswordFiled);
        profPanel.add(newpassLable);
        profPanel.add(newpasswordFiled);
        profPanel.add(newpassrepLable);
        profPanel.add(newpassrepField);
        profPanel.add(newphoneLable);
        profPanel.add(newphoneField);
        profPanel.add(noticeLabel);
        profPanel.add(subButton);
        profPanel.add(cancelButton);

        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldpass = Integer.parseInt(usernameField.getText());
                int newpass1 = Integer.parseInt(oldpasswordFiled.getText());
                int newpass2 = Integer.parseInt(newpasswordFiled.getText());
                if (newpass1 == newpass2) {
                    System.out.println("password resetting");
                } else {
                    //throw exception
                }
                String num = newpassrepField.getText();
                String post = newphoneField.getText();
                try {
                    Register register = new Register();
                    if (ID < 100000) {
                        register.editT(ID, oldpass, newpass1, num, post);
                    } else if (ID < 10000000) {
                        register.editE(ID, oldpass, newpass1, num, post);
                    } else {
                        register.editS(ID, oldpass, newpass1, num, post);
                    }
                    System.out.println(ID + "   " + oldpass + "    " + newpass1 + "    " + newpass2 + "    " + "yayaya");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public static void leavestd(JFrame mainFrame, int ID) throws SQLException {
        Stdn stdn = new Stdn();
        JLabel restHoursLabel = new JLabel();
        JLabel titleLabel = new JLabel("ثبت مرخصی");
        JButton leaveSub = new JButton("ثبت");

        restHoursLabel.setText("آیا مایل به درخواست مرخصی در این ترم میباشید؟");

        titleLabel.setBounds(1000, 120, 80, 30);
        restHoursLabel.setBounds(800, 200, 300, 50);
        leaveSub.setBounds(800, 310, 90, 20);

        mainFrame.add(titleLabel);
        mainFrame.add(restHoursLabel);
        mainFrame.add(leaveSub);
        leaveSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stdn.Leave(ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public static void leavetchr(JFrame mainFrame, int ID) throws SQLException {
        Tchr tchr = new Tchr();
        JLabel restHoursLabel = new JLabel();
        JLabel titleLabel = new JLabel("ثبت مرخصی");
        JButton leaveSub = new JButton("ثبت");

        restHoursLabel.setText("آیا امروز مایل به درخواست مرخصی میباشید؟");

        titleLabel.setBounds(1000, 120, 80, 30);
        restHoursLabel.setBounds(800, 200, 300, 50);
        leaveSub.setBounds(800, 310, 90, 20);

        mainFrame.add(titleLabel);
        mainFrame.add(restHoursLabel);
        mainFrame.add(leaveSub);
        leaveSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tchr.Leave(ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public static void leaveemp(JFrame mainFrame, int ID) throws SQLException {
        Emp emp = new Emp();
        JLabel restHoursLabel = new JLabel();
        JLabel titleLabel = new JLabel("ثبت مرخصی");
        JButton leaveSub = new JButton("ثبت");

        restHoursLabel.setText("آیا امروز مایل به درخواست مرخصی میباشید؟");

        titleLabel.setBounds(1000, 120, 80, 30);
        restHoursLabel.setBounds(800, 200, 300, 50);
        leaveSub.setBounds(800, 310, 90, 20);

        mainFrame.add(titleLabel);
        mainFrame.add(restHoursLabel);
        mainFrame.add(leaveSub);
        leaveSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    emp.Leave(ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public static void contactWays(JFrame mainFrame) {
        JLabel phone = new JLabel("+98-912345678");
        JLabel email = new JLabel("juni@contact.com");
        JLabel postalCode = new JLabel("123456");
        JLabel address = new JLabel("خیابان شهدای ۸ ام، پلاک ۱۰۵۷");

        int xoff = 512;
        phone.setBounds(xoff, 200, 300, 30);
        email.setBounds(xoff, 260, 300, 30);
        postalCode.setBounds(xoff, 320, 300, 30);
        address.setBounds(xoff, 380, 300, 30);

        mainFrame.add(address);
        mainFrame.add(phone);
        mainFrame.add(email);
        mainFrame.add(postalCode);
    }

    public static void loan(JFrame mainFrame, int ID) {


            JLabel restHoursLabel = new JLabel();
            JLabel titleLabel = new JLabel("مقدار وام را وارد کنید");
            JTextField leaveValue = new JTextField();
            JButton leaveSub = new JButton("ثبت");

            restHoursLabel.setText("درخواست وام بدون بهره:" );

            titleLabel.setBounds(1000, 120, 200, 50);
            restHoursLabel.setBounds(800, 200, 300, 50);
            leaveValue.setBounds(800, 250, 200, 50);
            leaveSub.setBounds(800, 310, 90, 20);

            mainFrame.add(titleLabel);
            mainFrame.add(restHoursLabel);
            mainFrame.add(leaveValue);
            mainFrame.add(leaveSub);

            leaveSub.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int loan_amt = Integer.parseInt(leaveValue.getText());
                        Repository repository = new Repository();
                        repository.req_loan(loan_amt , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });

    }
}