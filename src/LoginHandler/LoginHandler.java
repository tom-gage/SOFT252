/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginHandler;

/**
 *
 * @author Tom
 */
import DataHandler.DataHandler;
import GUI.*;
import Users.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class LoginHandler {

    public static IUser checkCredentials(String userName, String password) throws IOException {
        ArrayList dataArray = DataHandler.readUserData();

        for (int i = 0; i < 4; i++) {
            ArrayList<IUser> users = (ArrayList<IUser>) dataArray.get(i);

            for (int x = 0; x < users.size(); x++) {
                IUser user = users.get(x);
                
                if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                    
                    return user;
                }
            }
        }
        return null;
    }

    public static void openUserScreen(IUser user) throws IOException {

        String classType = user.getClassType();
        
        switch (classType) {
            case ("Administrator"):
                new AdminScreen().setVisible(true);
                break;
            case ("Doctor"):
                new DoctorScreen((Doctor) user).setVisible(true);
                break;
            case ("Patient"):
                new PatientScreen((Patient) user).setVisible(true);
                break;
            case ("Secretary"):
                new SecretaryScreen((Secretary) user).setVisible(true);
                break;
        }
    }
}
