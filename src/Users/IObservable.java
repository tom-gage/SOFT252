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
public interface IObservable {

    public static void registerObserver(IObserver observer){};
    public void removeObserver(IObserver observer);
    public void notifyObservers(Message message);
}
