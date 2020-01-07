/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Message;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public interface IUser {
    public String getClassType();
    public String getUserId();
    public String getName();
    public String getAddress();
    public String getUserName();
    public String getPassword();
    public ArrayList<Message> getMessages();
    public void setMessages(ArrayList<Message> messages);
}
