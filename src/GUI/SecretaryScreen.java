/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import SystemObjects.AccountCreationRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.MedicineOrderRequest;
import Users.Doctor;
import Users.Patient;
import Users.Secretary;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import DataHandler.DataHandler;
import java.io.IOException;
import Misc.*;
import SystemObjects.Appointment;
import SystemObjects.AppointmentRequest;
import SystemObjects.Medicine;
import SystemObjects.Message;
import Users.IUser;
import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class SecretaryScreen extends javax.swing.JFrame {

    Secretary secretary;
    Doctor doctor;
    Patient patient;

    ArrayList dataArray;

    ArrayList<AccountCreationRequest> accountCreationRequests;
    ArrayList<AccountDeletionRequest> accountDeletionRequests;
    ArrayList<MedicineOrderRequest> medicineOrderRequests;
    ArrayList<Doctor> doctors;
    ArrayList<Patient> patients;
    ArrayList<Secretary> secretaries;
    ArrayList<Medicine> medicines;
    ArrayList<AppointmentRequest> appointmentRequests;

    public SecretaryScreen() {
        initComponents();
    }

    public SecretaryScreen(Secretary secretary) throws IOException {
        initComponents();
        this.secretary = secretary;

        dataArray = DataHandler.readUserData();
        doctors = (ArrayList<Doctor>) dataArray.get(1);
        patients = (ArrayList<Patient>) dataArray.get(2);
        secretaries = (ArrayList<Secretary>) dataArray.get(3);
        medicines = (ArrayList<Medicine>) dataArray.get(4);

        accountCreationRequests = secretary.getAccountCreationRequests();
        accountDeletionRequests = secretary.getAccountDeletionRequests();
        medicineOrderRequests = secretary.getMedicineOrderRequests();
        refreshLists();
        messageAlert();
    }

    private void messageAlert() {
        int unread = secretary.getMessages().size();
        String messageAlert = "You have " + unread + " unread messages!";
        lblMessageAlert.setText(messageAlert);
    }

    private void approveAccountCreationRequest(AccountCreationRequest accountCreationRequest) throws IOException {
        String objectId = UserIdGenerator.generateUserId("Patient");
        String name = accountCreationRequest.getName();
        String address = accountCreationRequest.getAddress();
        String username = accountCreationRequest.getUsername();
        String password = accountCreationRequest.getPassword();
        String sex = accountCreationRequest.getSex();
        ArrayList empty = new ArrayList();
        int age = accountCreationRequest.getAge();

        ArrayList messages = new ArrayList();

        Patient newPatient = new Patient(objectId, name, address, username, password, sex, age, empty, empty, empty, messages);

        patients.add(newPatient);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeAccountCreationRequest(accountCreationRequest);
            secretaries.set(i, tempSecretary);
        }

        secretary.removeAccountCreationRequest(accountCreationRequest);

        dataArray.set(2, patients);
        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);
    }

    private void denyAccountCreationRequest(AccountCreationRequest accountCreationRequest) throws IOException {
        secretary.removeAccountCreationRequest(accountCreationRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeAccountCreationRequest(accountCreationRequest);
            secretaries.set(i, tempSecretary);
        }

        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);
    }

    private void approveAccountDeletionRequest(AccountDeletionRequest accountDeletionRequest) throws IOException {
        secretary.removeAccountDeletionRequest(accountDeletionRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeAccountDeletionRequest(accountDeletionRequest);
            secretaries.set(i, tempSecretary);
        }

        for (int i = 0; i < patients.size(); i++) {
            Patient tempPatient = patients.get(i);
            if (accountDeletionRequest.getUserId().equals(tempPatient.getUserId())) {
                DeleteRelated.objects(tempPatient.getUserId());
                patients.remove(i);
            }

        }

        dataArray.set(2, patients);
        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);
        MessagerHandler.registerNewObservers();
    }

    private void denyAccountDeletionRequest(AccountDeletionRequest accountDeletionRequest) throws IOException {
        secretary.removeAccountDeletionRequest(accountDeletionRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeAccountDeletionRequest(accountDeletionRequest);
            secretaries.set(i, tempSecretary);
        }

        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);

        Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "accountDeletionRequestDenied", accountDeletionRequest.getUserId(), "Account Deletion Request Denied", "Your account deletion request has been denied.");
        MessagerHandler.registerNewObservers();
        MessagerHandler.messageUsers(newMessage);

    }

    private void approveMedicineOrderRequest(MedicineOrderRequest medicineRequest) throws IOException {
        secretary.removeMedicineOrderRequest(medicineRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeMedicineOrderRequest(medicineRequest);
            secretaries.set(i, tempSecretary);
        }

        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            Medicine requestedMed = medicineRequest.getMedicine();

            if (medicine.getObjectId().equals(requestedMed.getObjectId())) {
                int newAmmount = medicine.getAmountInStock() + medicineRequest.getAmountRequested();
                requestedMed.setAmountInStock(newAmmount);
                System.out.println(requestedMed.getAmountInStock());
                medicines.set(i, requestedMed);
            }

        }

        dataArray.set(3, secretaries);
        dataArray.set(4, medicines);

        DataHandler.writeUserData(dataArray);
    }

    private void denyMedicineOrderRequest(MedicineOrderRequest medicineRequest) throws IOException {
        secretary.removeMedicineOrderRequest(medicineRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeMedicineOrderRequest(medicineRequest);
            secretaries.set(i, tempSecretary);
        }

        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);
    }

    private void approveAppointmentRequest(AppointmentRequest appointmentRequest) throws IOException {
        secretary.removeAppointmentRequest(appointmentRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeAppointmentRequest(appointmentRequest);
            secretaries.set(i, tempSecretary);
        }
        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);

        String objectId = ObjectIdGenerator.generateObjectId("Appointment");
        String doctorId = appointmentRequest.getDoctorId();
        String patientId = appointmentRequest.getPatientId();
        String status = "pending";
        String notes = "";
        String appointmentDate = appointmentRequest.getAppointmentDate();

        Appointment newAppointment = new Appointment(objectId, doctorId, patientId, status, notes, appointmentDate);

        Doctor doctor = (Doctor) GetUserById.getUserById(appointmentRequest.getDoctorId());
        doctor.addAppointment(newAppointment);

        Patient MPatient = (Patient) GetUserById.getUserById(appointmentRequest.getPatientId());
        MPatient.addAppointment(newAppointment);

        ReplaceUser.replaceUser(doctor);
        ReplaceUser.replaceUser(MPatient);

        Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "appointmentRequestApproved", appointmentRequest.getPatientId(), "New Appointment", "You have been given a new appointment on " + appointmentDate);
        MessagerHandler.registerNewObservers();
        MessagerHandler.messageUsers(newMessage);

    }

    private void denyAppointmentRequest(AppointmentRequest appointmentRequest) throws IOException {
        secretary.removeAppointmentRequest(appointmentRequest);

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary tempSecretary = secretaries.get(i);
            tempSecretary.removeAppointmentRequest(appointmentRequest);
            secretaries.set(i, tempSecretary);
        }
        dataArray.set(3, secretaries);

        DataHandler.writeUserData(dataArray);

        //System.out.println(appointmentRequest.getPatientId());
        Message newMessage = new Message(ObjectIdGenerator.generateObjectId("Message"), "appointmentRequestDenied", appointmentRequest.getPatientId(), "Appointment Request Denied", "Your request for an appointment has been denied.");
        MessagerHandler.registerNewObservers();
        MessagerHandler.messageUsers(newMessage);
    }

    private void refreshLists() throws IOException {
        dataArray = DataHandler.readUserData();
        doctors = (ArrayList<Doctor>) dataArray.get(1);
        patients = (ArrayList<Patient>) dataArray.get(2);
        secretaries = (ArrayList<Secretary>) dataArray.get(3);

        appointmentRequests = secretary.getAppointmentRequests();
        accountCreationRequests = secretary.getAccountCreationRequests();
        accountDeletionRequests = secretary.getAccountDeletionRequests();
        medicineOrderRequests = secretary.getMedicineOrderRequests();

        DefaultListModel<String> accCreateListModel = new DefaultListModel();
        DefaultListModel<String> accDeleteListModel = new DefaultListModel();
        DefaultListModel<String> medRequestListModel = new DefaultListModel();
        DefaultListModel<String> appRequestListModel = new DefaultListModel();
        DefaultListModel<String> doctorsListModel = new DefaultListModel();
        DefaultListModel<String> patientsListModel = new DefaultListModel();

        lstAccountCreationRequests.setModel(accCreateListModel);
        lstAccountDeletionRequests.setModel(accDeleteListModel);
        lstMedicineOrderRequests.setModel(medRequestListModel);
        lstAppointmentRequests.setModel(appRequestListModel);
        lstDoctors.setModel(doctorsListModel);
        lstPatients.setModel(patientsListModel);

        updateAccCreationList(accCreateListModel);
        updateAccDeletionList(accDeleteListModel);
        updateMedicineOrderRequestList(medRequestListModel);
        updateAppointmentRequestList(appRequestListModel);
        updateDoctorList(doctorsListModel);
        updatePatientList(patientsListModel);

        lstAccountCreationRequests.setSelectedIndex(0);
        lstAccountDeletionRequests.setSelectedIndex(0);
        lstMedicineOrderRequests.setSelectedIndex(0);
        lstAppointmentRequests.setSelectedIndex(0);
        lstDoctors.setSelectedIndex(0);
        lstPatients.setSelectedIndex(0);
    }

    private void updateAccCreationList(DefaultListModel model) {
        if (!accountCreationRequests.isEmpty()) {
            for (int i = 0; i < accountCreationRequests.size(); i++) {
                AccountCreationRequest request = accountCreationRequests.get(i);

                model.addElement(request.getName());
            }
        }

    }

    private void updateAccDeletionList(DefaultListModel model) {
        if (!accountDeletionRequests.isEmpty()) {
            for (int i = 0; i < accountDeletionRequests.size(); i++) {
                try {
                    AccountDeletionRequest request = accountDeletionRequests.get(i);
                    Patient patient = (Patient) GetUserById.getUserById(request.getUserId());
                    model.addElement(patient.getUserId() + " - " + patient.getName());
                } catch (IOException ex) {
                    Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void updateMedicineOrderRequestList(DefaultListModel model) {
        if (!medicineOrderRequests.isEmpty()) {
            for (int i = 0; i < medicineOrderRequests.size(); i++) {
                MedicineOrderRequest request = medicineOrderRequests.get(i);

                model.addElement(request.getMedicine().getName() + " x " + request.getAmountRequested());
            }
        }

    }

    private void updateAppointmentRequestList(DefaultListModel model) {
        if (!appointmentRequests.isEmpty()) {
            for (int i = 0; i < appointmentRequests.size(); i++) {
                AppointmentRequest request = appointmentRequests.get(i);
                try {
                    Patient patient = (Patient) GetUserById.getUserById(request.getPatientId());
                    Doctor doctor = (Doctor) GetUserById.getUserById(request.getDoctorId());
                    model.addElement(request.getAppointmentDate() + "(" + patient.getName() + " & " + doctor.getName() + ")");
                } catch (Exception e) {

                }
            }
        }

    }

    private void updatePatientList(DefaultListModel model) {
        if (!patients.isEmpty()) {
            for (int i = 0; i < patients.size(); i++) {
                Patient patient = patients.get(i);
                model.addElement(patient.getName());
            }
        }

    }

    private void updateDoctorList(DefaultListModel model) {
        if (!doctors.isEmpty()) {
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                model.addElement(doctor.getName());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstAccountCreationRequests = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAccountDeletionRequests = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstMedicineOrderRequests = new javax.swing.JList<>();
        btnDenyAccountDelete = new javax.swing.JButton();
        btnApproveAccountDelete = new javax.swing.JButton();
        btnApproveAccountCreate = new javax.swing.JButton();
        btnDenyAccountCreate = new javax.swing.JButton();
        btnApproveRequest = new javax.swing.JButton();
        btnDenyRequest = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstDoctors = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstPatients = new javax.swing.JList<>();
        btnCreateNewAppointment = new javax.swing.JButton();
        btnGiveMedicine = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstAppointmentRequests = new javax.swing.JList<>();
        btnApproveAppointmentRequest = new javax.swing.JButton();
        btnDenyAppointmentRequest = new javax.swing.JButton();
        btnViewMessages = new javax.swing.JButton();
        lblMessageAlert = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstAccountCreationRequests.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstAccountCreationRequests);

        lstAccountDeletionRequests.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstAccountDeletionRequests);

        lstMedicineOrderRequests.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lstMedicineOrderRequests);

        btnDenyAccountDelete.setText("Deny Account Deletion Request");
        btnDenyAccountDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenyAccountDeleteActionPerformed(evt);
            }
        });

        btnApproveAccountDelete.setText("Approve Account Deletion Request");
        btnApproveAccountDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveAccountDeleteActionPerformed(evt);
            }
        });

        btnApproveAccountCreate.setText("Approve Account Creation Request");
        btnApproveAccountCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveAccountCreateActionPerformed(evt);
            }
        });

        btnDenyAccountCreate.setText("Deny Account Creation Request");
        btnDenyAccountCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenyAccountCreateActionPerformed(evt);
            }
        });

        btnApproveRequest.setText("Approve Medicine Order Request");
        btnApproveRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveRequestActionPerformed(evt);
            }
        });

        btnDenyRequest.setText("Deny Medicine Order Request");
        btnDenyRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenyRequestActionPerformed(evt);
            }
        });

        lstDoctors.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(lstDoctors);

        lstPatients.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(lstPatients);

        btnCreateNewAppointment.setText("Create New Appointment");
        btnCreateNewAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewAppointmentActionPerformed(evt);
            }
        });

        btnGiveMedicine.setText("Fufil Prescription");
        btnGiveMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiveMedicineActionPerformed(evt);
            }
        });

        lstAppointmentRequests.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(lstAppointmentRequests);

        btnApproveAppointmentRequest.setText("Approve Appointment Request");
        btnApproveAppointmentRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveAppointmentRequestActionPerformed(evt);
            }
        });

        btnDenyAppointmentRequest.setText("Deny Appointment Request");
        btnDenyAppointmentRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenyAppointmentRequestActionPerformed(evt);
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

        jLabel1.setText("Account Creation Requests:");

        jLabel2.setText("Account Deletion Requests:");

        jLabel3.setText("Medicine Order Requests:");

        jLabel4.setText("Appointment Requests:");

        jLabel5.setText("Patients:");

        jLabel6.setText("Doctors:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnApproveAccountCreate)
                                    .addComponent(btnDenyAccountCreate))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnApproveAccountDelete)
                                    .addComponent(btnDenyAccountDelete)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApproveRequest)
                            .addComponent(btnDenyRequest)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnViewMessages)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessageAlert)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogOut))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApproveAppointmentRequest)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDenyAppointmentRequest)
                            .addComponent(jLabel4))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(btnGiveMedicine)
                            .addComponent(btnCreateNewAppointment))
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnApproveAccountDelete)
                            .addComponent(btnApproveAccountCreate)
                            .addComponent(btnApproveRequest)
                            .addComponent(btnApproveAppointmentRequest))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDenyAccountDelete)
                            .addComponent(btnDenyAccountCreate)
                            .addComponent(btnDenyRequest)
                            .addComponent(btnDenyAppointmentRequest)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCreateNewAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGiveMedicine)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewMessages)
                    .addComponent(lblMessageAlert)
                    .addComponent(btnLogOut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnApproveAccountCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveAccountCreateActionPerformed
        try {
            approveAccountCreationRequest(accountCreationRequests.get(lstAccountCreationRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnApproveAccountCreateActionPerformed

    private void btnDenyAccountCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenyAccountCreateActionPerformed
        try {
            denyAccountCreationRequest(accountCreationRequests.get(lstAccountCreationRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDenyAccountCreateActionPerformed

    private void btnApproveAccountDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveAccountDeleteActionPerformed
        try {
            approveAccountDeletionRequest(accountDeletionRequests.get(lstAccountDeletionRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnApproveAccountDeleteActionPerformed

    private void btnDenyAccountDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenyAccountDeleteActionPerformed
        try {
            denyAccountDeletionRequest(accountDeletionRequests.get(lstAccountDeletionRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDenyAccountDeleteActionPerformed

    private void btnApproveRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveRequestActionPerformed
        try {
            approveMedicineOrderRequest(medicineOrderRequests.get(lstMedicineOrderRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnApproveRequestActionPerformed

    private void btnDenyRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenyRequestActionPerformed
        try {
            denyMedicineOrderRequest(medicineOrderRequests.get(lstMedicineOrderRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDenyRequestActionPerformed

    private void btnCreateNewAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewAppointmentActionPerformed
        setVisible(false);
        new CreateNewAppointmentScreen(secretary, doctors.get(lstDoctors.getSelectedIndex()), patients.get(lstPatients.getSelectedIndex())).setVisible(true);
    }//GEN-LAST:event_btnCreateNewAppointmentActionPerformed

    private void btnGiveMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiveMedicineActionPerformed
        setVisible(false);
        try {
            new GiveOutMedicineScreen(secretary, patients.get(lstPatients.getSelectedIndex())).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGiveMedicineActionPerformed

    private void btnApproveAppointmentRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveAppointmentRequestActionPerformed
        try {
            approveAppointmentRequest(appointmentRequests.get(lstAppointmentRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnApproveAppointmentRequestActionPerformed

    private void btnDenyAppointmentRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenyAppointmentRequestActionPerformed
        try {
            denyAppointmentRequest(appointmentRequests.get(lstAppointmentRequests.getSelectedIndex()));
            refreshLists();
        } catch (IOException ex) {
            Logger.getLogger(SecretaryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDenyAppointmentRequestActionPerformed

    private void btnViewMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMessagesActionPerformed
        setVisible(false);
        new ViewMessagesScreen(secretary).setVisible(true);
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
//            java.util.logging.Logger.getLogger(SecretaryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SecretaryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SecretaryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SecretaryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SecretaryScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApproveAccountCreate;
    private javax.swing.JButton btnApproveAccountDelete;
    private javax.swing.JButton btnApproveAppointmentRequest;
    private javax.swing.JButton btnApproveRequest;
    private javax.swing.JButton btnCreateNewAppointment;
    private javax.swing.JButton btnDenyAccountCreate;
    private javax.swing.JButton btnDenyAccountDelete;
    private javax.swing.JButton btnDenyAppointmentRequest;
    private javax.swing.JButton btnDenyRequest;
    private javax.swing.JButton btnGiveMedicine;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnViewMessages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblMessageAlert;
    private javax.swing.JList<String> lstAccountCreationRequests;
    private javax.swing.JList<String> lstAccountDeletionRequests;
    private javax.swing.JList<String> lstAppointmentRequests;
    private javax.swing.JList<String> lstDoctors;
    private javax.swing.JList<String> lstMedicineOrderRequests;
    private javax.swing.JList<String> lstPatients;
    // End of variables declaration//GEN-END:variables
}
