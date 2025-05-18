package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import account.Account;
import account.AccountDAO;
import account.CurrentAcc;
import account.SavingAcc;
import authenticate.Authenticate;
import builder.TheUserBuilder;
import user.User;

public class home {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        while (true){
            int firstChoice = mainmenu();
            switch (firstChoice){
                case 1:
                    createAccountMenu();
                    break;

                case 2 :
                    login();
                    break;

                case 0:
                    System.out.println("Thank you for the trust on ABC bank !!!");
                    return;

                default :
                    System.out.println("Invalid Operation 1 TRa Again !!!");
                    System.out.println(" ");
            }
                
        }

    }

    public static int mainmenu(){
        System.out.println("---------------------------------------------------");
        System.out.println("--------------Wolcome to the ABC Bank--------------");
        System.out.println("---------------------------------------------------");
        System.out.println("Enter 1 : Create new account");
        System.out.println("Enter 2 : Login");
        System.out.println("Enter 0 : Exit");
        System.out.print("Choice : ");
        try {
            int firstChoice = scanner.nextInt();
            return firstChoice;
        }
        catch (InputMismatchException e){
            System.out.println("Invalid Operation !!! Try Again !!!");
            scanner.nextLine();
            return mainmenu();
        }
        

    }


    public static void createAccountMenu(){
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("--------- Welcome to ABC Bank Accout Opening Portal--------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter 1 : Savings Acccount");
        System.out.println("Enter 2 : Current Acccount");
        System.out.println("Enter 0 : Return to the previous menu");
        System.out.println("-----------------------------------------------------------");
        System.out.print("Choice : ");

        try{
            int choice = scanner.nextInt();
            System.out.println("-----------------------------------------------------------");

            switch (choice) {
                case 1:
                    TheUserBuilder builder1 = new TheUserBuilder();  
                    User user1 = new User(builder1);
                    long userNIC = user1.getUsrNIC();
                    new SavingAcc(userNIC);
                    break;
                
                case 2:
                    TheUserBuilder builder2 = new TheUserBuilder(); 
                    User user2 = new User(builder2);
                    userNIC = user2.getUsrNIC();
                    new CurrentAcc(userNIC);
                    break;

                case 0:
                    return;
            
                default:
                    System.out.println("Invalid Operation !");
                    createAccountMenu();
                    break;
            }

        }
        catch (InputMismatchException e){
            System.out.println("Invalid Operation !!!");
            System.out.println(" ");
            createAccountMenu();
        }

    }

    public static void login(){

        System.out.println(" ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("--------- Welcome to ABC Bank Accout Login Portal--------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter 1 : Savings Acccount");
        System.out.println("Enter 2 : Current Acccount");
        System.out.println("Enter 0 : Return to the previous menu");
        System.out.println("-----------------------------------------------------------");
        System.out.print("Choice : ");
        try{
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                String username = Account.getUsername();
                String password = Account.getPassword();
                System.out.println("-----------------------------------------------------------");
                Boolean authenticate = Authenticate.authenticateUSer(username, password);
                if (authenticate){
                    System.out.println("***********************************************************");
                    System.out.println("***********************************************************");
                    System.out.println("Welcome " + username);
                    System.out.println("***********************************************************");
                    System.out.println("***********************************************************");
                    
                    int choice2;
                    long userNIC;
                    SavingAcc savingObj = SavingAcc.getSavingAccObj();
                    choice2 = savingObj.accountOperationMenu();

                    userNIC = AccountDAO.getUserNIC(username);

                    switch (choice2){       
                        case 1:
                            savingObj.deposite(userNIC);
                            break;
                        
                        case 2:
                            savingObj.withdraw(userNIC);
                            break;

                        case 3:
                            savingObj.transfer(userNIC);
                            break;

                        default :
                            System.out.println("Invalid opearation !");
                            login();
                            break;
                        }
                    }
                    else{
                        System.out.println("Invalid username or password !");
                        login();
                    }
                

            case 2 :
                username = Account.getUsername();
                password = Account.getPassword();
                System.out.println("-----------------------------------------------------------");
                authenticate = Authenticate.authenticateUSer(username, password);
                if (authenticate){
                System.out.println("***********************************************************");
                System.out.println("***********************************************************");
                System.out.println("Welcome " + username);
                System.out.println("***********************************************************");
                System.out.println("***********************************************************");
                int choice2;
                long userNIC;
                CurrentAcc currentObj = CurrentAcc.getCurrentObj();
                choice2 = currentObj.accountOperationMenu();


                userNIC = AccountDAO.getUserNIC(username);

                switch (choice2){       
                    case 1:
                        currentObj.deposite(userNIC);
                        break;
                    
                    case 2:
                        currentObj.withdraw(userNIC);
                        break;

                    case 3:
                        currentObj.transfer(userNIC);
                        break;
                    case 0:
                        login();
                        break;
                        
                    default :
                        System.out.println("Invalid opearation !");
                        login();
                        break;
                    }
                }
                else{
                    System.out.println("Invalid username or password !");
                    login();
                }

            case 0:
                System.out.println(" ");
                mainmenu();
                break;
            
            default :
                System.out.println("Invalid operation !");
                login();
                break;
            }   

        }
        catch (InputMismatchException e){
            System.out.println("Invalid operation !");
            login();
        }

    }


    
}
