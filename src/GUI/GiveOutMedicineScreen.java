/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Users.*;
import SystemObjects.*;
import DataHandler.DataHandler;
import Misc.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tom
 */
public class GiveOutMedicineScreen extends javax.swing.JFrame {

    ArrayList dataArray;
    Secretary secretary;
    Patient patient;
    Prescription prescription;
    ArrayList<Prescription> prescriptions;
    ArrayList<Medicine> medicines;

    public GiveOutMedicineScreen() {
        initComponents();
    }

    public GiveOutMedicineScreen(Secretary secretary, Patient patient) throws IOException {
        initComponents();
        this.dataArray = DataHandler.readUserData();
        this.secretary = secretary;
        this.patient = patient;

        this.medicines = (ArrayList<Medicine>) dataArray.get(4);
        this.prescriptions = this.patient.getPrescriptions();

        lblPatientPrescriptions.setText(patient.getName() + "'s Prescriptions:");
        refreshLists();
    }

    private void fufilPrescription() throws IOException {

        prescription = prescriptions.get(lstPrescriptions.getSelectedIndex());//get selected prescription

        for (int i = 0; i < medicines.size(); i++) {//for each medicine
            Medicine medicine = medicines.get(i);//get current medicine
            Medicine prescribedMedicine = prescription.getMedicine();//get prescribed medicine
            if (medicine.getObjectId().equals(prescribedMedicine.getObjectId())) {//if medicines are the same
                int newAmmount = medicine.getAmountInStock() - prescription.getQuantity();//subtract prescription amount from amount in stock
                medicine.setAmountInStock(newAmmount);//update amount in stock
                medicines.set(i, medicine);//update medicine
            }

        }

        dataArray.set(4, medicines);//overwrite medicine array

        DataHandler.writeUserData(dataArray);//save to file

        prescriptions.remove(lstPrescriptions.getSelectedIndex());//remove prescription from array

        patient.setPrescriptions(prescriptions);//update patient's prescriptions

        ReplaceUser.replaceUser(patient);//save patient to file

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstPrescriptions = new javax.swing.JList<>();
        btnCancel = new javax.swing.JButton();
        btnFufil = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblPatientPrescriptions = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstPrescriptions.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstPrescriptions);

        btnCancel.setText("Go Back");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnFufil.setText("Fufil Prescription");
        btnFufil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFufilActionPerformed(evt);
            }
        });

        jLabel1.setText("Fufil Prescription:");

        lblPatientPrescriptions.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPatientPrescriptions)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 210, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(63, 63, 63)
                .addComponent(btnFufil)
                .addGap(202, 202, 202))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(lblPatientPrescriptions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnFufil))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            setVisible(false);
            new SecretaryScreen(secretary).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(GiveOutMedicineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnFufilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFufilActionPerformed
        try {
            fufilPrescription();
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(GiveOutMedicineScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFufilActionPerformed
    private void refreshLists() {
        prescriptions = patient.getPrescriptions();

        DefaultListModel<String> prescriptionsListModel = new DefaultListModel();

        lstPrescriptions.setModel(prescriptionsListModel);

        updatePrescriptionsList(prescriptionsListModel);

        lstPrescriptions.setSelectedIndex(0);
    }

    private void updatePrescriptionsList(DefaultListModel model) {
        if (!prescriptions.isEmpty()) {
            for (int i = 0; i < prescriptions.size(); i++) {
                Prescription prescription = prescriptions.get(i);
                model.addElement(prescription.getMedicine().getName() + " x " + prescription.getDosage());
            }
        }

    }

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
//            java.util.logging.Logger.getLogger(GiveOutMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GiveOutMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GiveOutMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GiveOutMedicineScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GiveOutMedicineScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFufil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPatientPrescriptions;
    private javax.swing.JList<String> lstPrescriptions;
    // End of variables declaration//GEN-END:variables
}
