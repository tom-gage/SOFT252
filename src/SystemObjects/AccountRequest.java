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
public class AccountRequest implements typeInterface{
    private String name, address;
    
    public AccountRequest(String name, String Address){
        this.name = name;
        this.address = address;
    }
}
