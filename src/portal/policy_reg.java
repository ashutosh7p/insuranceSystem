package portal;

import java.sql.*;
import javax.swing.JOptionPane;

public class policy_reg extends javax.swing.JFrame {
    
    public policy_reg() {
        initComponents();
        loadCustomers();
    }
    
    public void loadCustomers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection con = DriverManager.getConnection(DatabaseConfig.getConnectionUrl(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword());
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from n_customer");
            while (rs.next()) {
                jComboBox1.addItem(rs.getString(1) + " - " + rs.getString(2));
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
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48));
        jLabel1.setText("Insurance System");
        
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setText("Policy Registration");
        
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel3.setText("Customer");
        
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel4.setText("Policy Name");
        
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel5.setText("Amount to Deposit");
        
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel6.setText("Monthly Payment");
        
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 12));
        
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jButton1.setText("Register Policy");
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
                        .addGap(240, 240, 240)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 250, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
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
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(50, Short.MAX_VALUE))
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
        String selected = (String) jComboBox1.getSelectedItem();
        if (selected == null || selected.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a customer!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String cid = selected.split(" - ")[0];
        String policyName = jTextField1.getText();
        String deposit = jTextField2.getText();
        String monthly = jTextField3.getText();
        
        if (policyName.isEmpty() || deposit.isEmpty() || monthly.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            Connection con = DriverManager.getConnection(DatabaseConfig.getConnectionUrl(), DatabaseConfig.getUsername(), DatabaseConfig.getPassword());
            PreparedStatement ps = con.prepareStatement("insert into n_policy(cid, policy_name, deposit_amount, monthly_payment) values(?,?,?,?)");
            ps.setInt(1, Integer.parseInt(cid));
            ps.setString(2, policyName);
            ps.setDouble(3, Double.parseDouble(deposit));
            ps.setDouble(4, Double.parseDouble(monthly));
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Policy Registered Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jComboBox1.setSelectedIndex(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Registration Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for amounts!", "Error", JOptionPane.ERROR_MESSAGE);
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
                new policy_reg().setVisible(true);
            }
        });
    }
    
    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration
}
