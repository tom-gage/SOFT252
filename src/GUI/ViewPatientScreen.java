/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import SystemObjects.Appointment;
import SystemObjects.Prescription;
import Users.Doctor;
import Users.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tom
 */
public class ViewPatientScreen extends javax.swing.JFrame {

    String userId;
    String name;
    String address;
    String userName;
    String sex;
    int age;

    Patient patient;
    Doctor doctor;
    ArrayList<Appointment> pastApointments;
    ArrayList<Appointment> futureAppointments;
    ArrayList<Prescription> prescriptions;

    public ViewPatientScreen() {
        initComponents();
    }

    public ViewPatientScreen(Doctor doctor, Patient patient) {
        initComponents();
        this.doctor = doctor;
        this.patient = patient;
        this.pastApointments = patient.getAppointmentHistory();
        this.futureAppointments = patient.getFutureAppointments();
        this.prescriptions = patient.getPrescriptions();

        refreshLists();
        populateLabels();
    }

    private void populateLabels() {
        lblUserId.setText(patient.getUserId());
        lblName.setText(patient.getName());
        lblAddress.setText(patient.getAddress());
        lblUsername.setText(userName);
        lblAge.setText(Integer.toString(patient.getAge()));
        lblSex.setText(patient.getSex());
    }

    private void refreshLists() {
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

        for (int i = 0; i < pastApointments.size(); i++) {
            Appointment appointment = pastApointments.get(i);
            model.addElement(appointment.getPatientId());
        }
    }

    private void updateFutureAppointmentsList(DefaultListModel model) {

        for (int i = 0; i < futureAppointments.size(); i++) {
            Appointment appointment = futureAppointments.get(i);
            model.addElement(appointment.getPatientId());
        }
    }

    private void updatePrescriptionsList(DefaultListModel model) {
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            model.addElement(prescription.getObjectId());
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
        lblUserId = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblSex = new javax.swing.JLabel();
        lblAge = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

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

        lblUserId.setText("lblUserId");

        lblName.setText("jLabel2");

        lblAddress.setText("jLabel3");

        lblUsername.setText("jLabel4");

        lblSex.setText("jLabel5");

        lblAge.setText("jLabel6");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAge)
                            .addComponent(lblSex)
                            .addComponent(lblUsername)
                            .addComponent(lblAddress)
                            .addComponent(lblName)
                            .addComponent(lblUserId)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(btnCancel)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(lblUserId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAge)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        try {
            new DoctorScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ViewPatientScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPatientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPatientScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList<String> lstFutureAppointments;
    private javax.swing.JList<String> lstPastAppointments;
    private javax.swing.JList<String> lstPrescriptions;
    // End of variables declaration//GEN-END:variables
}
