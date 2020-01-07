/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import Users.IUser;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Medicine implements ISystemObject, IUser {

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

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    @Override
    public ArrayList<Message> getMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMessages(ArrayList<Message> messages) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
