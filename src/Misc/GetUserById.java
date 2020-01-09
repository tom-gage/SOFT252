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
public class GetUserById {

    public static IUser getUserById(String userId) throws IOException {
        ArrayList dataArray = DataHandler.readUserData();//get data from file
        

        for (int i = 0; i < 4; i++) {//for each user array
            ArrayList<IUser> users = (ArrayList<IUser>) dataArray.get(i);//get users array

            for (int x = 0; x < users.size(); x++) {//for each item in users array 
                IUser user = users.get(x);//get user
                
                if (user.getUserId().equals(userId)) {//if user id are equal return user
                    
                    return user;
                }
            }
        }
        return null;
    }
}
