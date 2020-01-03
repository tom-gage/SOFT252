/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Appointment;
import SystemObjects.Prescription;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Patient extends User implements IUser {

    private final String classType = "Patient";
    private String userId, name, address, username, password;

    private String sex;
    private int age;
    private ArrayList<Appointment> futureAppointments;
    private ArrayList<Appointment> appointmentHistory;

    private ArrayList<Prescription> prescriptions;

    public Patient(String userId, String name, String address, String username, String password, String sex, int age, ArrayList futureAppointments, ArrayList appointmentHistory, ArrayList prescriptions) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;

        this.sex = sex;
        this.age = age;
        
        this.futureAppointments = futureAppointments;
        this.appointmentHistory = appointmentHistory;
        
        this.prescriptions = prescriptions;
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

    public ArrayList<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public ArrayList<Appointment> getFutureAppointments() {
        return futureAppointments;
    }

    public void setFutureAppointments(ArrayList<Appointment> futureAppointments) {
        this.futureAppointments = futureAppointments;
    }

    public void setAppointmentHistory(ArrayList<Appointment> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }
    
    

}
