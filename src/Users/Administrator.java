/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Message;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Administrator implements IUser {

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

    @Override
    public ArrayList<Message> getMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMessages(ArrayList<Message> messages) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
