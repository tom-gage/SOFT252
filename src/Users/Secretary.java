/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Misc.ReplaceUser;
import SystemObjects.Appointment;
import SystemObjects.AccountCreationRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.AppointmentRequest;
import SystemObjects.MedicineOrderRequest;
import SystemObjects.Message;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class Secretary implements IUser, IObserver {

    private final String classType = "Secretary";
    private String userId, name, address, username, password;

    private ArrayList<AppointmentRequest> appointmentRequests;
    private ArrayList<AccountCreationRequest> accountCreationRequests;
    private ArrayList<AccountDeletionRequest> accountDeletionRequests;
    private ArrayList<MedicineOrderRequest> medicineOrderRequests;

    private ArrayList<Message> messages;

    public Secretary(String userId, String name, String address, String username, String password,
            ArrayList appointmentRequests, ArrayList accountCreationRequests, ArrayList accountDeletionRequests, ArrayList medicineOrderRequests, ArrayList messages) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;

        this.appointmentRequests = appointmentRequests;
        this.accountCreationRequests = accountCreationRequests;
        this.accountDeletionRequests = accountDeletionRequests;
        this.medicineOrderRequests = medicineOrderRequests;

        this.messages = messages;
    }

    public void addAppointmentRequest(AppointmentRequest appointmentRequest) {
        appointmentRequests.add(appointmentRequest);
    }

    public void addAccountDeletionRequest(AccountDeletionRequest accountDeletionRequest) {
        accountDeletionRequests.add(accountDeletionRequest);
    }

    public void addAccountCreationRequest(AccountCreationRequest accountCreationRequest) {
        accountCreationRequests.add(accountCreationRequest);
    }

    public void removeAccountCreationRequest(AccountCreationRequest accountCreationRequest) {
        for (int i = 0; i < accountCreationRequests.size(); i++) {
            AccountCreationRequest deleted = accountCreationRequests.get(i);
            if (accountCreationRequest.getObjectId().equals(deleted.getObjectId())) {
                accountCreationRequests.remove(i);
            }
        }
    }

    public void removeAccountDeletionRequest(AccountDeletionRequest accountDeletionRequest) {
        for (int i = 0; i < accountDeletionRequests.size(); i++) {
            AccountDeletionRequest deleted = accountDeletionRequests.get(i);
            if (accountDeletionRequest.getObjectId().equals(deleted.getObjectId())) {
                accountDeletionRequests.remove(i);
            }
        }
    }

    public void removeAppointmentRequest(AppointmentRequest appointmentRequest) {
        for (int i = 0; i < appointmentRequests.size(); i++) {
            AppointmentRequest deleted = appointmentRequests.get(i);
            if (appointmentRequest.getObjectId().equals(deleted.getObjectId())) {
                appointmentRequests.remove(i);
            }
        }
    }

    public void removeMedicineOrderRequest(MedicineOrderRequest medicineOrderRequest) {
        for (int i = 0; i < medicineOrderRequests.size(); i++) {
            MedicineOrderRequest deleted = medicineOrderRequests.get(i);
            if (medicineOrderRequest.getObjectId().equals(deleted.getObjectId())) {
                medicineOrderRequests.remove(i);
            }
        }
    }

    public void addMedicineOrderRequest(MedicineOrderRequest order) {
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

    public ArrayList<AppointmentRequest> getAppointmentRequests() {
        return appointmentRequests;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void update(Message message) {

        if (message.getMessageUserId().equals(userId)) {
            messages.add(message);
            try {
                ReplaceUser.replaceUser(this);

            } catch (IOException ex) {
                Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
