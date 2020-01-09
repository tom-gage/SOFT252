/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import DataHandler.DataHandler;
import Users.IUser;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class DeleteUser {

    public static void replaceUser(IUser user) throws IOException {
        ArrayList dataArray = DataHandler.readUserData();

        for (int i = 0; i < 3; i++) {
            ArrayList<IUser> users = (ArrayList<IUser>) dataArray.get(i);

            for (int x = 0; x < users.size(); x++) {
                IUser replacedUser = users.get(x);
                if (user.getUserId().equals(replacedUser.getUserId())) {
                    users.remove(x);
                }
            }
            
            dataArray.set(i, users);
        }
        
        DataHandler.writeUserData(dataArray);
    }
}
