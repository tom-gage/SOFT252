/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataHandler.DataHandler;
import LoginHandler.LoginHandler;
import System.AccountCreationRequest;
import System.AccountDeletionRequest;
import System.*;
import Users.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class LoginScreen extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public LoginScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        btnCreateNewAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtUserName.setText("admin1");
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        txtPassword.setText("password");

        btnCreateNewAccount.setText("Create New Account");
        btnCreateNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnLogin)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(btnCreateNewAccount)
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(33, 33, 33)
                .addComponent(btnCreateNewAccount)
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        try {
            IUser user = LoginHandler.checkCredentials(username, password);
            if (user.equals(null)) {
                //display error message
            } else {
                setVisible(false);
                LoginHandler.openUserScreen(user);
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCreateNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewAccountActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new CreateAccountScreen().setVisible(true);
    }//GEN-LAST:event_btnCreateNewAccountActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //TEST STUFF
        String id = "0001";
        String name = "Jim";
        String address = "Jims house";
        String password = "password";

        AccountCreationRequest accountCreationRequest = new AccountCreationRequest("testName", "testAddress", "username", "password", 99, "male");
        AccountDeletionRequest accountDeletionRequest = new AccountDeletionRequest("userId:0001");
        Appointment appointment = new Appointment("testDoctorId", "testPatientId");
        AppointmentRequest appointmentRequest = new AppointmentRequest("testDoctorId", "testPatientId", false);
        DoctorFeedback docFeedback = new DoctorFeedback("testId", "you suck", 0);
        Medicine medicine = new Medicine("paracetemol");
        Prescription prescription = new Prescription("testDocId", "testPatientId", "notes: fuck this", medicine, 100, 100);

        //patient stuff
        ArrayList appointmentHistory = new ArrayList();
        ArrayList prescriptions = new ArrayList();
        //fill patient array stuff
        appointmentHistory.add(appointment);
        prescriptions.add(prescription);

        //secretary stuff
        ArrayList appointmentRequests = new ArrayList();
        ArrayList accountCreationRequests = new ArrayList();
        ArrayList accountDeletionRequests = new ArrayList();

        //filling secretary arrays
        appointmentRequests.add(appointmentRequest);
        accountCreationRequests.add(accountCreationRequest);
        accountDeletionRequests.add(accountDeletionRequest);

        //doctor stuff
        ArrayList feedback = new ArrayList();
        ArrayList futureAppointments = new ArrayList();
        ArrayList pastAppointments = new ArrayList();
        //fill doctor arrays
        feedback.add(docFeedback);
        futureAppointments.add(appointment);
        pastAppointments.add(appointment);

        //initialise user mock objects
        Administrator admin = new Administrator(id, name, address, "admin1", password);
        Administrator adminB = new Administrator(id, name, address, "admin2", password);
        Doctor doctor = new Doctor(id, name, address, "doctor1", password, feedback, futureAppointments, pastAppointments);
        Doctor doctorB = new Doctor(id, name, address, "doctor2", password, feedback, futureAppointments, pastAppointments);
        Patient patient = new Patient(id, name, address, "patient1", password, "Male", 10, appointmentHistory, prescriptions);
        Patient patientB = new Patient(id, name, address, "patient2", password, "Male", 10, appointmentHistory, prescriptions);
        Secretary secretary = new Secretary(id, name, address, "secretary1", password, appointmentRequests, accountCreationRequests, accountDeletionRequests);
        Secretary secretaryB = new Secretary(id, name, address, "secretary2", password, appointmentRequests, accountCreationRequests, accountDeletionRequests);

        //data write stuff
        ArrayList<Administrator> administratorArray = new ArrayList();
        ArrayList<Doctor> doctorArray = new ArrayList();
        ArrayList<Patient> patientArray = new ArrayList();
        ArrayList<Secretary> secretaryArray = new ArrayList();
        ArrayList<Medicine> medicineArray = new ArrayList();

        administratorArray.add(admin);
        administratorArray.add(adminB);

        doctorArray.add(doctor);
        doctorArray.add(doctorB);

        patientArray.add(patient);
        patientArray.add(patientB);

        secretaryArray.add(secretary);
        secretaryArray.add(secretaryB);

        medicineArray.add(medicine);
        medicineArray.add(medicine);

        //classes array stuff
        ArrayList classesArray = new ArrayList();
        classesArray.add(administratorArray);
        classesArray.add(doctorArray);
        classesArray.add(patientArray);
        classesArray.add(secretaryArray);
        classesArray.add(medicineArray);

        System.out.println("PROGRAM START");

        System.out.println("write data");
        DataHandler.writeUserData(classesArray);

        System.out.println("read data");
        ArrayList test = DataHandler.readUserData();

        System.out.println("write data");
        DataHandler.writeUserData(test);

        System.out.println("read data");
        ArrayList test2 = DataHandler.readUserData();

        ArrayList<Administrator> adminTestArrayList = (ArrayList<Administrator>) test.get(0);
        ArrayList<Doctor> doctorTestArrayList = (ArrayList<Doctor>) test.get(1);
        ArrayList<Patient> patientTestArrayList = (ArrayList<Patient>) test.get(2);
        ArrayList<Secretary> secretaryTestArrayList = (ArrayList<Secretary>) test.get(3);

        Administrator testAdmin = adminTestArrayList.get(0);
        Doctor testDoctor = doctorTestArrayList.get(0);
        Patient testPatient = patientTestArrayList.get(0);
        Secretary testSecretary = secretaryTestArrayList.get(0);

//        System.out.println(testAdmin.getName());
//        System.out.println(testDoctor.getFutureAppointments());
//        System.out.println(testPatient.getPrescriptions());
//        System.out.println(testSecretary.getAccountDeletionRequests());
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateNewAccount;
    private javax.swing.JButton btnLogin;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
