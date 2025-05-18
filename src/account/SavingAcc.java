package account;


import builder.TheAccountBuilder;

public class SavingAcc extends Account {

    public static SavingAcc savingObj;

    public SavingAcc(long userNIC){
        savingObj = this;
        TheAccountBuilder builderObj = new TheAccountBuilder();
        this.username = builderObj.getUsername();
        this.password = builderObj.getPassword();
        AccountDAO.saveAccountToDB("S", userNIC);
        AccountDAO.saveAuthenticateToDB(username, password, userNIC);

        System.out.println("Savings Account created successfully !");

    }

    public SavingAcc(){}


    public static SavingAcc getSavingAccObj(){
        if (savingObj == null){
          savingObj = new SavingAcc();
        }
        return savingObj;}
    
}

