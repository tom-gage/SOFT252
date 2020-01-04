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
public class AccountDeletionRequest implements typeInterface {

    private final String classType = "AccountDeletionRequest";
    private String objectId;
    private String username;

    public AccountDeletionRequest(String objectId, String username) {
        this.objectId = objectId;
        this.username = username;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }
}
