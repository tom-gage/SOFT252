/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class AccountCreationRequest implements typeInterface{

    private final String classType = "AccountCreationRequest";
    private String objectId;
    private String name, address, username, password, sex;
    private int age;

    public AccountCreationRequest(String objectId, String name, String Address, String username, String password, int age, String sex) {
        this.objectId = objectId;
        this.name = name;
        this.address = address;
        this.username =username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }
    
    
}
