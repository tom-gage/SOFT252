/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import DataHandler.DataHandler;
import Users.*;
import SystemObjects.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class MessagerHandler {

    public static void messageUsers(Message message) {
        Messager messager = new Messager();
        messager.notifyObservers(message);
    }

    public static void registerNewObservers() throws IOException {
        ArrayList dataArray = DataHandler.readUserData();

        
        for (int i = 1; i < 4; i++) {
            ArrayList<IObserver> users = (ArrayList<IObserver>) dataArray.get(i);
            for (int j = 0; j < users.size(); j++) {
                Messager.registerObserver(users.get(j));
            }
        }
    }
}
