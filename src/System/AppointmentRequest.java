/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Users.typeInterface;
import java.util.Date;

/**
 *
 * @author Tom
 */
public class AppointmentRequest implements typeInterface{

    private final String classType = "appointmentRequest";

    private String doctorId, patientId;
    private Date appointmentDate;
    private boolean approved = false;

    public AppointmentRequest(String doctorId, String patientId, Date appointmentDate) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
    }

    public AppointmentRequest(String doctorId, String patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
}
