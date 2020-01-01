/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Users.IUser;
import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class Medicine implements typeInterface, IUser{

    private final String classType = "Medicine";
    private String name;

    public Medicine(String name) {
        this.name = name;
    }
    
        @Override
    public String getClassType(){
        return classType;
    }
    
    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return null;
    }
    
    @Override
    public String getUserName() {
        return null;
    }
    
    @Override
    public String getPassword() {
        return null;
    }
}
