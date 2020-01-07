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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Tom
 */
public class CreateAppointmentRequestScreen extends javax.swing.JFrame {

    IUser originatingUser;
    Patient patient;

    ArrayList dataArray;
    Doctor doctor;
    ArrayList<Doctor> doctors;

    public CreateAppointmentRequestScreen() {
        initComponents();
    }

    public CreateAppointmentRequestScreen(IUser originatingUser, Patient patient) throws IOException {
        initComponents();
        this.originatingUser = originatingUser;
        this.patient = patient;

        this.dataArray = DataHandler.readUserData();

        SpinnerModel day = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerModel month = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel year = new SpinnerNumberModel(2020, 2020, 3000, 1);

        spnDay.setModel(day);
        spnMonth.setModel(month);
        spnYear.setModel(year);

        refreshLists();
    }

    private void createAppointmentRequest() throws IOException {
        String objectId = ObjectIdGenerator.generateObjectId("AppointmentRequest");
        doctor = doctors.get(lstDoctors.getSelectedIndex());
        String doctorId = doctor.getUserId();
        String patientId = patient.getUserId();
        String date = spnDay.getValue() + "/" + spnMonth.getValue() + "/" + spnYear.getValue();
        AppointmentRequest appointmentRequest = new AppointmentRequest(objectId, doctorId, patientId, date, false);

        ArrayList<Secretary> secretaries = (ArrayList<Secretary>) dataArray.get(3);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary secretary = secretaries.get(i);
            secretary.addAppointmentRequest(appointmentRequest);

        }

        dataArray.set(3, secretaries);
        DataHandler.writeUserData(dataArray);

        for (int i = 0; i < secretaries.size(); i++) {
            String recipientId = secretaries.get(i).getUserId();
            String messageBody = "Patient " + patient.getName() + " " + patient.getUserId() + " has requested an appointment with Dr." + doctor.getName() + " on " + date;
            Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "appointmentRequestApproved",
                    recipientId, "New Appointment Request", messageBody);
            MessagerHandler.registerNewObservers();
            MessagerHandler.messageUsers(newMessage);
        }

    }

    private void refreshLists() {
        doctors = (ArrayList<Doctor>) dataArray.get(1);

        DefaultListModel<String> doctorListModel = new DefaultListModel();

        lstDoctors.setModel(doctorListModel);

        updateDoctorList(doctorListModel);

        lstDoctors.setSelectedIndex(0);
    }

    private void updateDoctorList(DefaultListModel model) {

        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            model.addElement(doctor.getName());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClose = new javax.swing.JButton();
        btnCreateAppointmentRequest = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstDoctors = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        spnDay = new javax.swing.JSpinner();
        spnMonth = new javax.swing.JSpinner();
        spnYear = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnClose.setText("Close");

        btnCreateAppointmentRequest.setText("Create Appointment Request");
        btnCreateAppointmentRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAppointmentRequestActionPerformed(evt);
            }
        });

        lstDoctors.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstDoctors);

        jLabel1.setText("Make Appointment Request:");

        jLabel2.setText("Select Doctor:");

        jLabel3.setText("Enter Date:");

        jLabel4.setText("Day:");

        jLabel5.setText("Month:");

        jLabel6.setText("Year:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnYear, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(btnClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(btnCreateAppointmentRequest)
                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreateAppointmentRequest)
                            .addComponent(btnClose))
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeScreen() {
        setVisible(false);
        if ("Patient".equals(originatingUser.getClassType())) {
            new PatientScreen((Patient) originatingUser).setVisible(true);
        }
    }


    private void btnCreateAppointmentRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAppointmentRequestActionPerformed
        try {
            createAppointmentRequest();
            closeScreen();
        } catch (IOException ex) {
            Logger.getLogger(CreateAppointmentRequestScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateAppointmentRequestActionPerformed

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
//            java.util.logging.Logger.getLogger(CreateAppointmentRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateAppointmentRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateAppointmentRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateAppointmentRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateAppointmentRequestScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreateAppointmentRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstDoctors;
    private javax.swing.JSpinner spnDay;
    private javax.swing.JSpinner spnMonth;
    private javax.swing.JSpinner spnYear;
    // End of variables declaration//GEN-END:variables
}
