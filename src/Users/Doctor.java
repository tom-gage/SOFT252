/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import System.Appointment;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class Doctor extends User implements typeInterface{

    private final String classType = "Doctor";
//    private String doctorUserId, doctorName, doctorAddress;
    private String userId, name, address;
    
    private ArrayList<Appointment> futureAppointments;
    private ArrayList<Appointment> pastAppointments;

    public Doctor(String userId, String name, String address, ArrayList futureAppointments, ArrayList pastAppointments) {
        this.userId = userId;
        this.name = name;
        this.address = address;
//        this.doctorUserId = userId;
//        this.doctorName = name;
//        this.doctorAddress = address;

        this.futureAppointments = futureAppointments;
        this.pastAppointments = pastAppointments;
    }
}
