/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.DataHandler;

import SystemObjects.AccountCreationRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.AppointmentRequest;
import SystemObjects.MedicineOrderRequest;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class MockUser {

    String classType;
    String userId;
    String name;
    String address;
    String username;
    String password;
    ArrayList messages;

    //admin
    //doctor/patient arrays
    ArrayList futureAppointments;
    ArrayList pastAppointments;

    //secretary
    ArrayList<AppointmentRequest> appointmentRequests;
    ArrayList<AccountCreationRequest> accountCreationRequests;
    ArrayList<AccountDeletionRequest> accountDeletionRequests;
    ArrayList<MedicineOrderRequest> medicineOrderRequests;

    public static class Builder {

        String classType;
        String userId;
        String name;
        String address;
        String username;
        String password;
        ArrayList messages;

        //admin doesn't have any arrays
        //doctor/patient arrays
        ArrayList futureAppointments;
        ArrayList pastAppointments;

        //secretary
        ArrayList appointmentRequests;
        ArrayList accountCreationRequests;
        ArrayList accountDeletionRequests;
        ArrayList medicineOrderRequests;

        public Builder() {
            this.classType = null;
            this.userId = "testUserId";
            this.name = "testName";
            this.address = "testAddress";
            this.username = "testUsername";
            this.password = "testPassword";
            this.messages = null;

            this.futureAppointments = null;
            this.pastAppointments = null;

            this.appointmentRequests = null;
            this.accountCreationRequests = null;
            this.accountDeletionRequests = null;
            this.medicineOrderRequests = null;
        }

        public Builder buildAdministrator(){
            classType = "Administrator";
            return this;
        }
        
        public Builder buildDoctor() {
            classType = "Doctor";
            
            messages = new ArrayList();
            futureAppointments = new ArrayList();
            pastAppointments = new ArrayList();
            return this;
        }

        public Builder buildPatient() {
            classType = "Patient";
            
            messages = new ArrayList();
            futureAppointments = new ArrayList();
            pastAppointments = new ArrayList();
            return this;
        }

        public Builder buildSecretary() {
            classType = "Secretary";
            
            messages = new ArrayList();
            appointmentRequests = new ArrayList();
            accountCreationRequests = new ArrayList();
            accountDeletionRequests = new ArrayList();
            medicineOrderRequests = new ArrayList();
            return this;
        }

        public MockUser build() {
            return new MockUser(this);
        }

    }

    private MockUser(Builder builder) {
        this.classType = builder.classType;
        this.userId = builder.userId;
        this.name = builder.name;
        this.address = builder.address;
        this.username = builder.username;
        this.password = builder.password;
        this.messages = builder.messages;
        
        this.futureAppointments = builder.futureAppointments;
        this.pastAppointments = builder.pastAppointments;
        
        this.appointmentRequests = builder.appointmentRequests;
        this.accountCreationRequests = builder.appointmentRequests;
        this.accountDeletionRequests = builder.accountDeletionRequests;
        this.medicineOrderRequests = builder.medicineOrderRequests;
    }
}
