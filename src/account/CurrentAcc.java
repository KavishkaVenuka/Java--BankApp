package account;

import builder.TheAccountBuilder;

public class CurrentAcc extends Account {

    public static CurrentAcc currentObj;

    public CurrentAcc(long userNIC){
        currentObj = this;
        TheAccountBuilder builderObj = new TheAccountBuilder();
        this.username = builderObj.getUsername();
        this.password = builderObj.getPassword();

        AccountDAO.saveAccountToDB("C", userNIC);
        AccountDAO.saveAuthenticateToDB(username, password, userNIC);

        System.out.println("Current Account created successfully !");
    }

    public CurrentAcc(){}

    public static CurrentAcc getCurrentObj(){
        if (currentObj == null){
            currentObj =  new CurrentAcc();
        }
        return currentObj;
    }
    
}

    

