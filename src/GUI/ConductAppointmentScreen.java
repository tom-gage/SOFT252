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

        lblStatus = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        btnCancel = new javax.swing.JButton();
        btnCompleteAppointment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblStatus.setText("status");

        lblDate.setText("date");

        lblDoctor.setText("lblDoctor");

        lblPatient.setText("patient");

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        txtNotes.setText("notes\n");
        jScrollPane1.setViewportView(txtNotes);

        btnCancel.setText("Cancel");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addGap(46, 46, 46)
                                .addComponent(lblDoctor)
                                .addGap(109, 109, 109)
                                .addComponent(lblPatient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(lblStatus))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(btnCancel)
                            .addGap(18, 18, 18)
                            .addComponent(btnCompleteAppointment))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(lblPatient)
                    .addComponent(lblDate))
                .addGap(10, 10, 10)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnCompleteAppointment))
                .addGap(22, 22, 22))
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
            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConductAppointmentScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConductAppointmentScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCompleteAppointment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea txtNotes;
    // End of variables declaration//GEN-END:variables
}
