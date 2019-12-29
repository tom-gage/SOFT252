/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import System.*;
import java.util.*;

/**
 *
 * @author Tom
 */
public class Secretary extends User implements typeInterface{

    private final String classType = "Secretary";
//    private String secretaryUserId, secretaryName, secretaryAddress;
    private String userId, name, address;

    private ArrayList<Appointment> appointmentRequests;
    private ArrayList<AccountCreationRequest> accountCreationRequests;
    private ArrayList<AccountCreationRequest> accountDeletionRequests;

    public Secretary(String userId, String name, String address, ArrayList appointmentRequests, ArrayList accountCreationRequests, ArrayList accountDeletionRequests) {
        this.userId = userId;
        this.name = name;
        this.address = address;
//        this.secretaryUserId = userId;
//        this.secretaryName = name;
//        this.secretaryAddress = address;

        this.appointmentRequests = appointmentRequests;
        this.accountCreationRequests = accountCreationRequests;
        this.accountDeletionRequests = accountDeletionRequests;
    }
}
