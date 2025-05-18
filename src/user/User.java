package user;

import builder.TheUserBuilder;

public class User {
    private static User usr;

    protected long userNIC;
    protected String nameWithInitials;
    protected String addresss;
    
    public User(TheUserBuilder usrBuilderObj){
        this.userNIC = usrBuilderObj.getUsrNIC();
        this.nameWithInitials = usrBuilderObj.getName();
        this.addresss = usrBuilderObj.getAddress();

        userDAO.saveUsrToDB(this.userNIC, this.nameWithInitials, this.addresss);
    }

    public static User getUserInstance(){return usr;}
    public long getUsrNIC(){return this.userNIC;}
    public String getName(){return this.nameWithInitials;}
    public String getAddress(){return this.addresss;}
}

