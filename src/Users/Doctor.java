/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import SystemObjects.Appointment;
import SystemObjects.DoctorFeedback;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Doctor extends User implements IUser {

    private final String classType = "Doctor";
//    private String doctorUserId, doctorName, doctorAddress;
    private String userId, name, address, username, password;
    
    private ArrayList<DoctorFeedback> feedback;
    private ArrayList<Appointment> futureAppointments;
    private ArrayList<Appointment> pastAppointments;

    public Doctor(String userId, String name, String address, String username, String password, ArrayList feedback, ArrayList futureAppointments, ArrayList pastAppointments) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;

        this.feedback = feedback;
        this.futureAppointments = futureAppointments;
        this.pastAppointments = pastAppointments;
    }
    
    public void addFeedback(DoctorFeedback feedback){
        this.feedback.add(feedback);
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
    
    

}
