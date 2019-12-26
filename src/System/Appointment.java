/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

/**
 *
 * @author Tom
 */

import java.util.*;

public class Appointment {
    private String doctorId, patientId;
    private Date appointmentDate;
    
    public Appointment(String doctorId, String patientId, Date appointmentDate){
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
    }
}
