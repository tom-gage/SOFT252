/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author Tom
 */
public class Administrator extends User implements IUser {

    private final String classType = "Administrator";

    private String userId, name, address, username, password;

    public Administrator(String userId, String name, String address, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
    }


    @Override
    public String getClassType(){
        return classType;
    }
    
    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }
    
    @Override
    public String getUserName() {
        return username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
}
