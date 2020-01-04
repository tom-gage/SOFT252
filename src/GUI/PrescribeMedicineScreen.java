/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import SystemObjects.Prescription;
import Users.Doctor;
import Users.Patient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Misc.*;
import SystemObjects.Medicine;
import java.util.ArrayList;
import DataHandler.DataHandler;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tom
 */
public class PrescribeMedicineScreen extends javax.swing.JFrame {

    Doctor doctor;
    Patient patient;

    String prescriptionId;
    String doctorId;
    String patientId;
    String doctorNotes;
    
    int dosage;
    int quantity;
    
    Medicine medicine;
    ArrayList dataArray;
    ArrayList<Medicine> medicineArray;
    
    public PrescribeMedicineScreen() {
        initComponents();
    }

    public PrescribeMedicineScreen(Doctor doctor, Patient patient) throws IOException {
        initComponents();
        this.doctor = doctor;
        this.patient = patient;
        this.dataArray = DataHandler.readUserData();
        this.medicineArray = (ArrayList<Medicine>) dataArray.get(4);
        
        refreshLists();
        
    }

    
    private void createPrescription() throws IOException{
        
        prescriptionId = ObjectIdGenerator.generateObjectId("Prescription");
        
        doctorId = doctor.getUserId();
        patientId = patient.getUserId();
        
        
        medicine = medicineArray.get(lstMedicines.getSelectedIndex());
        
        doctorNotes = txtPrescriptionNotes.getText();
        
        dosage = (int) spnDosage.getValue();
        quantity = (int) spnQuantity.getValue();
        
        Prescription prescription = new Prescription(prescriptionId, doctorId, patientId, doctorNotes, medicine, quantity, dosage);
        
        System.out.println(prescription.getObjectId());
        
        patient.addPrescription(prescription);
        
        ReplaceUser.replaceUser(patient);
        
        
    }
    private void refreshLists() {
        DefaultListModel<String> medicineListModel = new DefaultListModel();

        lstMedicines.setModel(medicineListModel);

        updateMedicineslist(medicineListModel);
        
        lstMedicines.setSelectedIndex(0);
    }

    private void updateMedicineslist(DefaultListModel model) {

        for (int i = 0; i < medicineArray.size(); i++) {
            Medicine medicine = medicineArray.get(i);
            model.addElement(medicine.getName());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstMedicines = new javax.swing.JList<>();
        txtPrescriptionNotes = new javax.swing.JTextField();
        spnQuantity = new javax.swing.JSpinner();
        spnDosage = new javax.swing.JSpinner();
        btnCancel = new javax.swing.JButton();
        btnCreatePrescription = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstMedicines.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstMedicines);

        txtPrescriptionNotes.setText("jTextField1");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCreatePrescription.setText("Create");
        btnCreatePrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePrescriptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPrescriptionNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancel)
                            .addComponent(btnCreatePrescription)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spnDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrescriptionNotes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreatePrescription)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        try {
            new DoctorScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCreatePrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePrescriptionActionPerformed
        setVisible(false);
        try {
            createPrescription();
            new DoctorScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreatePrescriptionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrescribeMedicineScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreatePrescription;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstMedicines;
    private javax.swing.JSpinner spnDosage;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTextField txtPrescriptionNotes;
    // End of variables declaration//GEN-END:variables

}
