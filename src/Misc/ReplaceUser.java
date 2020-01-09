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
public class ReplaceUser {

    public static void replaceUser(IUser user) throws IOException {
        ArrayList dataArray = DataHandler.readUserData();//get data

        for (int i = 0; i < 4; i++) {//for each user array
            ArrayList<IUser> users = (ArrayList<IUser>) dataArray.get(i);//get users array

            for (int x = 0; x < users.size(); x++) {//for each user in users
                IUser replacedUser = users.get(x);//get user
                if (user.getUserId().equals(replacedUser.getUserId())) {//if id's match 
                    users.set(x, user);//replace user
                }
            }
            
            dataArray.set(i, users);//overwrite
        }
        
        DataHandler.writeUserData(dataArray);//save to file
    }
}
