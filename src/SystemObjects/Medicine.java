/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import Users.IUser;
import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class Medicine implements typeInterface, IUser {

    private final String classType = "Medicine";
    private String objectId;
    private String name;
    private int amountInStock;

    public Medicine(String objectId, String name, int amountInStock) {
        this.objectId = objectId;
        this.name = name;
        this.amountInStock = amountInStock;
    }

    @Override
    public String getClassType() {
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

    @Override
    public String getObjectId() {
        return objectId;
    }
}
