/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import SystemObjects.Appointment;
import Users.Patient;
import java.util.ArrayList;
import DataHandler.DataHandler;
import Misc.GetUserById;
import Users.Doctor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tom
 */
public class DoctorScreen extends javax.swing.JFrame {

    ArrayList dataArray;

    ArrayList<Appointment> pastAppointments;
    ArrayList<Appointment> futureAppointments;
    ArrayList<Patient> patients;
    Doctor doctor;

    public DoctorScreen() {
        initComponents();

    }

    public DoctorScreen(Doctor doctor) throws IOException {
        initComponents();

        dataArray = DataHandler.readUserData();
        pastAppointments = doctor.getPastAppointments();
        futureAppointments = doctor.getFutureAppointments();
        patients = (ArrayList<Patient>) dataArray.get(2);
        this.doctor = doctor;

        refreshLists();
        messageAlert();
    }

    private void messageAlert() {
        int unread = doctor.getMessages().size();
        String messageAlert = "You have " + unread + " unread messages!";//update amount of unread messages
        lblMessageAlert.setText(messageAlert);
    }

    private void refreshLists() throws IOException {
        DefaultListModel<String> pastAppointmentsModel = new DefaultListModel();
        DefaultListModel<String> futureAppointmentsModel = new DefaultListModel();
        DefaultListModel<String> patientsModel = new DefaultListModel();

        lstPastAppointments.setModel(pastAppointmentsModel);
        lstFutureAppointments.setModel(futureAppointmentsModel);
        lstPatients.setModel(patientsModel);

        populatePastAppointmentsList(pastAppointmentsModel);
        populateFutureAppointmentsList(futureAppointmentsModel);
        populatePatientsList(patientsModel);

        lstPastAppointments.setSelectedIndex(0);
        lstFutureAppointments.setSelectedIndex(0);
        lstPatients.setSelectedIndex(0);

    }

    private void populatePastAppointmentsList(DefaultListModel model) {
        if (!pastAppointments.isEmpty()) {
            for (int i = 0; i < pastAppointments.size(); i++) {
                try {
                    Appointment appointment = pastAppointments.get(i);
                    Patient patient = (Patient) GetUserById.getUserById(appointment.getPatientId());
                    model.addElement(appointment.getAppointmentDate() + " - " + patient.getName());
                } catch (IOException ex) {
                    Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void populateFutureAppointmentsList(DefaultListModel model) throws IOException {
        if (!futureAppointments.isEmpty()) {
            for (int i = 0; i < futureAppointments.size(); i++) {
                Appointment appointment = futureAppointments.get(i);
                Patient patient = (Patient) GetUserById.getUserById(appointment.getPatientId());
                model.addElement(appointment.getAppointmentDate() + " - " + patient.getName());
            }
        }

    }

    private void populatePatientsList(DefaultListModel model) {
        if (!patients.isEmpty()) {
            for (int i = 0; i < patients.size(); i++) {
                Patient patient = patients.get(i);
                model.addElement(patient.getName());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstFutureAppointments = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPastAppointments = new javax.swing.JList<>();
        btnViewPastAppointment = new javax.swing.JButton();
        btnViewFutureAppointment = new javax.swing.JButton();
        btnConductAppointment = new javax.swing.JButton();
        btnCreateAppointment = new javax.swing.JButton();
        btnCreateMedicine = new javax.swing.JButton();
        btnRequestMedicineOrder = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstPatients = new javax.swing.JList<>();
        btnViewPatient = new javax.swing.JButton();
        btnPrescribeMedicine = new javax.swing.JButton();
        btnViewMessages = new javax.swing.JButton();
        lblMessageAlert = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstFutureAppointments.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstFutureAppointments);

        lstPastAppointments.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lstPastAppointments);

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

        btnConductAppointment.setText("Conduct Appointment");
        btnConductAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConductAppointmentActionPerformed(evt);
            }
        });

        btnCreateAppointment.setText("Create New Appointment");
        btnCreateAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAppointmentActionPerformed(evt);
            }
        });

        btnCreateMedicine.setText("Create New Medicine");
        btnCreateMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateMedicineActionPerformed(evt);
            }
        });

        btnRequestMedicineOrder.setText("Request Medicine Order");
        btnRequestMedicineOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestMedicineOrderActionPerformed(evt);
            }
        });

        lstPatients.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(lstPatients);

        btnViewPatient.setText("View Patient");
        btnViewPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPatientActionPerformed(evt);
            }
        });

        btnPrescribeMedicine.setText("Prescribe Medicine");
        btnPrescribeMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescribeMedicineActionPerformed(evt);
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

        jLabel1.setText("Past Appointments:");

        jLabel2.setText("Upcoming Appointments:");

        jLabel3.setText("Patients:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnViewMessages)
                        .addGap(18, 18, 18)
                        .addComponent(lblMessageAlert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogOut))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewPastAppointment)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewFutureAppointment)
                            .addComponent(btnConductAppointment)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(btnCreateAppointment)
                            .addComponent(btnViewPatient)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrescribeMedicine))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreateMedicine)
                            .addComponent(btnRequestMedicineOrder))
                        .addGap(0, 103, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreateMedicine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRequestMedicineOrder))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewPastAppointment)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnViewFutureAppointment)
                                .addComponent(btnViewPatient)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConductAppointment)
                            .addComponent(btnPrescribeMedicine))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateAppointment)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnViewMessages)
                        .addComponent(lblMessageAlert))
                    .addComponent(btnLogOut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewPastAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPastAppointmentActionPerformed
        setVisible(false);
        Appointment appointment = pastAppointments.get(lstPastAppointments.getSelectedIndex());
        try {
            new ViewAppointmentScreen(doctor, appointment, doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnViewPastAppointmentActionPerformed

    private void btnViewFutureAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewFutureAppointmentActionPerformed
        setVisible(false);
        Appointment appointment = futureAppointments.get(lstFutureAppointments.getSelectedIndex());
        try {
            new ViewAppointmentScreen(doctor, appointment, doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnViewFutureAppointmentActionPerformed

    private void btnConductAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConductAppointmentActionPerformed
        setVisible(false);
        Appointment appointment = futureAppointments.get(lstFutureAppointments.getSelectedIndex());
        try {
            new ConductAppointmentScreen(doctor, appointment, lstFutureAppointments.getSelectedIndex()).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConductAppointmentActionPerformed

    private void btnPrescribeMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescribeMedicineActionPerformed
        setVisible(false);
        try {
            new PrescribeMedicineScreen(doctor, patients.get(lstPatients.getSelectedIndex())).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrescribeMedicineActionPerformed

    private void btnViewPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPatientActionPerformed
        setVisible(false);
        new ViewPatientScreen(doctor, patients.get(lstPatients.getSelectedIndex())).setVisible(true);
    }//GEN-LAST:event_btnViewPatientActionPerformed

    private void btnCreateMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateMedicineActionPerformed
        setVisible(false);
        try {
            new CreateNewMedicineScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateMedicineActionPerformed

    private void btnRequestMedicineOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestMedicineOrderActionPerformed
        setVisible(false);
        try {
            new CreateNewMedicineRequestScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(DoctorScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRequestMedicineOrderActionPerformed

    private void btnCreateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAppointmentActionPerformed
        setVisible(false);
        new CreateNewAppointmentScreen(doctor, doctor, patients.get(lstPatients.getSelectedIndex())).setVisible(true);
    }//GEN-LAST:event_btnCreateAppointmentActionPerformed

    private void btnViewMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMessagesActionPerformed
        setVisible(false);
        new ViewMessagesScreen(doctor).setVisible(true);
    }//GEN-LAST:event_btnViewMessagesActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        setVisible(false);
        try {
            new LoginScreen().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

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
//            java.util.logging.Logger.getLogger(DoctorScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DoctorScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DoctorScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DoctorScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DoctorScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConductAppointment;
    private javax.swing.JButton btnCreateAppointment;
    private javax.swing.JButton btnCreateMedicine;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnPrescribeMedicine;
    private javax.swing.JButton btnRequestMedicineOrder;
    private javax.swing.JButton btnViewFutureAppointment;
    private javax.swing.JButton btnViewMessages;
    private javax.swing.JButton btnViewPastAppointment;
    private javax.swing.JButton btnViewPatient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMessageAlert;
    private javax.swing.JList<String> lstFutureAppointments;
    private javax.swing.JList<String> lstPastAppointments;
    private javax.swing.JList<String> lstPatients;
    // End of variables declaration//GEN-END:variables
}
