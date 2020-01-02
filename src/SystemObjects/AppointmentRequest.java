/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import java.util.Date;
import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class AppointmentRequest implements typeInterface{

    private final String classType = "AppointmentRequest";

    private String doctorId, patientId;
    private Date appointmentDate;
    private boolean approved = false;

    public AppointmentRequest(String doctorId, String patientId, Date appointmentDate, boolean approved) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.approved = approved;
    }

    public AppointmentRequest(String doctorId, String patientId, boolean approved) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.approved = approved;
    }
}
