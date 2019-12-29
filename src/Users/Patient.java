/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import System.Appointment;
import System.Prescription;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Patient extends User implements typeInterface{

    private final String classType = "Patient";
//    private String patientUserId, patientName, patientAddress;
    private String userId, name, address;
    
    private String sex;
    private int age;
    private ArrayList<Appointment> appointmentHistory;
    private ArrayList<Prescription> prescriptions;

    public Patient(String userId, String name, String address, String sex, int age, ArrayList appointmentHistory, ArrayList prescriptions) {
        this.userId = userId;
        this.name = name;
        this.address = address;
//        this.patientUserId = userId;
//        this.patientName = name;
//        this.patientAddress = address;

        this.sex = sex;
        this.age = age;
        this.appointmentHistory = appointmentHistory;
        this.prescriptions = prescriptions;
    }

    public String getName() {
        return name;
    }



}
