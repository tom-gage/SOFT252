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
public class userIdGenerator {

    public static String generateUserId(String classType) throws IOException {
        int largest = 0;
        String letterIdentifier;
        String newUserId;
        ArrayList dataArray = DataHandler.readUserData();

        switch (classType) {
            case "Administrator":
                letterIdentifier = "A";
                break;
            case "Doctor":
                letterIdentifier = "D";
                break;
            case "Patient":
                letterIdentifier = "P";
                break;
            case "Secretary":
                letterIdentifier = "S";
                break;
            default:
                letterIdentifier = "?";
                break;
        }

        for (int i = 0; i < 3; i++) {
            ArrayList<IUser> users = (ArrayList<IUser>) dataArray.get(0);

            for (int j = 0; j < users.size(); j++) {
                IUser user = users.get(j);
                int userIdAsInt = getUserIdAsInt(user.getUserId());
                if (userIdAsInt > largest) {
                    largest = userIdAsInt;
                }
            }
        }

        newUserId = letterIdentifier + Integer.toString(largest);
        return newUserId;
    }

    private static int getUserIdAsInt(String id) throws IOException {
        int userIdAsInt;
        StringBuilder userId = new StringBuilder(id);
        userId.deleteCharAt(0);
        userIdAsInt = Integer.parseInt(userId.toString());
        return userIdAsInt;
    }
}
