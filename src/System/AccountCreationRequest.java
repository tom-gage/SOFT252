/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class AccountCreationRequest implements typeInterface{

    private final String classType = "AccountCreationRequest";
    private String name, address;

    public AccountCreationRequest(String name, String Address) {
        this.name = name;
        this.address = address;
    }
}
