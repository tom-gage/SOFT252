/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Users.Doctor;
import java.util.ArrayList;
import DataHandler.DataHandler;
import SystemObjects.Medicine;
import SystemObjects.MedicineOrderRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import Misc.*;
import SystemObjects.Message;
import Users.Secretary;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Tom
 */
public class CreateNewMedicineRequestScreen extends javax.swing.JFrame {

    Doctor doctor;
    String objectId;
    Medicine medicine;
    int amountRequested;
    ArrayList dataArray;
    ArrayList<Medicine> medicines;
    ArrayList<Secretary> secretaries;

    public CreateNewMedicineRequestScreen(Doctor doctor) throws IOException {
        initComponents();

        this.doctor = doctor;
        dataArray = DataHandler.readUserData();
        medicines = (ArrayList<Medicine>) dataArray.get(4);
        secretaries = (ArrayList<Secretary>) dataArray.get(3);

        SpinnerModel model = new SpinnerNumberModel(1, 0, 100, 1);
        spnQuantity.setModel(model);

        refreshLists();
    }

    private void createNewMedicineRequest() throws IOException {
        medicine = medicines.get(lstMedicines.getSelectedIndex());
        amountRequested = (int) spnQuantity.getValue();
        objectId = ObjectIdGenerator.generateObjectId("MedicineOrderRequest");//get data
        MedicineOrderRequest medicineOrderRequest = new MedicineOrderRequest(objectId, medicine, amountRequested);//create new medicine order request

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary secretary = secretaries.get(i);
            secretary.addMedicineOrderRequest(medicineOrderRequest);//add new medicineOrderRequest to all secretaries
            secretaries.set(i, secretary);
        }

        dataArray.set(3, secretaries);//overwrite secretaries
        DataHandler.writeUserData(dataArray);//write to file

        for (int i = 0; i < secretaries.size(); i++) {
            String recipientId = secretaries.get(i).getUserId();
            Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "newMedicineRequestRecieved",
                    recipientId, "New Medicine Order Request", "An order of: " + medicine.getName() + " x " + amountRequested + " has been requested.");//compose new message
            MessagerHandler.registerNewObservers();
            MessagerHandler.messageUsers(newMessage);//publish message
        }

    }

    private void refreshLists() {
        DefaultListModel<String> medicinesListModel = new DefaultListModel();

        lstMedicines.setModel(medicinesListModel);

        updateMedicineList(medicinesListModel);

        lstMedicines.setSelectedIndex(0);
    }

    private void updateMedicineList(DefaultListModel model) {
        if (!medicines.isEmpty()) {
            for (int i = 0; i < medicines.size(); i++) {
                Medicine medicine = medicines.get(i);
                model.addElement(medicine.getName());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstMedicines = new javax.swing.JList<>();
        btnCancel = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        spnQuantity = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstMedicines.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstMedicines);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCreate.setText("Submit Request");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel1.setText("Create New Medicine Request:");

        jLabel2.setText("Select Medicine:");

        jLabel3.setText("Select Quantity:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(70, 70, 70)
                .addComponent(btnCreate)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnCancel))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        try {
            new DoctorScreen(doctor).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(CreateNewMedicineRequestScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        setVisible(false);
        try {
            new DoctorScreen(doctor).setVisible(true);
            createNewMedicineRequest();

        } catch (IOException ex) {
            Logger.getLogger(CreateNewMedicineRequestScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

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
//            java.util.logging.Logger.getLogger(CreateNewMedicineRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateNewMedicineRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateNewMedicineRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateNewMedicineRequestScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateNewMedicineRequestScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstMedicines;
    private javax.swing.JSpinner spnQuantity;
    // End of variables declaration//GEN-END:variables
}
