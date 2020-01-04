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
public class MedicineOrderRequest implements typeInterface{
      private final String classType = "MedicineOrderRequest";
    private String objectId;
    
    private Medicine medicine;
    private int amountRequested;
    
    public MedicineOrderRequest(String objectId, Medicine medicine, int amountRequested){
        this.objectId = objectId;
        this.medicine = medicine;
        this.amountRequested = amountRequested;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }
    
    
}
