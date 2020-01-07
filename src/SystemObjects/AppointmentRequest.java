/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import java.util.Date;

/**
 *
 * @author Tom
 */
public class AppointmentRequest implements ISystemObject {

    private final String classType = "AppointmentRequest";
    private String objectId;

    private String doctorId, patientId;
    private String appointmentDate;
    private boolean approved = false;

    public AppointmentRequest(String objectId, String doctorId, String patientId, String appointmentDate, boolean approved) {
        this.objectId = objectId;
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

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getClassType() {
        return classType;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }
    
    
    
}
