/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft252;

import SystemObjects.Prescription;
import SystemObjects.Medicine;
import SystemObjects.Appointment;
import SystemObjects.DoctorFeedback;
import SystemObjects.AppointmentRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.AccountCreationRequest;
import Users.Administrator;
import Users.Doctor;
import Users.Patient;
import Users.Secretary;

import DataHandler.DataHandler;

import Users.typeInterface;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tom
 */
public class SOFT252 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String id = "0001";
        String name = "Jim";
        String address = "Jims house";
        
        AccountCreationRequest accountCreationRequest = new AccountCreationRequest("testName", "testAddress");
        AccountDeletionRequest accountDeletionRequest = new AccountDeletionRequest("userId:0001");
        Appointment appointment = new Appointment("testDoctorId", "testPatientId");
        AppointmentRequest appointmentRequest = new AppointmentRequest("testDoctorId", "testPatientId");
        DoctorFeedback docFeedback = new DoctorFeedback("testId", "you suck", 0);
        Medicine medicine = new Medicine("paracetemol");
        Prescription prescription = new Prescription("testDocId", "testPatientId", "notes: fuck this", medicine, 100, 100);
        
        //patient stuff
        ArrayList appointmentHistory = new ArrayList();
        ArrayList prescriptions = new ArrayList();
        //fill patient array stuff
        appointmentHistory.add(appointment);
        prescriptions.add(prescription);
        
        //secretary stuff
        ArrayList appointmentRequests = new ArrayList();
        ArrayList accountCreationRequests = new ArrayList();
        ArrayList accountDeletionRequests = new ArrayList();
        
        //filling secretary arrays
        appointmentRequests.add(appointmentRequest);
        accountCreationRequests.add(accountCreationRequest);
        accountDeletionRequests.add(accountDeletionRequest);
        
        //doctor stuff
        ArrayList futureAppointments = new ArrayList();
        ArrayList pastAppointments = new ArrayList();
        //fill doctor arrays
        futureAppointments.add(appointment);
        pastAppointments.add(appointment);
        
        //initialise user mock objects
        Administrator admin = new Administrator(id, name, address);
        Doctor doctor = new Doctor(id, name, address, futureAppointments, pastAppointments);
        Patient patient = new Patient(id, name, address, "Male", 10, appointmentHistory, prescriptions);
        Secretary secretary  = new Secretary(id, name, address, appointmentRequests, accountCreationRequests, accountDeletionRequests);
        
        //data write stuff
        ArrayList<Administrator> administratorArray = new ArrayList();
        ArrayList<Doctor> doctorArray = new ArrayList();
        ArrayList<Patient> patientArray = new ArrayList();
        ArrayList<Secretary> secretaryArray = new ArrayList();
        ArrayList<Medicine> medicineArray = new ArrayList();

        
        administratorArray.add(admin);
        doctorArray.add(doctor);
        patientArray.add(patient);
        secretaryArray.add(secretary);
        medicineArray.add(medicine);
        
        //classes array stuff
        ArrayList classesArray = new ArrayList();
        classesArray.add(administratorArray);
        classesArray.add(doctorArray);
        classesArray.add(patientArray);
        classesArray.add(secretaryArray);
        classesArray.add(medicineArray);
        
      
        System.out.println("yeet");
        DataHandler.writeUserData(classesArray, admin);
        typeInterface[] testYEET = DataHandler.readUserData();
        
        Patient testPP = (Patient) testYEET[2];
        
        System.out.println(testPP.getName());
    }
    
}
