/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Appointment;
import SystemObjects.AccountCreationRequest;
import java.util.*;

/**
 *
 * @author Tom
 */
public class Secretary extends User implements IUser {

    private final String classType = "Secretary";
//    private String secretaryUserId, secretaryName, secretaryAddress;
    private String userId, name, address, username, password;

    private ArrayList<Appointment> appointmentRequests;
    private ArrayList<AccountCreationRequest> accountCreationRequests;
    private ArrayList<AccountCreationRequest> accountDeletionRequests;

    public Secretary(String userId, String name, String address, String username, String password, ArrayList appointmentRequests, ArrayList accountCreationRequests, ArrayList accountDeletionRequests) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
//        this.secretaryUserId = userId;
//        this.secretaryName = name;
//        this.secretaryAddress = address;

        this.appointmentRequests = appointmentRequests;
        this.accountCreationRequests = accountCreationRequests;
        this.accountDeletionRequests = accountDeletionRequests;
    }

    @Override
    public String getClassType() {
        return classType;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public ArrayList<AccountCreationRequest> getAccountCreationRequests() {
        return accountCreationRequests;
    }

    public ArrayList<AccountCreationRequest> getAccountDeletionRequests() {
        return accountDeletionRequests;
    }

}
