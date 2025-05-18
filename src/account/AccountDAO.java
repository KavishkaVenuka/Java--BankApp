package account;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_connector.DBConnect;
import user.userDAO;

public class AccountDAO {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean saveAccountToDB(String acctype, long userNIC) {
        String query = "INSERT INTO account(account_type, balance, user_id) VALUES (?, ?, ?)";
        double balance = 0;
    
        try (
            Connection conn = DBConnect.getConnectionInstance();
            PreparedStatement stmnt = conn.prepareStatement(query)
        ) {
            stmnt.setString(1, acctype);
            stmnt.setDouble(2, balance);
            stmnt.setLong(3, userNIC);
    
            int insertedRow = stmnt.executeUpdate();
            System.out.println("Inserted rows: " + insertedRow); // DEBUG line
    
            return insertedRow > 0;
    
        } catch (SQLException e) {
            System.out.println("SQL Insert failed!");
            e.printStackTrace();
            return false;
        }
    }



    public static boolean saveAuthenticateToDB(String username, String password, long userNIC){
        String query = "INSERT INTO authenticate(username, password, user_id) VALUES(?, ?, ?)";

        try(Connection conn = DBConnect.getConnectionInstance();
            PreparedStatement stmnt = conn.prepareStatement(query)){
                stmnt.setString(1, username);
                stmnt.setString(2, password);
                stmnt.setLong(3, userNIC);

                int insertedRow = stmnt.executeUpdate();
                return insertedRow > 0;

            }
            catch (SQLException e){
                e.printStackTrace();
                return false;

            }

    }


    public static boolean depositeToDB(long userNIC){
        while (true){
            System.out.print("Enter the amount for deposite : ");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount <= 0){
                    System.out.println("Deposite amount should be possitive!");
                    continue;
                }
                double balance = getAccountBalance(userNIC);
                balance = balance + amount;
                Boolean isSuccessed = setAccountBalance(userNIC, balance);
                System.out.println("ISSuccess : " + isSuccessed);
                return isSuccessed;
            }

            catch (InputMismatchException e){
                System.out.println("Invalid value ! Try Again !");
                scanner.nextLine();
            }
        }
    }



    public static boolean withdrawToDB(long userNIC, double amount){
        double balance = getAccountBalance(userNIC);
        if (balance > amount){
            balance = balance - amount;
        }
        else {
            System.out.println("Account balance is not sufficient !");
            return false;
        }

        String query = "UPDATE account SET balance = ? WHERE user_id = ?";
        try(Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            
            stmnt.setDouble(1, balance);
            stmnt.setLong(2, userNIC);
            int insertedRow = stmnt.executeUpdate();
            return insertedRow > 0;
         
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;

        }

    }


    public static boolean transferToDB(long userNIC, int recieverAccNo){
        SavingAcc savingObj = SavingAcc.getSavingAccObj();
        System.out.print("Enter the amount to transfer : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        savingObj.withdraw(userNIC, amount);

        long recieverNIC = getUserNIC(recieverAccNo);
        String nameWithInitials = userDAO.getnameWithInitials(recieverNIC);
        System.out.println(" ");
        System.out.println("_______________________________________________________");
        System.out.println("Account holder : " + nameWithInitials);
        System.out.println("Account no : " + recieverAccNo);
        System.out.println("Transfered amount : " + amount);
        System.out.println("_______________________________________________________");

        double recieverBalance = getAccountBalance(recieverNIC);
        recieverBalance = recieverBalance + amount;
        String query =  "UPDATE account SET balance = ? WHERE user_id = ?";
        try (Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            stmnt.setDouble(1, recieverBalance);
            stmnt.setLong(2, recieverNIC);
            int insertedRow = stmnt.executeUpdate();
            return insertedRow > 0;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;

        }

    }



    public static long getUserNIC(int recieverAccNo ){
        String query = "SELECT user_id FROM account WHERE account_no = ?";
        
        try (Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            stmnt.setInt(1, recieverAccNo);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()){
                long userNIC = rs.getLong("user_id");
                return userNIC;
            }
            else {
                return 0;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return getUserNIC(recieverAccNo);
        }

    }



    private static double getAccountBalance(long userNIC){
        String query = "SELECT balance FROM account WHERE user_id = ?";

        try (Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            stmnt.setLong(1, userNIC);
            ResultSet st = stmnt.executeQuery();
            double balance;

            if (st.next()){
                balance = st.getDouble("balance");
            }
            else {
                balance = 0;
            }
            return balance;
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return getAccountBalance(userNIC);
        }

    }



    private static boolean setAccountBalance(long userNIC, double value){
        String query = "UPDATE account SET balance = ? WHERE user_id = ?";

        try(Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            stmnt.setDouble(1, value);
            stmnt.setLong(2, userNIC);
            
            int insertedRow = stmnt.executeUpdate();
            return insertedRow > 0;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }



    public static long getUserNIC(String username){
        String query = "SELECT user_id FROM authenticate WHERE  username = ?";

        try (Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            stmnt.setString(1, username);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()){
                long userNIC = rs.getLong("user_id");
                return userNIC;
            }
            else{
                System.out.println("User not found!");
                return -1;
            }
            
            
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        
    }

    
}
