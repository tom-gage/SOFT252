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
import SystemObjects.Message;
import static java.lang.reflect.Array.set;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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

        SpinnerModel model = new SpinnerNumberModel(1, 0, 100,1);
        spnDosage.setModel(model);
        spnQuantity.setModel(model);
    

    }

    private void createPrescription() throws IOException {

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

        Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "newPrescriptionRecieved", patientId, "New Precription", "You have recieved a new prescription for " + medicine.getName() + ".");
        MessagerHandler.registerNewObservers();
        MessagerHandler.messageUsers(newMessage);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstMedicines.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstMedicines);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCreatePrescription.setText("Create Prescription");
        btnCreatePrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePrescriptionActionPerformed(evt);
            }
        });

        jLabel1.setText("Prescribe Medicine:");

        jLabel2.setText("Select medicine:");

        jLabel3.setText("Select Dosage:");

        jLabel4.setText("Select Quantity:");

        jLabel5.setText("Add Notes:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spnQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                    .addComponent(spnDosage)))
                            .addComponent(jLabel5)
                            .addComponent(txtPrescriptionNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(btnCancel)
                        .addGap(52, 52, 52)
                        .addComponent(btnCreatePrescription)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(spnDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtPrescriptionNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnCreatePrescription))
                .addContainerGap(133, Short.MAX_VALUE))
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
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PrescribeMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PrescribeMedicineScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreatePrescription;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstMedicines;
    private javax.swing.JSpinner spnDosage;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTextField txtPrescriptionNotes;
    // End of variables declaration//GEN-END:variables

}
