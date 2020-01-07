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
public class AccountRequest implements ISystemObject {

    private String objectId;
    private String name, address;

    public AccountRequest(String objectId, String name, String Address) {
        this.objectId = objectId;
        this.name = name;
        this.address = address;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }
}
