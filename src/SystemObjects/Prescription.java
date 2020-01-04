/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import Users.typeInterface;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;

/**
 *
 * @author Tom
 */
public class Prescription implements typeInterface {

    private final String classType = "Prescription";
    private String objectId;

    private String doctorId, patientId, doctorNotes;

    private Medicine medicine;
    private int quantity, dosage;

    public Prescription(String objectId, String doctorId, String patientId, String doctorNotes, Medicine medicine, int quantity, int dosage) {
        this.objectId = objectId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.doctorNotes = doctorNotes;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getClassType() {
        return classType;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public int getDosage() {
        return dosage;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public String getPatientId() {
        return patientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
