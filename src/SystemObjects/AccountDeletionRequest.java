/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

/**
 *
 * @author Tom
 */
public class AccountDeletionRequest implements ISystemObject {

    private final String classType = "AccountDeletionRequest";
    private String objectId;
    private String userId;

    public AccountDeletionRequest(String objectId, String userId) {
        this.objectId = objectId;
        this.userId = userId;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getUserId() {
        return userId;
    }
}
