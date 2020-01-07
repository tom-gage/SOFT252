/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

/**
 *
 * @author Tom
 */
import Users.Doctor;
import Users.Patient;
import java.util.*;

public class Appointment implements ISystemObject {

    private final String classType = "Appointment";
    private String objectId;


    private String doctorId, patientId;
    private String status;
    private String notes;
    private String appointmentDate;

    public Appointment(String objectId, String doctorId, String patientId, String status, String notes, String appointmentDate) {
        this.objectId = objectId;
        this.doctorId = doctorId;
        this.patientId = patientId;

        this.status = status;
        this.notes = notes;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(String doctorId, String patientId, String status) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.status = status;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getNotes() {
        return notes;
    }

    public String getDoctorId() {
        return doctorId;
    }
    
    

    public String getStatus() {
        return status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
