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
import SystemObjects.AppointmentRequest;
import SystemObjects.DoctorFeedback;
import SystemObjects.Medicine;
import SystemObjects.MedicineOrderRequest;
import SystemObjects.Message;
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
public class DeleteRelated {

    public static void objects(String userId) throws IOException {
        ArrayList dataArray = DataHandler.readUserData();//get data from file
        ArrayList<Doctor> doctors = (ArrayList<Doctor>) dataArray.get(1);
        ArrayList<Patient> patients = (ArrayList<Patient>) dataArray.get(2);
        ArrayList<Secretary> secretaries = (ArrayList<Secretary>) dataArray.get(3);//get user arrays


        for (int i = 0; i < doctors.size(); i++) {//for each item in doctors, remove item if it is associated with userId
            Doctor doctor = doctors.get(i);
            ArrayList<DoctorFeedback> doctorFeedbacks = doctor.getFeedback();
            ArrayList<Appointment> futureAppointments = doctor.getFutureAppointments();
            ArrayList<Appointment> pastAppointments = doctor.getPastAppointments();
            ArrayList<Message> messages = doctor.getMessages();

            for (int j = 0; j < doctorFeedbacks.size(); j++) {
                DoctorFeedback object = doctorFeedbacks.get(j);
                if (object.getDoctorId().equals(userId)) {
                    doctorFeedbacks.remove(j);
                }
            }

            for (int j = 0; j < futureAppointments.size(); j++) {
                Appointment object = futureAppointments.get(j);
                if (object.getDoctorId().equals(userId)) {
                    futureAppointments.remove(j);
                } else if (object.getPatientId().equals(userId)) {
                    futureAppointments.remove(j);
                }
            }

            for (int j = 0; j < pastAppointments.size(); j++) {
                Appointment object = pastAppointments.get(j);
                if (object.getDoctorId().equals(userId)) {
                    pastAppointments.remove(j);
                } else if (object.getPatientId().equals(userId)) {
                    pastAppointments.remove(j);
                }
            }

            for (int j = 0; j < messages.size(); j++) {
                Message object = messages.get(j);
                if (object.getMessageUserId().equals(userId)) {
                    messages.remove(j);
                }
            }

            doctor.setFeedback(doctorFeedbacks);
            doctor.setFutureAppointments(futureAppointments);
            doctor.setPastAppointments(pastAppointments);
            doctor.setMessages(messages);

            doctors.set(i, doctor);
        }

        for (int i = 0; i < patients.size(); i++) {//for each item in patients, remove item if it is associated with userId
            Patient patient = patients.get(i);
            ArrayList<Appointment> futureAppointments = patient.getFutureAppointments();
            ArrayList<Appointment> pastAppointments = patient.getAppointmentHistory();
            ArrayList<Prescription> prescriptions = patient.getPrescriptions();
            ArrayList<Message> messages = patient.getMessages();

            for (int j = 0; j < futureAppointments.size(); j++) {
                Appointment object = futureAppointments.get(j);
                if (object.getDoctorId().equals(userId)) {
                    futureAppointments.remove(j);
                } else if (object.getPatientId().equals(userId)) {
                    futureAppointments.remove(j);
                }
            }

            for (int j = 0; j < pastAppointments.size(); j++) {
                Appointment object = pastAppointments.get(j);
                if (object.getDoctorId().equals(userId)) {
                    pastAppointments.remove(j);
                } else if (object.getPatientId().equals(userId)) {
                    pastAppointments.remove(j);
                }
            }

            for (int j = 0; j < prescriptions.size(); j++) {
                Prescription object = prescriptions.get(j);
                if (object.getDoctorId().equals(userId)) {
                    prescriptions.remove(j);
                } else if (object.getPatientId().equals(userId)) {
                    prescriptions.remove(j);
                }
            }

            for (int j = 0; j < messages.size(); j++) {
                Message object = messages.get(j);
                if (object.getMessageUserId().equals(userId)) {
                    messages.remove(j);
                }
            }

            patient.setFutureAppointments(futureAppointments);
            patient.setAppointmentHistory(pastAppointments);
            patient.setMessages(messages);

            patients.set(i, patient);

        }

        for (int i = 0; i < secretaries.size(); i++) {//for each item in secretaries, remove item if it is associated with userId
            Secretary secretary = secretaries.get(i);
            ArrayList<AppointmentRequest> appointmentRequests = secretary.getAppointmentRequests();
            ArrayList<AccountDeletionRequest> accountDeletionRequests = secretary.getAccountDeletionRequests();
            ArrayList<Message> messages = secretary.getMessages();

            for (int j = 0; j < appointmentRequests.size(); j++) {
                AppointmentRequest object = appointmentRequests.get(j);
                if (object.getDoctorId().equals(userId)) {
                    appointmentRequests.remove(j);
                } else if (object.getPatientId().equals(userId)) {
                    appointmentRequests.remove(j);
                }
            }


            for (int j = 0; j < accountDeletionRequests.size(); j++) {
                AccountDeletionRequest object = accountDeletionRequests.get(j);
                if (object.getUserId().equals(userId)) {
                    accountDeletionRequests.remove(j);
                }
            }


            for (int j = 0; j < messages.size(); j++) {
                Message object = messages.get(j);
                if (object.getMessageUserId().equals(userId)) {
                    messages.remove(j);
                }
            }

            secretary.setAccountDeletionRequests(accountDeletionRequests);
            secretary.setAppointmentRequests(appointmentRequests);
            secretary.setMessages(messages);

            secretaries.set(i, secretary);
        }
        
        
        dataArray.set(1, doctors);//overwrite data
        dataArray.set(2, patients);
        dataArray.set(3, secretaries);
        
        DataHandler.writeUserData(dataArray);//write to file

    }
}
