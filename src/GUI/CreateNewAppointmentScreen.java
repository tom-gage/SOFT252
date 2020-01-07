/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Users.Doctor;
import Users.IUser;
import Users.Patient;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import Misc.*;
import SystemObjects.Appointment;
import SystemObjects.Message;
import Users.Secretary;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Tom
 */
public class CreateNewAppointmentScreen extends javax.swing.JFrame {

    IUser originatingUser;
    Doctor doctor;
    Patient patient;

    String objectId;
    String docId;
    String patientId;
    String status;
    String notes;
    String appointmentDate;

    public CreateNewAppointmentScreen() {
        initComponents();
    }

    public CreateNewAppointmentScreen(IUser originatingUser, Doctor doctor, Patient patient) {
        initComponents();

        this.originatingUser = originatingUser;
        this.doctor = doctor;
        this.patient = patient;
        
        SpinnerModel day = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerModel month = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel year = new SpinnerNumberModel(2020, 2020, 3000, 1);
        
        spnDay.setModel(day);
        spnMonth.setModel(month);
        spnYear.setModel(year);
    }

    private void createNewAppointment() throws IOException, ParseException {
        objectId = ObjectIdGenerator.generateObjectId("Appointment");
        docId = doctor.getUserId();
        patientId = patient.getUserId();
        status = "pending";
        notes = "";

        appointmentDate = spnDay.getValue() + "/" + spnMonth.getValue() + "/" + spnYear.getValue();

        System.out.println(objectId);

        Appointment appointment = new Appointment(objectId, docId, patientId, status, notes, appointmentDate);

        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);

        ReplaceUser.replaceUser(doctor);
        ReplaceUser.replaceUser(patient);

        Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "newAppointmentCreated", patientId, "New Appointment", "You have been given a new appointment on " + appointmentDate);
        MessagerHandler.registerNewObservers();
        MessagerHandler.messageUsers(newMessage);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();
        btnCreateAppointment = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spnDay = new javax.swing.JSpinner();
        spnMonth = new javax.swing.JSpinner();
        spnYear = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCreateAppointment.setText("Submit Request");
        btnCreateAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAppointmentActionPerformed(evt);
            }
        });

        jLabel1.setText("Create Appointment:");

        jLabel2.setText("Select Date:");

        jLabel3.setText("Day:");

        jLabel4.setText("Month:");

        jLabel5.setText("Year:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btnCancel)
                        .addGap(65, 65, 65)
                        .addComponent(btnCreateAppointment))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnYear, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnCreateAppointment))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeScreen() {
        setVisible(false);
        if ("Doctor".equals(originatingUser.getClassType())) {
            try {
                new DoctorScreen(doctor).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("Secretary".equals(originatingUser.getClassType())) {
            try {
                new SecretaryScreen((Secretary) originatingUser).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        closeScreen();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCreateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAppointmentActionPerformed
        try {
            createNewAppointment();
            closeScreen();
        } catch (IOException ex) {
            Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateAppointmentActionPerformed

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
//            java.util.logging.Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateNewAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateNewAppointmentScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateAppointment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSpinner spnDay;
    private javax.swing.JSpinner spnMonth;
    private javax.swing.JSpinner spnYear;
    // End of variables declaration//GEN-END:variables
}
