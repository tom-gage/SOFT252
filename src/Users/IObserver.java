/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Message;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public interface IObserver {
    public void update(Message data);
}
