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
import SystemObjects.MedicineOrderRequest;
import SystemObjects.Prescription;
import Users.Administrator;
import Users.Doctor;
import Users.Patient;
import Users.Secretary;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public class ObjectIdGenerator {

    public static String generateObjectId(String classType) throws IOException {
        int largest = 0;
        String letterIdentifier;
        String newObjectId;
        ArrayList dataArray = DataHandler.readUserData();

        switch (classType) {
            case "AccountCreationRequest":
                letterIdentifier = "AC";
                break;
            case "AccountDeletionRequest":
                letterIdentifier = "AD";
                break;
            case "AccountRequest":
                letterIdentifier = "ar";
                break;
            case "Appointment":
                letterIdentifier = "Ap";
                break;
            case "AppointmentRequest":
                letterIdentifier = "AR";
                break;
            case "DoctorFeedback":
                letterIdentifier = "DF";
                break;
            case "Medicine":
                letterIdentifier = "Me";
                break;
            case "Prescription":
                letterIdentifier = "Pr";
                break;
            case "MedicineOrderRequest":
                letterIdentifier = "MR";
                break;
            case "Message":
                letterIdentifier = "MS";
                break;
            default:
                letterIdentifier = "?";
                break;
        }

        ArrayList<Administrator> administrators = (ArrayList<Administrator>) dataArray.get(0);
        ArrayList<Doctor> doctors = (ArrayList<Doctor>) dataArray.get(1);
        ArrayList<Patient> patients = (ArrayList<Patient>) dataArray.get(2);
        ArrayList<Secretary> secretaries = (ArrayList<Secretary>) dataArray.get(3);
        ArrayList<Medicine> medicines = (ArrayList<Medicine>) dataArray.get(4);

        for (int i = 0; i < administrators.size(); i++) {

        }

        for (int i = 0; i < doctors.size(); i++) {//for each doctor, read systemObject arrays and get largest id as int
            Doctor doctor = doctors.get(i);
            ArrayList<DoctorFeedback> doctorFeedbacks = doctor.getFeedback();
            ArrayList<Appointment> futureAppointments = doctor.getFutureAppointments();
            ArrayList<Appointment> pastAppointments = doctor.getPastAppointments();

            for (int j = 0; j < doctorFeedbacks.size(); j++) {
                DoctorFeedback object = doctorFeedbacks.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

            for (int j = 0; j < futureAppointments.size(); j++) {
                Appointment object = futureAppointments.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

            for (int j = 0; j < pastAppointments.size(); j++) {
                Appointment object = pastAppointments.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }
        }

        for (int i = 0; i < patients.size(); i++) {//for each patient, read systemObject arrays and get largest id as int
            Patient patient = patients.get(i);
            ArrayList<Appointment> futureAppointments = patient.getFutureAppointments();
            ArrayList<Appointment> pastAppointments = patient.getAppointmentHistory();
            ArrayList<Prescription> prescriptions = patient.getPrescriptions();

            for (int j = 0; j < futureAppointments.size(); j++) {
                Appointment object = futureAppointments.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

            for (int j = 0; j < pastAppointments.size(); j++) {
                Appointment object = pastAppointments.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

            for (int j = 0; j < prescriptions.size(); j++) {
                Prescription object = prescriptions.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }
        }

        for (int i = 0; i < secretaries.size(); i++) {//for each secretary, read systemObject arrays and get largest id as int
            Secretary secretary = secretaries.get(i);
            ArrayList<AccountCreationRequest> accountCreationRequests = secretary.getAccountCreationRequests();
            ArrayList<AccountDeletionRequest> accountDeletionRequests = secretary.getAccountDeletionRequests();
            ArrayList<MedicineOrderRequest> medicineOrderRequests = secretary.getMedicineOrderRequests();

            for (int j = 0; j < accountCreationRequests.size(); j++) {
                AccountCreationRequest object = accountCreationRequests.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

            for (int j = 0; j < accountDeletionRequests.size(); j++) {
                AccountDeletionRequest object = accountDeletionRequests.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

            for (int j = 0; j < medicineOrderRequests.size(); j++) {
                MedicineOrderRequest object = medicineOrderRequests.get(j);
                int objectIdAsInt = getObjectIdAsInt(object.getObjectId());
                if (objectIdAsInt > largest) {
                    largest = objectIdAsInt;
                }
            }

        }

        for (int i = 0; i < medicines.size(); i++) {////for each medicine, get largest id as int
            Medicine medicine = medicines.get(i);

            int objectIdAsInt = getObjectIdAsInt(medicine.getObjectId());
            if (objectIdAsInt > largest) {
                largest = objectIdAsInt;
            }

        }

        newObjectId = letterIdentifier + Integer.toString(largest + 1);//increment largest id by one and add letter identifier
        return newObjectId;//return new id
    }

    private static int getObjectIdAsInt(String id) throws IOException {//in id
        int objectIdAsInt;
        StringBuilder objectId = new StringBuilder(id);

        objectId.deleteCharAt(0);
        objectId.deleteCharAt(0);//remove letter identifier

        objectIdAsInt = Integer.parseInt(objectId.toString());//return as int
        return objectIdAsInt;
    }
}
