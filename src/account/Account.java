package account;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Account {
    
    protected  int accountNo;
    protected String username;
    protected String password;
    protected double balance;
    private static Scanner scanner = new Scanner(System.in);



    public void deposite(long userNIC){
        while (true){
            Boolean isSuccessed= AccountDAO.depositeToDB(userNIC);
            if (isSuccessed){
                System.out.println("Deposite completed ! ");
            }
            else {
                System.out.println("Deposite fails! Try again!");
            }
            System.out.println("Enter 1 : Deposite more");
            System.out.println("Enter 0 : Exit to the previous menu");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice == 0){
                    return ;
                }
                else {
                    System.out.println(" ");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid operation! Try again!");
                System.out.println(" ");
            }
        }
    }


    public void withdraw(long userNIC){
        while (true){
            System.out.print("Amount to withdraw (LKR) : ");
            try {
                double amount = scanner.nextDouble();
                Boolean isSuccessed = AccountDAO.withdrawToDB(userNIC, amount);
                if (isSuccessed){
                    System.out.println("Withdraw is completed !");
                    System.out.println("Enter 1 : Withdraw more");
                    System.out.println("Enter 0 : Exit the previous menu");
                    try{
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 0){
                            return;
                        }
                        else {
                            System.out.println(" ");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid operation! Try again!");
                        System.out.println(" ");
                    }
                }
                else {
                    withdraw(userNIC);
                }

            }
            catch (InputMismatchException e){
                System.out.println("Invalid amount ! Try again !!!");
                withdraw(userNIC);
            }
        }

    }


    public void withdraw(long userNIC, double amount){
        
        try {
            Boolean isSuccessed = AccountDAO.withdrawToDB(userNIC, amount);
            if (isSuccessed){
                System.out.println("Withdraw is completed !");
            }
            else {
                withdraw(userNIC);
            }
        }
        catch (InputMismatchException e){
            System.out.println("Invalid amount ! Try again !!!");
            withdraw(userNIC);
        }

    }



    public void transfer(long senderNIC){
        System.out.print("Enter reciver's account no : ");
        try {
            int recieverAccno = scanner.nextInt();
            scanner.nextLine();
            Boolean isSuccessed = AccountDAO.transferToDB(senderNIC, recieverAccno);
            if (isSuccessed){
                System.out.println("Transaction successed !");
            }else {
                System.out.println("Transaction is unsucessfull !");
                System.out.println(" ");
            }
            System.out.println("Enter 1 : Transfer more");
            System.out.println("Enter 0 : Exit to the previous menu");
            System.out.print("Choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0){
                return;
            }
            else{
                System.out.println(" ");
                transfer(senderNIC); 
            
                
            }
            
        }
        catch (InputMismatchException e){
            System.out.println("Invalid account number ! Try again !!!");
            transfer(senderNIC);
        }

    }



    public static String getUsername(){
        System.out.print("Enter username : ");
        String username = scanner.nextLine();
        return username;
    }



    public static String getPassword(){
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        return password;
    }


    public int accountOperationMenu(){
        System.out.println("Enter 1 ; Deposite");
        System.out.println("Enter 2 : Withdraw");
        System.out.println("Enter 3 : Transfer");
        System.out.println("Enter 0 : Exit to the previous menu");
        System.out.print("Choice : ");
        try {
            int choice = scanner.nextInt();
            return choice;
        }
        catch (InputMismatchException e){
            System.out.println("Invalid operation ! Please Try Again !");
            return accountOperationMenu();
            
        }
        
    }

 
}
