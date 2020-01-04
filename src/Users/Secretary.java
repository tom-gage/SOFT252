/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Appointment;
import SystemObjects.AccountCreationRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.MedicineOrderRequest;
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
    private ArrayList<AccountDeletionRequest> accountDeletionRequests;
    private ArrayList<MedicineOrderRequest> medicineOrderRequests;

    public Secretary(String userId, String name, String address, String username, String password, ArrayList appointmentRequests, ArrayList accountCreationRequests, ArrayList accountDeletionRequests, ArrayList medicineOrderRequests) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;

        this.appointmentRequests = appointmentRequests;
        this.accountCreationRequests = accountCreationRequests;
        this.accountDeletionRequests = accountDeletionRequests;
        this.medicineOrderRequests = medicineOrderRequests;
    }
    
    public void addMedicineOrderRequest(MedicineOrderRequest order){
        medicineOrderRequests.add(order);
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

    public ArrayList<AccountDeletionRequest> getAccountDeletionRequests() {
        return accountDeletionRequests;
    }

    public ArrayList<MedicineOrderRequest> getMedicineOrderRequests() {
        return medicineOrderRequests;
    }

    public ArrayList<Appointment> getAppointmentRequests() {
        return appointmentRequests;
    }

    public String getUsername() {
        return username;
    }
    
    

}
