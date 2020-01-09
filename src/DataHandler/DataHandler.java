/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHandler;

/**
 *
 * @author Tom
 */
import SystemObjects.Prescription;
import SystemObjects.Medicine;
import SystemObjects.Appointment;
import SystemObjects.AccountRequest;
import SystemObjects.DoctorFeedback;
import SystemObjects.AppointmentRequest;
import SystemObjects.AccountDeletionRequest;
import SystemObjects.AccountCreationRequest;
import SystemObjects.MedicineOrderRequest;
import SystemObjects.Message;
import Users.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.ArrayList;
import SystemObjects.ISystemObject;
import java.net.URL;

public class DataHandler {

    
    static String filePath = "./data.json";

    public static ArrayList readUserData() throws FileNotFoundException, IOException {
        
        File data = new File(filePath);

        InputStream in = new FileInputStream(data);

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        ArrayList classesArray = readClassesArray(reader);
        
        
        return classesArray;

    }

    public static ArrayList readClassesArray(JsonReader reader) throws IOException {
        ArrayList classesArray = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            classesArray.add(readUserClassArray(reader));
        }
        reader.endArray();
        return classesArray;
    }

    public static ArrayList<IUser> readUserClassArray(JsonReader reader) throws IOException {
        ArrayList classArray = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            classArray.add(readUserClass(reader));
        }
        reader.endArray();
        return classArray;
    }

    public static IUser readUserClass(JsonReader reader) throws IOException {
        String classType = "";
        IUser userClass = null;
        reader.beginObject();
        while (reader.hasNext()) {

            reader.nextName();
            classType = reader.nextString();

            if (classType.equals("Administrator")) {
                userClass = readAdministrator(reader);
            } else if (classType.equals("Doctor")) {
                userClass = readDoctor(reader);
            } else if (classType.equals("Patient")) {
                userClass = readPatient(reader);
            } else if (classType.equals("Secretary")) {
                userClass = readSecretary(reader);
            } else if (classType.equals("Medicine")) {
                userClass = readMedicine(reader);
            }

        }
        reader.endObject();

        return userClass;
    }

    public static ArrayList<ISystemObject> readSystemObjectClassArray(JsonReader reader) throws IOException {
        ArrayList classArray = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            classArray.add(readSystemObjectClass(reader));
        }
        reader.endArray();
        return classArray;
    }

    public static ISystemObject readSystemObjectClass(JsonReader reader) throws IOException {
        String classType = "";
        ISystemObject systemClass = null;
        reader.beginObject();
        while (reader.hasNext()) {

            reader.nextName();
            classType = reader.nextString();

            if (classType.equals("AccountCreationRequest")) {
                systemClass = readAccountCreationRequest(reader);
            } else if (classType.equals("AccountDeletionRequest")) {
                systemClass = readAccountDeletionRequest(reader);
            } else if (classType.equals("AccountRequest")) {
                systemClass = readAccountRequest(reader);
            } else if (classType.equals("Appointment")) {
                systemClass = readAppointment(reader);
            } else if (classType.equals("AppointmentRequest")) {
                systemClass = readAppointmentRequest(reader);
            } else if (classType.equals("DoctorFeedback")) {
                systemClass = readDoctorFeedback(reader);
            } else if (classType.equals("Medicine")) {
                systemClass = (ISystemObject) readMedicine(reader);
            } else if (classType.equals("Prescription")) {
                systemClass = readPrescription(reader);
            } else if (classType.equals("MedicineOrderRequest")) {
                systemClass = readMedicineOrderRequest(reader);
            } else if (classType.equals("Message")) {
                systemClass = readMessage(reader);
            }

        }
        reader.endObject();
        return systemClass;
    }

    public static IUser readAdministrator(JsonReader reader) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("userId")) {
                userId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            } else if (nameValue.equals("username")) {
                username = reader.nextString();
            } else if (nameValue.equals("password")) {
                password = reader.nextString();
            } else {
                reader.skipValue();
            }

        }

        return new Administrator(userId, name, address, username, password);
    }

    public static IUser readDoctor(JsonReader reader) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        ArrayList feedback = null;
        ArrayList futureAppointments = null;
        ArrayList pastAppointments = null;
        ArrayList messages = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("userId")) {
                userId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            } else if (nameValue.equals("username")) {
                username = reader.nextString();
            } else if (nameValue.equals("password")) {
                password = reader.nextString();
            } else if (nameValue.equals("feedback")) {
                feedback = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("futureAppointments")) {
                futureAppointments = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("pastAppointments")) {
                pastAppointments = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("messages")) {
                messages = readSystemObjectClassArray(reader);
            } else {
                reader.skipValue();
            }

        }

        return new Doctor(userId, name, address, username, password, feedback, futureAppointments, pastAppointments, messages);
    }

    public static IUser readPatient(JsonReader reader) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        String sex = null;
        int age = -1;
        ArrayList futureAppointments = null;
        ArrayList appointmentHistory = null;
        ArrayList prescriptions = null;

        ArrayList messages = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("userId")) {
                userId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            } else if (nameValue.equals("username")) {
                username = reader.nextString();
            } else if (nameValue.equals("password")) {
                password = reader.nextString();
            } else if (nameValue.equals("sex")) {
                sex = reader.nextString();
            } else if (nameValue.equals("age")) {
                age = reader.nextInt();
            } else if (nameValue.equals("appointmentHistory")) {
                appointmentHistory = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("futureAppointments")) {
                futureAppointments = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("prescriptions")) {
                prescriptions = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("messages")) {
                messages = readSystemObjectClassArray(reader);
            } else {
                reader.skipValue();
            }

        }

        return new Patient(userId, name, address, username, password, sex, age, futureAppointments, appointmentHistory, prescriptions, messages);
    }

    public static IUser readSecretary(JsonReader reader) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        ArrayList appointmentRequests = null;
        ArrayList accountCreationRequests = null;
        ArrayList accountDeletionRequests = null;
        ArrayList medicineOrderRequests = null;

        ArrayList messages = null;

        while (reader.hasNext()) {

            String nameValue = reader.nextName();

            if (nameValue.equals("userId")) {
                userId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            } else if (nameValue.equals("username")) {
                username = reader.nextString();
            } else if (nameValue.equals("password")) {
                password = reader.nextString();
            } else if (nameValue.equals("appointmentRequests")) {
                appointmentRequests = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("accountCreationRequests")) {
                accountCreationRequests = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("accountDeletionRequests")) {
                accountDeletionRequests = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("medicineOrderRequests")) {
                medicineOrderRequests = readSystemObjectClassArray(reader);
            } else if (nameValue.equals("messages")) {
                messages = readSystemObjectClassArray(reader);
            } else {
                reader.skipValue();
            }

        }

        return new Secretary(userId, name, address, username, password, appointmentRequests, accountCreationRequests, accountDeletionRequests, medicineOrderRequests, messages);
    }

    public static ISystemObject readAccountCreationRequest(JsonReader reader) throws IOException {
        String objectId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        int age = -1;
        String sex = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            } else if (nameValue.equals("username")) {
                username = reader.nextString();
            } else if (nameValue.equals("password")) {
                password = reader.nextString();
            } else if (nameValue.equals("age")) {
                age = reader.nextInt();
            } else if (nameValue.equals("sex")) {
                sex = reader.nextString();
            } else {
                reader.skipValue();
            }

        }

        return new AccountCreationRequest(objectId, name, address, username, password, age, sex);
    }

    public static ISystemObject readAccountDeletionRequest(JsonReader reader) throws IOException {
        String objectId = null;
        String userId = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("userId")) {
                userId = reader.nextString();
            } else {
                reader.skipValue();
            }

        }

        return new AccountDeletionRequest(objectId, userId);

    }

    public static ISystemObject readAccountRequest(JsonReader reader) throws IOException {
        String objectId = null;
        String name = null;
        String address = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            } else {
                reader.skipValue();
            }

        }

        return new AccountRequest(objectId, name, address);

    }

    public static ISystemObject readAppointment(JsonReader reader) throws IOException {
        String objectId = null;
        String doctorId = null;
        String patientId = null;
        String status = null;
        String notes = null;
        String appointmentDate = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("patientId")) {
                patientId = reader.nextString();
            } else if (nameValue.equals("status")) {
                status = reader.nextString();
            } else if (nameValue.equals("notes")) {
                notes = reader.nextString();
            } else if (nameValue.equals("appointmentDate")) {
                appointmentDate = reader.nextString(); //PLACEHOLDER
            } else {
                reader.skipValue();
            }

        }
        return new Appointment(objectId, doctorId, patientId, status, notes, appointmentDate);

    }

    public static ISystemObject readAppointmentRequest(JsonReader reader) throws IOException {
        String objectId = null;
        String doctorId = null;
        String patientId = null;
        String appointmentDate = null;
        boolean approved = false;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("patientId")) {
                patientId = reader.nextString();
            } else if (nameValue.equals("appointmentDate")) {
                appointmentDate = reader.nextString();
            } else if (nameValue.equals("approved")) {
                approved = reader.nextBoolean();
            } else {
                reader.skipValue();
            }
 
        }
        return new AppointmentRequest(objectId, doctorId, patientId, appointmentDate, approved);

    }

    public static ISystemObject readDoctorFeedback(JsonReader reader) throws IOException {
        String objectId = null;
        String doctorId = null;
        String title = null;
        String feedbackNotes = null;
        int rating = -1;

        while (reader.hasNext()) {

            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("feedbackNotes")) {
                feedbackNotes = reader.nextString();
            } else if (nameValue.equals("title")) {
                title = reader.nextString();
            } else if (nameValue.equals("rating")) {
                rating = reader.nextInt();
            } else {
                reader.skipValue();
            }

        }
        return new DoctorFeedback(objectId, doctorId, title, feedbackNotes, rating);

    }

    public static IUser readMedicine(JsonReader reader) throws IOException {
        String objectId = null;
        String name = null;
        int amountInStock = -1;

        while (reader.hasNext()) {

            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("amountInStock")) {
                amountInStock = reader.nextInt();
            } else {
                reader.skipValue();
            }

        }
        return new Medicine(objectId, name, amountInStock);

    }

    public static ISystemObject readPrescription(JsonReader reader) throws IOException {
        String objectId = null;
        String doctorId = null;
        String patientId = null;
        String doctorNotes = null;

        Medicine medicine = null;

        int quantity = -1;
        int dosage = -1;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("patientId")) {
                patientId = reader.nextString();
            } else if (nameValue.equals("doctorNotes")) {
                doctorNotes = reader.nextString();
            } else if (nameValue.equals("medicine")) {
                medicine = (Medicine) readUserClass(reader);
            } else if (nameValue.equals("quantity")) {
                quantity = reader.nextInt();
            } else if (nameValue.equals("dosage")) {
                dosage = reader.nextInt();
            } else {
                reader.skipValue();
            }

        }
        return new Prescription(objectId, doctorId, patientId, doctorNotes, medicine, quantity, dosage);

    }

    public static ISystemObject readMedicineOrderRequest(JsonReader reader) throws IOException {
        String objectId = null;

        Medicine medicine = null;

        int amountRequested = -1;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("medicine")) {
                medicine = (Medicine) readUserClass(reader);
            } else if (nameValue.equals("amountRequested")) {
                amountRequested = reader.nextInt();
            } else {
                reader.skipValue();
            }

        }
        return new MedicineOrderRequest(objectId, medicine, amountRequested);

    }

    public static ISystemObject readMessage(JsonReader reader) throws IOException {
        String objectId = null;
        String messageType = null;
        String messageUserId = null;
        String messageTitle = null;
        String messageBody = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("messageType")) {
                messageUserId = reader.nextString();
            } else if (nameValue.equals("messageUserId")) {
                messageType = reader.nextString();
            } else if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("messageTitle")) {
                messageTitle = reader.nextString();
            } else if (nameValue.equals("messageBody")) {
                messageBody = reader.nextString();
            } else {
                reader.skipValue();
            }

        }
        return new Message(objectId, messageType, messageUserId, messageTitle, messageBody);

    }

    public static void writeUserData(ArrayList classesArray) throws IOException {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(classesArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
