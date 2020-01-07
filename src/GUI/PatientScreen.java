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
public class PatientScreen extends javax.swing.JFrame {

    Patient patient;
    Doctor doctor;

    ArrayList<Appointment> pastAppointments;
    ArrayList<Appointment> futureAppointments;
    ArrayList<Prescription> prescriptions;

    public PatientScreen() {
        initComponents();
    }

    public PatientScreen(Patient patient) {
        initComponents();

        this.patient = patient;

        refreshLists();

        messageAlert();
    }

    private void messageAlert() {
        int unread = patient.getMessages().size();
        String messageAlert = "You have " + unread + " unread messages!";
        lblMessageAlert.setText(messageAlert);
    }

    private void requestAccountTermination() throws IOException {
        String newObjectId = ObjectIdGenerator.generateObjectId("AccountDeletionRequest");
        String userId = patient.getUserId();
        AccountDeletionRequest accountDeletionRequest = new AccountDeletionRequest(newObjectId, userId);

        ArrayList dataArray = DataHandler.readUserData();
        ArrayList<Secretary> secretaries = (ArrayList<Secretary>) dataArray.get(3);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary secretary = secretaries.get(i);
            secretary.addAccountDeletionRequest(accountDeletionRequest);
        }

        dataArray.set(3, secretaries);
        DataHandler.writeUserData(dataArray);
    }

    private void refreshLists() {
        pastAppointments = patient.getAppointmentHistory();
        futureAppointments = patient.getFutureAppointments();
        prescriptions = patient.getPrescriptions();

        DefaultListModel<String> pastAppointmentsListModel = new DefaultListModel();
        DefaultListModel<String> futureAppointmentsListModel = new DefaultListModel();
        DefaultListModel<String> prescriptionsListModel = new DefaultListModel();

        lstPastAppointments.setModel(pastAppointmentsListModel);
        lstFutureAppointments.setModel(futureAppointmentsListModel);
        lstPrescriptions.setModel(prescriptionsListModel);

        updatePastAppointmentsList(pastAppointmentsListModel);
        updateFutureAppointmentsList(futureAppointmentsListModel);
        updatePrescriptionsList(prescriptionsListModel);

        lstPastAppointments.setSelectedIndex(0);
        lstFutureAppointments.setSelectedIndex(0);
        lstPrescriptions.setSelectedIndex(0);
    }

    private void updatePastAppointmentsList(DefaultListModel model) {

        for (int i = 0; i < pastAppointments.size(); i++) {
            Appointment appointment = pastAppointments.get(i);
            model.addElement(patient.getName() + " - " + appointment.getAppointmentDate());
        }
    }

    private void updateFutureAppointmentsList(DefaultListModel model) {

        for (int i = 0; i < futureAppointments.size(); i++) {
            Appointment appointment = futureAppointments.get(i);
            model.addElement(patient.getName() + " - " + appointment.getAppointmentDate());
        }
    }

    private void updatePrescriptionsList(DefaultListModel model) {

        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            model.addElement(prescription.getMedicine().getName()+ " x " + prescription.getDosage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstPastAppointments = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstFutureAppointments = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPrescriptions = new javax.swing.JList<>();
        btnTerminateAccount = new javax.swing.JButton();
        btnRequestAppointment = new javax.swing.JButton();
        btnGiveFeedback = new javax.swing.JButton();
        btnViewPastAppointment = new javax.swing.JButton();
        btnViewFutureAppointment = new javax.swing.JButton();
        btnViewPrescription = new javax.swing.JButton();
        btnViewMessages = new javax.swing.JButton();
        lblMessageAlert = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstPastAppointments.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstPastAppointments);

        lstFutureAppointments.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstFutureAppointments);

        lstPrescriptions.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lstPrescriptions);

        btnTerminateAccount.setText("Request Account Termination");
        btnTerminateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminateAccountActionPerformed(evt);
            }
        });

        btnRequestAppointment.setText("Request Appointment");
        btnRequestAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestAppointmentActionPerformed(evt);
            }
        });

        btnGiveFeedback.setText("Give Feedback");
        btnGiveFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiveFeedbackActionPerformed(evt);
            }
        });

        btnViewPastAppointment.setText("View Past Appointment");
        btnViewPastAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPastAppointmentActionPerformed(evt);
            }
        });

        btnViewFutureAppointment.setText("View Upcoming Appointment");
        btnViewFutureAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewFutureAppointmentActionPerformed(evt);
            }
        });

        btnViewPrescription.setText("View Prescription");
        btnViewPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPrescriptionActionPerformed(evt);
            }
        });

        btnViewMessages.setText("View Messages");
        btnViewMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewMessagesActionPerformed(evt);
            }
        });

        lblMessageAlert.setText("lblMessageAlert");

        btnLogOut.setText("LogOut");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewPastAppointment))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewFutureAppointment))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewPrescription)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTerminateAccount)
                                    .addComponent(btnRequestAppointment)
                                    .addComponent(btnGiveFeedback))))
                        .addGap(0, 258, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnViewMessages)
                        .addGap(18, 18, 18)
                        .addComponent(lblMessageAlert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogOut)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewPastAppointment))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTerminateAccount)
                                .addGap(18, 18, 18)
                                .addComponent(btnRequestAppointment)
                                .addGap(18, 18, 18)
                                .addComponent(btnGiveFeedback)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewFutureAppointment)
                            .addComponent(btnViewPrescription))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewMessages)
                    .addComponent(lblMessageAlert)
                    .addComponent(btnLogOut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewPastAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPastAppointmentActionPerformed
        try {
            setVisible(false);
            Appointment appointment = pastAppointments.get(lstPastAppointments.getSelectedIndex());
            doctor = (Doctor) GetUserById.getUserById(appointment.getDoctorId());
            new ViewAppointmentScreen(patient, appointment, doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnViewPastAppointmentActionPerformed

    private void btnViewFutureAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewFutureAppointmentActionPerformed
        try {
            setVisible(false);
            Appointment appointment = futureAppointments.get(lstFutureAppointments.getSelectedIndex());
            doctor = (Doctor) GetUserById.getUserById(appointment.getDoctorId());
            new ViewAppointmentScreen(patient, appointment, doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnViewFutureAppointmentActionPerformed

    private void btnViewPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPrescriptionActionPerformed

        try {
            setVisible(false);
            new ViewPrescriptionScreen(patient, prescriptions.get(lstPrescriptions.getSelectedIndex())).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PatientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnViewPrescriptionActionPerformed

    private void btnTerminateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminateAccountActionPerformed
        try {
            requestAccountTermination();
        } catch (IOException ex) {
            Logger.getLogger(PatientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTerminateAccountActionPerformed

    private void btnRequestAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestAppointmentActionPerformed
        try {
            setVisible(false);
            new CreateAppointmentRequestScreen(patient, patient).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PatientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRequestAppointmentActionPerformed

    private void btnGiveFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiveFeedbackActionPerformed
        try {
            setVisible(false);
            new CreateDoctorFeedBackScreen(patient, patient).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PatientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGiveFeedbackActionPerformed

    private void btnViewMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMessagesActionPerformed
        setVisible(false);
        new ViewMessagesScreen(patient).setVisible(true);
    }//GEN-LAST:event_btnViewMessagesActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        setVisible(false);
        new LoginScreen().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

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
//            java.util.logging.Logger.getLogger(PatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PatientScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiveFeedback;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnRequestAppointment;
    private javax.swing.JButton btnTerminateAccount;
    private javax.swing.JButton btnViewFutureAppointment;
    private javax.swing.JButton btnViewMessages;
    private javax.swing.JButton btnViewPastAppointment;
    private javax.swing.JButton btnViewPrescription;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMessageAlert;
    private javax.swing.JList<String> lstFutureAppointments;
    private javax.swing.JList<String> lstPastAppointments;
    private javax.swing.JList<String> lstPrescriptions;
    // End of variables declaration//GEN-END:variables
}
