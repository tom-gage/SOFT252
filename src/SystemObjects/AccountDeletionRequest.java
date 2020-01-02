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
public class AccountDeletionRequest implements typeInterface{

    private final String classType = "AccountDeletionRequest";
    private String username;

    public AccountDeletionRequest(String username) {
        this.username = username;
    }
}
