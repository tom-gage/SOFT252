/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Users.Doctor;
import Users.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataHandler.DataHandler;
import SystemObjects.Appointment;
import Misc.ReplaceUser;
import Misc.*;
import static Misc.GetUserById.getUserById;

/**
 *
 * @author Tom
 */
public class ConductAppointmentScreen extends javax.swing.JFrame {

    ArrayList dataArray;
    ArrayList<Doctor> doctorArray;
    ArrayList<Patient> patientArray;

    Doctor doctor;
    Patient patient;
    Appointment appointment;

    int appointmentIndex;

    public ConductAppointmentScreen() {
        initComponents();
    }

    public ConductAppointmentScreen(Doctor doctor, Appointment appointment, int appointmentIndex) throws IOException {

        initComponents();
        this.dataArray = DataHandler.readUserData();
        this.doctorArray = (ArrayList<Doctor>) dataArray.get(1);
        this.patientArray = (ArrayList<Patient>) dataArray.get(2);

        System.out.println(appointment.getDoctorId());
        
        this.doctor = (Doctor) getUserById(appointment.getDoctorId());
        this.patient = (Patient) getUserById(appointment.getPatientId());

        System.out.println(appointment.getPatientId());

        this.appointment = appointment;
        this.appointmentIndex = appointmentIndex;
        
        populateLabels();
    }
    
    private void populateLabels(){
        String title = "Conducting appointment on patient " + patient.getName() + ".";
        lblTitle.setText(title);
    }

    private void completeAppointment() throws IOException {
        
        ArrayList<Appointment> doctorFutureApointments = doctor.getFutureAppointments();
        ArrayList<Appointment> doctorPastApointments = doctor.getPastAppointments();

        ArrayList<Appointment> patientFutureApointments = patient.getFutureAppointments();
        ArrayList<Appointment> patientPastApointments = patient.getAppointmentHistory();

        appointment.setNotes(txtNotes.getText());
        appointment.setStatus("complete");

        //remove patient future appointment
        for (int i = 0; i < patientFutureApointments.size(); i++) {
            Appointment patientAppointment = patientFutureApointments.get(i);
            if (patientAppointment.getObjectId().equals(appointment.getObjectId())) {
                patientFutureApointments.remove(i);
            }
        }
        //add appointment to past appointments list
        patientPastApointments.add(appointment);

        //apply to patient object
        patient.setFutureAppointments(patientFutureApointments);
        patient.setAppointmentHistory(patientPastApointments);
        ReplaceUser.replaceUser(patient);

        //remove doctor future appointment
        doctorFutureApointments.remove(appointmentIndex);
        //add appointment to past appointment
        doctorPastApointments.add(appointment);

        //apply to doctor object
        doctor.setFutureAppointments(doctorFutureApointments);
        doctor.setPastAppointments(doctorPastApointments);
        ReplaceUser.replaceUser(doctor);

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        btnCancel = new javax.swing.JButton();
        btnCompleteAppointment = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane1.setViewportView(txtNotes);

        btnCancel.setText("Cancel Appointment");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCompleteAppointment.setText("Complete Appointment");
        btnCompleteAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteAppointmentActionPerformed(evt);
            }
        });

        lblTitle.setText("Conducting appointment for patient:");

        jLabel1.setText("Notes:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnCompleteAppointment))
                    .addComponent(jLabel1))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnCompleteAppointment))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        try {
            new DoctorScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewDoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCompleteAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteAppointmentActionPerformed
        try {
            completeAppointment();
        } catch (IOException ex) {
            Logger.getLogger(ConductAppointmentScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
        try {
            new DoctorScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ConductAppointmentScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompleteAppointmentActionPerformed

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
//            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ConductAppointmentScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCompleteAppointment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txtNotes;
    // End of variables declaration//GEN-END:variables
}
