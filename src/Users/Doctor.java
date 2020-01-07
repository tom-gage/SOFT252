/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Misc.ReplaceUser;
import SystemObjects.Appointment;
import SystemObjects.DoctorFeedback;
import SystemObjects.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class Doctor implements IUser, IObserver {

    private final String classType = "Doctor";
    private String userId, name, address, username, password;

    private ArrayList<DoctorFeedback> feedback;
    private ArrayList<Appointment> futureAppointments;
    private ArrayList<Appointment> pastAppointments;
    private ArrayList<Message> messages;

    public Doctor(String userId, String name, String address, String username, String password, ArrayList feedback, ArrayList futureAppointments, ArrayList pastAppointments, ArrayList messages) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;

        this.feedback = feedback;
        this.futureAppointments = futureAppointments;
        this.pastAppointments = pastAppointments;
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

    public ArrayList<Message> getMessages() {
        return messages;
    }

    
    
    public void addFeedback(DoctorFeedback feedback) {
        this.feedback.add(feedback);
    }

    public void addAppointment(Appointment appointment) {
        futureAppointments.add(appointment);
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

    public ArrayList<DoctorFeedback> getFeedback() {
        return feedback;
    }

    public ArrayList<Appointment> getFutureAppointments() {
        return futureAppointments;
    }

    public ArrayList<Appointment> getPastAppointments() {
        return pastAppointments;
    }

    public void setFutureAppointments(ArrayList<Appointment> futureAppointments) {
        this.futureAppointments = futureAppointments;
    }

    public void setPastAppointments(ArrayList<Appointment> pastAppointments) {
        this.pastAppointments = pastAppointments;
    }

    @Override
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

}
