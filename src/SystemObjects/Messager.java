/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import java.util.ArrayList;
import Users.IObservable;
import Users.IObserver;

/**
 *
 * @author Tom
 */
public class Messager implements IObservable {

    private static ArrayList<IObserver> observers = new ArrayList<>();
    public ArrayList testArray = new ArrayList<>();
    
    public Messager(){
        
    }

    public static void registerObserver(IObserver observer) {
        
        
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(message);

        }
    }

}
