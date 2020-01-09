/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Message;

/**
 *
 * @author Tom
 */
public interface Observable {

    public static void registerObserver(Observer observer){};
    public void removeObserver(Observer observer);
    public void notifyObservers(Message message);
}
