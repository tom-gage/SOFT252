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
        ArrayList dataArray = DataHandler.readUserData();

        for (int i = 0; i < 3; i++) {
            ArrayList<IUser> users = (ArrayList<IUser>) dataArray.get(i);

            for (int x = 0; x < users.size(); x++) {
                IUser user = users.get(x);
                if (user.getUserId().equals(userId)) {
                    System.out.println(user.getUserId());
                    return user;
                }
            }
        }
        return null;
    }
}
