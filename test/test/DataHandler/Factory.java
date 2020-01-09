/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.DataHandler;

import SystemObjects.AccountCreationRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.AccountRequest;
import SystemObjects.Appointment;
import SystemObjects.AppointmentRequest;
import SystemObjects.DoctorFeedback;
import SystemObjects.ISystemObject;
import SystemObjects.Medicine;
import SystemObjects.MedicineOrderRequest;
import SystemObjects.Message;
import SystemObjects.Prescription;
import Users.Administrator;
import Users.Doctor;
import Users.IUser;
import Users.Patient;
import Users.Secretary;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Factory {

    public static IUser createUser(String type) {
        String classType = null;
        String userId = "testUserId";
        String name = "testName";
        String address = "testAddress";
        String username = "testUsername";
        String password = "testPassword";

        ArrayList messages = new ArrayList();

        ArrayList futureAppointments = new ArrayList();
        ArrayList pastAppointments = new ArrayList();
        
        ArrayList prescriptions = new ArrayList();

        ArrayList appointmentRequests = new ArrayList();
        ArrayList accountCreationRequests = new ArrayList();
        ArrayList accountDeletionRequests = new ArrayList();
        ArrayList medicineOrderRequests = new ArrayList();

        switch (type) {
            case "Administrator":
                return new Administrator(userId, name, address, username, password);
            case "Doctor":
                return new Doctor(userId, name, address, username, password, messages, futureAppointments, pastAppointments, messages);
            case "Patient":
                return new Patient(userId, name, address, username, password, "male", 0, futureAppointments, appointmentRequests, prescriptions, messages);
            case "Secretary":
                return new Secretary(userId, name, address, username, password, appointmentRequests, accountCreationRequests, accountDeletionRequests, medicineOrderRequests, messages);
            default:
                return null;
        }
        
        
    }
    
    public static ISystemObject createObject(String type){
        //String classType = null;
        String testStringValue = "testObjectData";
        int testIntValue = 0;
//        ArrayList testArray = new ArrayList();
        Medicine medicine = new Medicine(testStringValue, testStringValue, testIntValue);
        
        switch (type){
            case "AccountCreationRequest":
                return new AccountCreationRequest(testStringValue, testStringValue, testStringValue, testStringValue, testStringValue, testIntValue, testStringValue);
            case "AccountDeletionRequest":
                return new AccountDeletionRequest(testStringValue, testStringValue);
            case "AccountRequest":
                return new AccountRequest(testStringValue, testStringValue, testStringValue);
            case "Appointment":
                return new Appointment(testStringValue, testStringValue, testStringValue, testStringValue, testStringValue, testStringValue);
            case "AppointmentRequest":
                return new AppointmentRequest(testStringValue, testStringValue, testStringValue, testStringValue, true);
            case "DoctorFeedback":
                return new DoctorFeedback(testStringValue, testStringValue, testStringValue, testStringValue, testIntValue);
            case "Medicine":
                return new Medicine(testStringValue, testStringValue, testIntValue);
            case "MedicineOrderRequest":
                return new MedicineOrderRequest(testStringValue, medicine, testIntValue);
            case "Message":
                return new Message(testStringValue, testStringValue, testStringValue, testStringValue, testStringValue);
            case "Prescription":
                return new Prescription(testStringValue, testStringValue, testStringValue, testStringValue, medicine, testIntValue, testIntValue);
            default:
                return null;
                
        }
        
    }
}
