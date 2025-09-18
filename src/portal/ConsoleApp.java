package portal;

import java.sql.*;
import java.util.Scanner;

public class ConsoleApp {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    Insurance Management System");
        System.out.println("        Console Version");
        System.out.println("========================================");
        
        // Test database connection
        if (!testDatabaseConnection()) {
            System.out.println("Failed to connect to database. Exiting...");
            return;
        }
        
        System.out.println("\nDatabase connected successfully!");
        
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    listCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    listPolicies();
                    break;
                case 4:
                    addPolicy();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static boolean testDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                DatabaseConfig.getConnectionUrl(), 
                DatabaseConfig.getUsername(), 
                DatabaseConfig.getPassword()
            );
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
            return false;
        }
    }
    
    private static void showMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. List All Customers");
        System.out.println("2. Add New Customer");
        System.out.println("3. List All Policies");
        System.out.println("4. Add New Policy");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void listCustomers() {
        System.out.println("\n========== CUSTOMERS ==========");
        try {
            Connection con = DriverManager.getConnection(
                DatabaseConfig.getConnectionUrl(), 
                DatabaseConfig.getUsername(), 
                DatabaseConfig.getPassword()
            );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM n_customer");
            
            System.out.printf("%-5s %-20s %-30s %-15s %-15s%n", 
                "ID", "Name", "Address", "Contact", "Occupation");
            System.out.println("-".repeat(85));
            
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-30s %-15s %-15s%n",
                    rs.getInt("cid"),
                    rs.getString("cname"),
                    rs.getString("address").length() > 30 ? 
                        rs.getString("address").substring(0, 27) + "..." : 
                        rs.getString("address"),
                    rs.getString("contact"),
                    rs.getString("occupation")
                );
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void addCustomer() {
        System.out.println("\n========== ADD NEW CUSTOMER ==========");
        try {
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter Contact: ");
            String contact = scanner.nextLine();
            
            System.out.print("Enter Occupation: ");
            String occupation = scanner.nextLine();
            
            System.out.print("Enter Bank Name: ");
            String bankName = scanner.nextLine();
            
            System.out.print("Enter Account Number: ");
            String accNo = scanner.nextLine();
            
            Connection con = DriverManager.getConnection(
                DatabaseConfig.getConnectionUrl(), 
                DatabaseConfig.getUsername(), 
                DatabaseConfig.getPassword()
            );
            
            // Get next customer ID
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(cid) FROM n_customer");
            int nextId = 1;
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO n_customer VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, nextId);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, contact);
            ps.setString(5, occupation);
            ps.setString(6, bankName);
            ps.setString(7, accNo);
            
            ps.executeUpdate();
            System.out.println("Customer added successfully with ID: " + nextId);
            
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void listPolicies() {
        System.out.println("\n========== POLICIES ==========");
        try {
            Connection con = DriverManager.getConnection(
                DatabaseConfig.getConnectionUrl(), 
                DatabaseConfig.getUsername(), 
                DatabaseConfig.getPassword()
            );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT p.*, c.cname FROM n_policy p " +
                "JOIN n_customer c ON p.cid = c.cid"
            );
            
            System.out.printf("%-5s %-20s %-25s %-15s %-15s%n", 
                "CID", "Customer Name", "Policy Name", "Deposit", "Monthly");
            System.out.println("-".repeat(80));
            
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-25s $%-14.2f $%-14.2f%n",
                    rs.getInt("cid"),
                    rs.getString("cname"),
                    rs.getString("policy_name"),
                    rs.getDouble("deposit_amount"),
                    rs.getDouble("monthly_payment")
                );
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void addPolicy() {
        System.out.println("\n========== ADD NEW POLICY ==========");
        
        // First show available customers
        listCustomers();
        
        try {
            System.out.print("\nEnter Customer ID: ");
            int cid = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            System.out.print("Enter Policy Name: ");
            String policyName = scanner.nextLine();
            
            System.out.print("Enter Deposit Amount: ");
            double deposit = scanner.nextDouble();
            
            System.out.print("Enter Monthly Payment: ");
            double monthly = scanner.nextDouble();
            
            Connection con = DriverManager.getConnection(
                DatabaseConfig.getConnectionUrl(), 
                DatabaseConfig.getUsername(), 
                DatabaseConfig.getPassword()
            );
            
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO n_policy (cid, policy_name, deposit_amount, monthly_payment) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, cid);
            ps.setString(2, policyName);
            ps.setDouble(3, deposit);
            ps.setDouble(4, monthly);
            
            ps.executeUpdate();
            System.out.println("Policy added successfully!");
            
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
