package builder;

import java.util.InputMismatchException;
import java.util.Scanner;

import validation.ValidateUserNIC;

public class TheUserBuilder {

    protected long userNIC;
    protected String nameWithInitials;
    protected String addresss;
    private static Scanner scanner = new Scanner(System.in);

    public TheUserBuilder(){
        this.userNIC = promptUserNIC();
        scanner.nextLine();
        this.nameWithInitials = promptName();
        this.addresss = promptAddress();
    }

    

    public static long promptUserNIC(){
        while (true){
            System.out.print("Enter user NIC :");
        try{
            long usrNIC = scanner.nextLong();
            Boolean availability = ValidateUserNIC.checkNICAvailability(usrNIC);
            if (availability){
                return usrNIC;
            }
            else {
                System.out.println("You already have an account !");
            }


        }
        catch (InputMismatchException e){
            System.out.println("Invalid NIC type! NIC should be a number!");
            scanner.nextLine();
            System.out.println(" ");
        }
        }
    }

    

    public static String promptName(){
        System.out.print("Enter name with initials : ");
        String name = scanner.nextLine();
        return name;
    }


    public static String promptAddress(){
        System.out.print("Enter the permanent address : ");
        String address = scanner.nextLine();
        return address;
    }


    public long getUsrNIC(){ return this.userNIC;}
    public String getName(){ return this.nameWithInitials;}
    public String getAddress(){ return this.addresss;}

}

