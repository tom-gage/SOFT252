/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import SystemObjects.*;
import DataHandler.DataHandler;
import Misc.*;
import Users.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class CreateDoctorFeedBackScreen extends javax.swing.JFrame {

    IUser originatingUser;
    Patient patient;
    ArrayList dataArray;
    ArrayList<Doctor> doctors;
    Doctor doctor;

    public CreateDoctorFeedBackScreen() {
        initComponents();
    }

    public CreateDoctorFeedBackScreen(IUser originatingUser, Patient patient) throws IOException {
        initComponents();
        this.originatingUser = originatingUser;
        this.patient = patient;

        this.dataArray = DataHandler.readUserData();

        SpinnerModel model = new SpinnerNumberModel(1, 0, 100, 1);
        spnRating.setModel(model);

        refreshLists();
    }

    private void createFeedback() throws IOException {
        String objectId = ObjectIdGenerator.generateObjectId("DoctorFeedback");
        doctor = doctors.get(lstDoctors.getSelectedIndex());
        String docId = doctor.getUserId();
        String title = txtTitle.getText();
        String feedback = txtFeedbackNotes.getText();
        int rating = (int) spnRating.getValue();//get feedback data

        DoctorFeedback newFeedback = new DoctorFeedback(objectId, docId, title, feedback, rating);//create new feedback

        doctor.addFeedback(newFeedback);//add feedback to doctor

        ReplaceUser.replaceUser(doctor);//update new doctor with new feedback, write new doctor to file

        //create new message
        Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "feedbackRecieved", docId, "Feedback recieved", "You have been provided with feedback, please contact your administrator to view it.");
        MessagerHandler.registerNewObservers();
        MessagerHandler.messageUsers(newMessage);//send message

    }

    private void refreshLists() {
        doctors = (ArrayList<Doctor>) dataArray.get(1);

        DefaultListModel<String> doctorListModel = new DefaultListModel();

        lstDoctors.setModel(doctorListModel);

        updateDoctorList(doctorListModel);

        lstDoctors.setSelectedIndex(0);
    }

    private void updateDoctorList(DefaultListModel model) {
        if (!doctors.isEmpty()) {
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                model.addElement(doctor.getName());//add doctors to list component
            }
        }

    }

    private void closeScreen() {
        setVisible(false);
        if ("Patient".equals(originatingUser.getClassType())) {
            new PatientScreen((Patient) originatingUser).setVisible(true);//open patient screen
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstDoctors = new javax.swing.JList<>();
        txtTitle = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFeedbackNotes = new javax.swing.JTextArea();
        spnRating = new javax.swing.JSpinner();
        btnCancel = new javax.swing.JButton();
        btnCreateFeedback = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstDoctors.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstDoctors);

        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });

        txtFeedbackNotes.setColumns(20);
        txtFeedbackNotes.setRows(5);
        jScrollPane2.setViewportView(txtFeedbackNotes);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCreateFeedback.setText("Create Feedback");
        btnCreateFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateFeedbackActionPerformed(evt);
            }
        });

        jLabel1.setText("Create Feedback:");

        jLabel2.setText("Select Doctor:");

        jLabel3.setText("Enter Feedback Title:");

        jLabel4.setText("Enter Feedback Details:");

        jLabel5.setText("Select Rating:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(spnRating, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(129, 129, 129)
                                .addComponent(jLabel3)))
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addGap(167, 167, 167)
                        .addComponent(btnCreateFeedback)
                        .addGap(172, 172, 172))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateFeedback)
                    .addComponent(btnCancel))
                .addGap(108, 108, 108))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        closeScreen();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCreateFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateFeedbackActionPerformed
        try {
            createFeedback();
            closeScreen();
        } catch (IOException ex) {
            Logger.getLogger(CreateDoctorFeedBackScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateFeedbackActionPerformed

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

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
//            java.util.logging.Logger.getLogger(CreateDoctorFeedBackScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateDoctorFeedBackScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateDoctorFeedBackScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateDoctorFeedBackScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateDoctorFeedBackScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateFeedback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstDoctors;
    private javax.swing.JSpinner spnRating;
    private javax.swing.JTextArea txtFeedbackNotes;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
