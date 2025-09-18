package portal;

import java.sql.*;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {
    
    public Register() {
        initComponents();
        loadCustomerId();
    }
    
    public void loadCustomerId() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection con = DriverManager.getConnection(DatabaseConfig.getConnectionUrl(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword());
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select count(*) from n_customer");
            while (rs.next()) {
                jTextField1.setText("" + (rs.getInt(1) + 1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48));
        jLabel1.setText("Insurance System");
        
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setText("Customer Registration");
        
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel3.setText("Customer ID");
        
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel4.setText("Customer Name");
        
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel5.setText("Address");
        
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel6.setText("Contact");
        
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel7.setText("Occupation");
        
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel8.setText("Bank Name");
        
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel9.setText("Account Number");
        
        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 12));
        
        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jTextField5.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jTextField6.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jTextField7.setFont(new java.awt.Font("Times New Roman", 0, 12));
        
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jScrollPane1.setViewportView(jTextArea1);
        
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int cid;
        String cname, address, contact, occupation, bname, accno;
        
        cid = Integer.parseInt(jTextField1.getText());
        cname = jTextField2.getText();
        address = jTextArea1.getText();
        contact = jTextField4.getText();
        occupation = jTextField5.getText();
        bname = jTextField6.getText();
        accno = jTextField7.getText();
        
        if (cname.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            Connection con = DriverManager.getConnection(DatabaseConfig.getConnectionUrl(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword());
            PreparedStatement ps = con.prepareStatement("insert into n_customer values(?,?,?,?,?,?,?)");
            ps.setInt(1, cid);
            ps.setString(2, cname);
            ps.setString(3, address);
            ps.setString(4, contact);
            ps.setString(5, occupation);
            ps.setString(6, bname);
            ps.setString(7, accno);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Customer Registered Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields
            jTextField2.setText("");
            jTextArea1.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            loadCustomerId();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Registration Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new Menus().setVisible(true);
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
    
    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration
}
