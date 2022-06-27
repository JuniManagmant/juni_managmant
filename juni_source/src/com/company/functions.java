package com.company;
import javax.swing.*;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.awt.*;
import java.awt.event.*;


public class functions{

    public static class menuBar {
        public static void student(JFrame mainFrame , int ID) throws SQLException {
            Stdn stdn = new Stdn();
            Repository repository = new Repository();
            String name = repository.readname("student" , "name", "ID = "+ ID);

            //mainFrame
            //JFrame mainFrame = new JFrame("صفحه اصلی");

            //variables
            JMenuBar studentMB = new JMenuBar();
            JMenu menuTahsili = new JMenu("امور تحصیلی");
            JMenu menuMali = new JMenu("امور مالی");
            JMenu menuEdari = new JMenu("امور اداری");
            JMenu menuExtra = new JMenu("متفرقه");
            /* --tahsili menu submenus-- */
            JMenuItem tahsiliHazf = new JMenuItem("حذف و اضافه");
            JMenuItem tahsiliEntekhab = new JMenuItem("انتخاب واحد");
            JMenuItem tahsiliMoshahedeD = new JMenuItem("مشاهده دروس");
            JMenuItem tahsiliArzeshyabi = new JMenuItem("ارزش یابی استاد");
            JMenuItem tahsiliMoshahedeV = new JMenuItem("مشاهده وضعیت تحصیلی");
            JMenu tahsiliKarname = new JMenu("کارنامه");
            /* --karname submenu-- */
            JMenuItem karnameMoshahede = new JMenuItem("مشاهده کارنامه");
            /* --mali menu submenu-- */
            JMenuItem maliStatusPay = new JMenuItem("پرداخت و مشاهده تراز مالی");
            JMenuItem maliVaam = new JMenuItem("درخواست وام");
            /* --edari menu submenu-- */
            JMenuItem edariMorakhasi = new JMenuItem("ثبت مرخصی");
            JMenuItem edariEshteghal = new JMenuItem("درخواست فرم اشتغال به تحصیلی");
            /* --extra menu submenu-- */
            JMenuItem extraProfedit = new JMenuItem("ویرایش پروفایل");
            JMenuItem extraContact = new JMenuItem("راه های ارتباطی");

            //adding submenus to menubar
            studentMB.add(menuTahsili);
            studentMB.add(menuMali);
            studentMB.add(menuEdari);
            studentMB.add(menuExtra);
            /* --tahsili submenu-- */
            menuTahsili.add(tahsiliEntekhab);
            menuTahsili.add(tahsiliHazf);
            menuTahsili.add(tahsiliMoshahedeD);
            menuTahsili.add(tahsiliArzeshyabi);
            menuTahsili.add(tahsiliMoshahedeV);
            menuTahsili.add(tahsiliKarname);
            /* .:karname submenu:. */
            tahsiliKarname.add(karnameMoshahede);
            /* --mali submenu-- */
            menuMali.add(maliStatusPay);
            menuMali.add(maliVaam);
            /* --edari submenu-- */
            menuEdari.add(edariMorakhasi);
            menuEdari.add(edariEshteghal);
            /* --extra submenu-- */
            menuExtra.add(extraProfedit);
            menuExtra.add(extraContact);

            //adding elements to mainFrame
            mainFrame.setJMenuBar(studentMB);

            //mainFrame init
            mainFrame.setSize(1270,1000);
            mainFrame.setLayout(null);
            mainFrame.setVisible(true);
            extraContact.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.contactWays(mainFrame);
                    Repository repository = null;
                    try {
                        mainFrame.revalidate();
                        repository = new Repository();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String name = null;
                    try {
                        name = repository.readname("student" , "name", "ID = "+ ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    functions.header.showHeader(mainFrame, name , ID);
                }


            });
            edariMorakhasi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        mainFrame.revalidate();
                        mainFrame.getContentPane().removeAll();
                        CommonPages.leavestd(mainFrame,ID);
                        Repository repository = new Repository();
                        String name = repository.readname("student" , "name", "ID = "+ ID);
                        functions.header.showHeader(mainFrame, name , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            extraProfedit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.revalidate();
                    mainFrame.getContentPane().removeAll();
                    CommonPages.profileEdit(mainFrame , ID);
                    Repository repository = null;
                    try {
                        repository = new Repository();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String name = null;
                    try {
                        name = repository.readname("student" , "name", "ID = "+ ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });

            maliVaam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.revalidate();
                    mainFrame.getContentPane().removeAll();
                    CommonPages.loan(mainFrame , ID);
                    Repository repository = null;
                    try {
                        repository = new Repository();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String name = null;
                    try {
                        name = repository.readname("student" , "name", "ID = "+ ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            maliStatusPay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        mainFrame.getContentPane().removeAll();
                        studentPages.payment(mainFrame , ID);
                        Repository repository = new Repository();
                        String name = repository.readname("student" , "name", "ID = "+ ID);
                        functions.header.showHeader(mainFrame, name , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
           // ______________________________________________________________________________________
            tahsiliHazf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    studentPages.removeUnit(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });

            tahsiliEntekhab.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    studentPages.unitSelect(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);


                }
            });

            tahsiliMoshahedeD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    studentPages.viewUnits(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });

            tahsiliArzeshyabi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    studentPages.teacherGrading(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });


            karnameMoshahede.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    studentPages.showKarname(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            edariEshteghal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    studentPages.eshteghal(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
        }
        public static void teacher(JFrame mainFrame, int ID) throws SQLException {
            Repository repository = new Repository();
            String name = repository.readname("student" , "name", "ID = "+ ID);
            //mainFrame
            //JFrame mainFrame = new JFrame("صفحه اصلی");

            //variables
            JMenuBar teacherMB = new JMenuBar();
            JMenu menuTahsili = new JMenu("امور تحصیلی");
            JMenu menuMali = new JMenu("امور مالی");
            JMenu menuEdari = new JMenu("امور اداری");
            JMenu menuExtra = new JMenu("متفرقه");
            /* --tahsili menu submenus-- */
            JMenuItem tahsiliNomre = new JMenuItem("نمره دهی");
            JMenuItem tahsiliHazf = new JMenuItem("حذف دانشجو");
            JMenuItem tahsiliMoshahedeD = new JMenuItem("مشاهده دروس");
            JMenuItem tahsiliMoshDan = new JMenuItem("مشاهده لیست دانشجویان");
            /* --mali menu submenu-- */
            JMenuItem maliVaziyat = new JMenuItem("مشاهده تراز مالی");
            JMenuItem maliVaam = new JMenuItem("درخواست وام");
            /* --edari menu submenu-- */
            JMenuItem edariMorakhasi = new JMenuItem("ثبت مرخصی");
            /* --extra menu submenu-- */
            JMenuItem extraProfedit = new JMenuItem("ویرایش پروفایل");
            JMenuItem extraContact = new JMenuItem("راه های ارتباطی");


            //adding submenus to menubar
            teacherMB.add(menuTahsili);
            teacherMB.add(menuMali);
            teacherMB.add(menuEdari);
            teacherMB.add(menuExtra);
            /* --tahsili submenu-- */
            menuTahsili.add(tahsiliNomre);
            menuTahsili.add(tahsiliHazf);
            menuTahsili.add(tahsiliMoshahedeD);
            menuTahsili.add(tahsiliMoshDan);
            /* --mali submenu-- */
            menuMali.add(maliVaziyat);
            menuMali.add(maliVaam);
            /* --edari submenu-- */
            menuEdari.add(edariMorakhasi);
            /* --extra submenu-- */
            menuExtra.add(extraProfedit);
            menuExtra.add(extraContact);


            //adding elements to mainFrame
            mainFrame.setJMenuBar(teacherMB);

            //mainFrame init
            mainFrame.setSize(1270,1000);
            mainFrame.setLayout(null);
            mainFrame.setVisible(true);
            extraContact.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.contactWays(mainFrame);
                    functions.header.showHeader(mainFrame, name , ID);
                }


            });
            edariMorakhasi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        mainFrame.getContentPane().removeAll();
                        CommonPages.leavetchr(mainFrame,ID);
                        functions.header.showHeader(mainFrame, name , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            extraProfedit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.profileEdit(mainFrame , ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            maliVaam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.loan(mainFrame , ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            maliVaziyat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        mainFrame.getContentPane().removeAll();
                        studentPages.payment(mainFrame , ID);
                        functions.header.showHeader(mainFrame, name , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            tahsiliNomre.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    functions.header.showHeader(mainFrame, name , ID);
                    teacherPages.grading(mainFrame,ID);
                }
            });
            tahsiliHazf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    teacherPages.DropStudent(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            tahsiliMoshahedeD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    teacherPages.viewCourses(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });


            tahsiliMoshDan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    teacherPages.viewStudents(mainFrame,ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });

        }

        public static void staff(JFrame mainFrame, int ID) throws SQLException {
            Repository repository = new Repository();
            String name = repository.readname("student" , "name", "ID = "+ ID);
            //mainFrame
            //JFrame mainFrame = new JFrame("صفحه اصلی");

            //variables
            JMenuBar staffMB = new JMenuBar();
            JMenu menuMali = new JMenu("امور مالی");
            JMenu menuEdari = new JMenu("امور اداری");
            JMenu menuExtra = new JMenu("متفرقه");
            /* --mali menu submenu-- */
            JMenuItem maliVaziyat = new JMenuItem("مشاهده تراز مالی");
            JMenuItem maliVaam = new JMenuItem("درخواست وام");
            /* --edari menu submenu-- */
            JMenuItem edariMorakhasi = new JMenuItem("ثبت مرخصی");
            /* --extra menu submenu-- */
            JMenuItem extraProfedit = new JMenuItem("ویرایش پروفایل");
            JMenuItem extraContact = new JMenuItem("راه های ارتباطی");


            //adding submenus to menubar
            staffMB.add(menuMali);
            staffMB.add(menuEdari);
            staffMB.add(menuExtra);
            /* --mali submenu-- */
            menuMali.add(maliVaziyat);
            menuMali.add(maliVaam);
            /* --edari submenu-- */
            menuEdari.add(edariMorakhasi);
            /* --extra submenu-- */
            menuExtra.add(extraProfedit);
            menuExtra.add(extraContact);


            //adding elements to mainFrame
            mainFrame.setJMenuBar(staffMB);

            //mainFrame init
            mainFrame.setSize(1270,1000);
            mainFrame.setLayout(null);
            mainFrame.setVisible(true);
            extraContact.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.contactWays(mainFrame);
                    functions.header.showHeader(mainFrame, name , ID);
                }


            });
            edariMorakhasi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        mainFrame.getContentPane().removeAll();
                        CommonPages.leaveemp(mainFrame,ID);
                        functions.header.showHeader(mainFrame, name , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            extraProfedit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.profileEdit(mainFrame , ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            maliVaam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();
                    CommonPages.loan(mainFrame , ID);
                    functions.header.showHeader(mainFrame, name , ID);
                }
            });
            maliVaziyat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        mainFrame.getContentPane().removeAll();
                        studentPages.payment(mainFrame , ID);
                        functions.header.showHeader(mainFrame, name , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });


        }
    }
    public static class header {
        public static void showHeader(JFrame mainFrame, String name , int ID){

            JButton mainMenu = new JButton("صفحه اصلی");
            JLabel nameLabel = new JLabel();
            JLabel lastLogin = new JLabel();
            JButton exitButton = new JButton("خروج");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();
            // dtf.format(now) it return current time

            // time and name label values
            lastLogin.setText(dtf.format(now));
            nameLabel.setText(name);

            // xy
            mainMenu.setBounds(300,40,150,30);
            exitButton.setBounds(40,40,80,30);
            nameLabel.setBounds(150,40,120,30);
            lastLogin.setBounds(210,40,100,30);

            // adding
            mainFrame.add(mainMenu);
            mainFrame.add(exitButton);
            mainFrame.add(nameLabel);
            mainFrame.add(lastLogin);

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getContentPane().removeAll();


                    try {
                        menuBar.student(mainFrame , ID);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        CommonPages.login(mainFrame);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            mainMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    teacherPages.mainPage(mainFrame,ID);
                    showHeader(mainFrame,name,ID);
                }
            });

        }
    }
}
