/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Appointment;
import SystemObjects.Message;
import SystemObjects.Prescription;
import java.util.ArrayList;
import Misc.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class Patient implements IUser, IObserver {

    private final String classType = "Patient";
    private String userId, name, address, username, password;

    private String sex;
    private int age;
    private ArrayList<Appointment> futureAppointments;
    private ArrayList<Appointment> appointmentHistory;

    private ArrayList<Prescription> prescriptions;

    private ArrayList<Message> messages;

    public Patient(String userId, String name, String address, String username, String password, String sex, int age, ArrayList futureAppointments, ArrayList appointmentHistory, ArrayList prescriptions, ArrayList messages) {
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

        this.messages = messages;
    }

    public Patient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addAppointment(Appointment appointment) {
        futureAppointments.add(appointment);
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
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

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
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

    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
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
                System.out.println("patient: " + message.getMessageUserId() + " + " + userId);
            } catch (IOException ex) {
                Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
