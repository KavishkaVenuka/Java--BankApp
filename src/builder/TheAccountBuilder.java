package builder;

import java.util.InputMismatchException;
import java.util.Scanner;

import validation.ValidateUsername;


public class TheAccountBuilder {

    private String username;
    private String password;
    private double balance;
    private static Scanner scanner = new Scanner(System.in);
    

    public TheAccountBuilder(){
        this.username = setUsername();
        this.password = setPassword();

    }

    
    public double builderWithDeposite(double deposite){
        this.balance = deposite;
        return balance;
    }


    public static String setUsername(){
        System.out.print("Enter a username : ");
        String usrname = scanner.nextLine();
        Boolean validation = ValidateUsername.validatUsername(usrname);
        if (validation) {
            System.out.println("This username is already taken !!! Try Again !!!");
            System.out.println(" ");
            return setUsername(); 
        }
        else{
            return usrname;
        }
        
    }


    public static String setPassword(){
        System.out.print("Enter a password : ");
        try{
            String password = scanner.nextLine();
            System.out.print("Confirm password : ");
            String confirmation = scanner.nextLine();
            if (password.equals(confirmation)){
                System.out.println("Password setup successfull !!!");
                return password;
            }else{
                System.out.println("Password setup is unsuccessfull !!!");
                System.out.println(" ");
                return setPassword();
            }
        }
        catch (InputMismatchException e){
            System.out.println("Password must contains at least on letter !!!");
            System.out.println(" ");
            return setPassword();
        }
        
    }

    public String getUsername(){return this.username;}  
    public String getPassword(){return this.password;}

}
