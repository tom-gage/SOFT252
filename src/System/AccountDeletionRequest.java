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
public class AccountDeletionRequest implements typeInterface{

    private final String classType = "AccountDeletionRequest";
    private String userId;

    public AccountDeletionRequest(String userId) {
        this.userId = userId;
    }
}
