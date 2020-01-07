/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import Users.IUser;

/**
 *
 * @author Tom
 */
public class Message implements ISystemObject{
    private final String classType = "Message";
    String objectId;
    String messageType;
    String messageUserId;
    String messageTitle;
    String messageBody;

    public Message(String objectId, String messageType, String messageUserId, String messageTitle, String messageBody){
        this.objectId = objectId;
        this.messageType = messageType;
        this.messageUserId = messageUserId;
        
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
        
    }

    public String getObjectId() {
        return objectId;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    
    public String getMessageUserId() {
        return messageUserId;
    }



    public String getMessageTitle() {
        return messageTitle;
    }

    
}
