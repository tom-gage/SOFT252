/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class Prescription implements typeInterface{

    private final String classType = "Prescription";
    private String doctorId, patientId, doctorNotes;

    private Medicine medicine;
    private int quantity, dosage;

    public Prescription(String doctorId, String patientId, String doctorNotes, Medicine medicine, int quantity, int dosage) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.doctorNotes = doctorNotes;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }
}
