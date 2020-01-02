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
import java.util.*;
import Users.typeInterface;

public class Appointment implements typeInterface{

    private final String classType = "Appointment";
    private String doctorId, patientId;
    private Date appointmentDate;

    public Appointment(String doctorId, String patientId, Date appointmentDate) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(String doctorId, String patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
}
