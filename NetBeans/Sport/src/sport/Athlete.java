/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 哈哈
 */
public class Athlete extends javax.swing.JFrame {

    /**
     * Creates new form Athlete
     */
    public Athlete() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        GRXX = new javax.swing.JButton();
        XMCX = new javax.swing.JButton();
        BJDF = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        grxx = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        xmcx = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        bjdf = new javax.swing.JTable();
        BHCX = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        bhcx = new javax.swing.JTable();
        ZHCX = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        zhcx = new javax.swing.JTable();
        CJCX = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        cjcx = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Athlete");

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setToolTipText("Welcome！！！");

        jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome！！！");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GRXX.setText("选手信息");
        GRXX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRXXActionPerformed(evt);
            }
        });

        XMCX.setText("项目查询");
        XMCX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XMCXActionPerformed(evt);
            }
        });

        BJDF.setText("班级得分");
        BJDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BJDFActionPerformed(evt);
            }
        });

        grxx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "学号", "姓名", "性别", "班级", "学院", "项目1", "项目2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(grxx);

        xmcx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "项目编号", "项目名称", "预赛时间", "决赛时间"
            }
        ));
        jScrollPane1.setViewportView(xmcx);

        bjdf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "班级", "学院", "得分"
            }
        ));
        jScrollPane2.setViewportView(bjdf);

        BHCX.setText("编号查询");
        BHCX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHCXActionPerformed(evt);
            }
        });

        bhcx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "编号", "学号", "姓名"
            }
        ));
        jScrollPane5.setViewportView(bhcx);

        ZHCX.setText("组号查询");
        ZHCX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZHCXActionPerformed(evt);
            }
        });

        zhcx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "组号", "项目号", "运动员编号"
            }
        ));
        jScrollPane6.setViewportView(zhcx);

        CJCX.setText("成绩查询");
        CJCX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CJCXActionPerformed(evt);
            }
        });

        cjcx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "运动员编号", "项目号", "项目名", "预赛成绩", "预赛排名", "决赛成绩", "决赛排名"
            }
        ));
        jScrollPane7.setViewportView(cjcx);

        jButton1.setFont(new java.awt.Font("黑体", 1, 24)); // NOI18N
        jButton1.setText("退出登录");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(85, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CJCX)
                            .addComponent(ZHCX)
                            .addComponent(XMCX)
                            .addComponent(GRXX)
                            .addComponent(BJDF)
                            .addComponent(BHCX))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane7)))))
                .addGap(53, 53, 53))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GRXX))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XMCX))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BJDF))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BHCX)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ZHCX)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CJCX)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void XMCXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XMCXActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) xmcx.getModel();
        dtm.setRowCount(0);
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
        //MySQL配置时的用户名
        String user = "haha";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql;
            sql = "select Ino,Iname,First_time,Last_time from Item";
            try (ResultSet rs = statement.executeQuery(sql)) {
                String ino = null;
                String iname = null;
                String first_time = null;
                String last_time = null;            
                while (rs.next()) {
                    //获取Bnum这列数据
                    ino = rs.getString("Ino");
                    iname = rs.getString("Iname");
                    first_time = rs.getString("First_time");
                    last_time = rs.getString("Last_time");
                    //输出结果
                    Vector al = new Vector();
                    al.add(ino);
                    al.add(iname);
                    al.add(first_time);
                    al.add(last_time);
                    dtm.addRow(al);
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }


    }//GEN-LAST:event_XMCXActionPerformed

    private void GRXXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRXXActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) grxx.getModel();
        dtm.setRowCount(0);
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
        //MySQL配置时的用户名
        String user = "haha";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql;
            sql = "select Sno,Sname,Ssex,Sclass,Sdept,Iname1,Iname2 from Athlete";
            try (ResultSet rs = statement.executeQuery(sql)) {
                String sno = null;
                String sname = null;
                String ssex = null;
                String sclass = null;
                String sdept = null;
                String iname1 = null;
                String iname2 = null;
                while (rs.next()) {
                    //获取Bnum这列数据
                    sno = rs.getString("Sno");
                    sname = rs.getString("Sname");
                    ssex = rs.getString("Ssex");
                    sclass = rs.getString("Sclass");
                    sdept = rs.getString("Sdept");
                    iname1 = rs.getString("Iname1");
                    iname2 = rs.getString("Iname2");
                    //输出结果

                    Vector al = new Vector();
                    al.add(sno);
                    al.add(sname);
                    al.add(ssex);
                    al.add(sclass);
                    al.add(sdept);
                    al.add(iname1);
                    al.add(iname2);

                    dtm.addRow(al);
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
    }//GEN-LAST:event_GRXXActionPerformed

    private void BJDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BJDFActionPerformed
       DefaultTableModel dtm =(DefaultTableModel)bjdf.getModel();
        dtm.setRowCount(0);
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
        //MySQL配置时的用户名
        String user = "haha";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql;
            sql = "select * from Class";
             try (ResultSet rs = statement.executeQuery(sql)) {
                 String cname = null;
                 String cdept = null;
                 String cgrade = null;
               
                 while(rs.next()){
                     //获取Bnum这列数据
                     cname = rs.getString("Cname");
                     cdept = rs.getString("Cdept");
                     cgrade = rs.getString("Cgrade");
                
                     //输出结果
                     
                     Vector al = new Vector();
                     al.add(cname);
                     al.add(cdept);
                     al.add(cgrade);
                   
                     
                     dtm.addRow(al);
                 }}
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }//GEN-LAST:event_BJDFActionPerformed

    private void BHCXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHCXActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dtm =(DefaultTableModel)bhcx.getModel();
        dtm.setRowCount(0);
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
        //MySQL配置时的用户名
        String user = "haha";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql;
            sql = "select * from Athlete_number";
             try (ResultSet rs = statement.executeQuery(sql)) {
                 String ano = null;
                 String sno = null;
                 String sname = null;
                 while(rs.next()){
                     //获取Bnum这列数据
                     ano = rs.getString("Ano");
                     sno = rs.getString("Sno");
                     sname = rs.getString("Sname");

                     //输出结果
                     
                     Vector al = new Vector();
                     al.add(ano);
                     al.add(sno);
                     al.add(sname);
               
                     
                     dtm.addRow(al);
                 }}
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }//GEN-LAST:event_BHCXActionPerformed

    private void ZHCXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZHCXActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dtm =(DefaultTableModel)zhcx.getModel();
        dtm.setRowCount(0);
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
        //MySQL配置时的用户名
        String user = "haha";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql;
            sql = "select * from Groups";
             try (ResultSet rs = statement.executeQuery(sql)) {
                 String gno = null;
                 String ino = null;
                 String ano = null;

                 while(rs.next()){
                     //获取Bnum这列数据
                     gno = rs.getString("Gno");
                     ino = rs.getString("Ino");
                     ano = rs.getString("Ano");

                     //输出结果
                     
                     Vector al = new Vector();
                     al.add(gno);
                     al.add(ino);
                     al.add(ano);

                     
                     dtm.addRow(al);
                 }}
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }//GEN-LAST:event_ZHCXActionPerformed

    private void CJCXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CJCXActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dtm =(DefaultTableModel)cjcx.getModel();
        dtm.setRowCount(0);
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
        //MySQL配置时的用户名
        String user = "haha";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql;
            sql = "select * from Grade";
             try (ResultSet rs = statement.executeQuery(sql)) {
                 String ano = null;
                 String ino = null;
                 String iname = null;
                 String first_grade = null;
                 String first_rank = null;
                 String last_grade = null;
                 String last_rank = null;
                 while(rs.next()){
                     //获取Bnum这列数据
                     ano = rs.getString("Ano");
                     ino = rs.getString("Ino");
                     iname = rs.getString("Iname");
                     first_grade = rs.getString("First_grade");
                     first_rank = rs.getString("First_rank");
                     last_grade = rs.getString("Last_grade");
                     last_rank = rs.getString("Last_rank");
                     //输出结果
                     
                     Vector al = new Vector();
                     al.add(ano);
                     al.add(ino);
                     al.add(iname);
                     al.add(first_grade);
                     al.add(first_rank);
                     al.add(last_grade);
                     al.add(last_rank);
                     
                     dtm.addRow(al);
                 }}
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }//GEN-LAST:event_CJCXActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         Loading lg = new Loading();
        lg.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Athlete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Athlete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Athlete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Athlete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Athlete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BHCX;
    private javax.swing.JButton BJDF;
    private javax.swing.JButton CJCX;
    private javax.swing.JButton GRXX;
    private javax.swing.JButton XMCX;
    private javax.swing.JButton ZHCX;
    private javax.swing.JTable bhcx;
    private javax.swing.JTable bjdf;
    private javax.swing.JTable cjcx;
    private javax.swing.JTable grxx;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable xmcx;
    private javax.swing.JTable zhcx;
    // End of variables declaration//GEN-END:variables
}
