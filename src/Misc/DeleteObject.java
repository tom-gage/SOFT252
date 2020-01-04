/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import DataHandler.DataHandler;
import SystemObjects.AccountCreationRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.Appointment;
import SystemObjects.DoctorFeedback;
import SystemObjects.Medicine;
import SystemObjects.Prescription;
import Users.Administrator;
import Users.Doctor;
import Users.Patient;
import Users.Secretary;
import Users.typeInterface;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class DeleteObject {

    public static void deleteObject(typeInterface inObject) throws IOException {

        ArrayList dataArray = DataHandler.readUserData();

        ArrayList<Administrator> administrators = (ArrayList<Administrator>) dataArray.get(0);
        ArrayList<Doctor> doctors = (ArrayList<Doctor>) dataArray.get(1);
        ArrayList<Patient> patients = (ArrayList<Patient>) dataArray.get(2);
        ArrayList<Secretary> secretaries = (ArrayList<Secretary>) dataArray.get(3);
        ArrayList<Medicine> medicines = (ArrayList<Medicine>) dataArray.get(4);

        for (int i = 0; i < administrators.size(); i++) {

        }

        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            ArrayList<DoctorFeedback> doctorFeedbacks = doctor.getFeedback();
            ArrayList<Appointment> futureAppointments = doctor.getFutureAppointments();
            ArrayList<Appointment> pastAppointments = doctor.getPastAppointments();

            for (int j = 0; j < doctorFeedbacks.size(); j++) {
                DoctorFeedback object = doctorFeedbacks.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    doctorFeedbacks.remove(j);
                }
            }

            for (int j = 0; j < futureAppointments.size(); j++) {
                Appointment object = futureAppointments.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    futureAppointments.remove(j);
                }
            }

            for (int j = 0; j < pastAppointments.size(); j++) {
                Appointment object = pastAppointments.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    pastAppointments.remove(j);
                }
            }
            doctors.set(i, doctor);
        }

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            ArrayList<Appointment> futureAppointments = patient.getFutureAppointments();
            ArrayList<Appointment> pastAppointments = patient.getAppointmentHistory();
            ArrayList<Prescription> prescriptions = patient.getPrescriptions();

            for (int j = 0; j < futureAppointments.size(); j++) {
                Appointment object = futureAppointments.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    pastAppointments.remove(j);
                }
            }

            for (int j = 0; j < pastAppointments.size(); j++) {
                Appointment object = pastAppointments.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    pastAppointments.remove(j);
                }
            }

            for (int j = 0; j < prescriptions.size(); j++) {
                Prescription object = prescriptions.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    prescriptions.remove(j);
                }
            }
            patients.set(i, patient);
        }

        for (int i = 0; i < secretaries.size(); i++) {
            Secretary secretary = secretaries.get(i);
            ArrayList<AccountCreationRequest> accountCreationRequests = secretary.getAccountCreationRequests();
            ArrayList<AccountDeletionRequest> accountDeletionRequests = secretary.getAccountDeletionRequests();

            for (int j = 0; j < accountCreationRequests.size(); j++) {
                AccountCreationRequest object = accountCreationRequests.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    accountCreationRequests.remove(j);
                }
            }

            for (int j = 0; j < accountDeletionRequests.size(); j++) {
                AccountDeletionRequest object = accountDeletionRequests.get(j);

                if (inObject.getObjectId().equals(object.getObjectId())) {
                    accountDeletionRequests.remove(j);
                }
            }
            secretaries.set(i, secretary);
        }

        for (int j = 0; j < medicines.size(); j++) {
            Medicine medicine = medicines.get(j);

            if (inObject.getObjectId().equals(medicine.getObjectId())) {
                medicines.remove(j);
            }
            medicines.set(j, medicine);
        }
        
        dataArray.set(0, administrators);
        dataArray.set(1, doctors);
        dataArray.set(2, patients);
        dataArray.set(3, secretaries);
        dataArray.set(4, medicines);
        
        DataHandler.writeUserData(dataArray);

    }
}
