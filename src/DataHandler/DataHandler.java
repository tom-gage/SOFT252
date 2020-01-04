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
import Users.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class DataHandler {

    static String filePath = "C:\\Users\\Tom\\Documents\\SOFT252\\src\\Data\\data.json";

    public static ArrayList readUserData() throws FileNotFoundException, IOException {
        Gson gson = new Gson();

        File data = new File(filePath);
        InputStream in;
        in = new FileInputStream(data);

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        ArrayList classesArray = readClassesArray(reader);

//        System.out.println(classesArray);
        return classesArray;

    }

    private static ArrayList readClassesArray(JsonReader reader) throws IOException {
        ArrayList classesArray = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            classesArray.add(readUserClassArray(reader));
        }
        reader.endArray();
        return classesArray;
    }

    private static ArrayList<IUser> readUserClassArray(JsonReader reader) throws IOException {
        ArrayList classArray = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            classArray.add(readUserClass(reader));
        }
        reader.endArray();
        return classArray;
    }

    private static IUser readUserClass(JsonReader reader) throws IOException {
        String classType = "";
        IUser userClass = null;
        reader.beginObject();
        while (reader.hasNext()) {

//            System.out.println(reader.nextName());
            reader.nextName();
            classType = reader.nextString();
//            System.out.println(classType);

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

    private static ArrayList<typeInterface> readSystemObjectClassArray(JsonReader reader) throws IOException {
        ArrayList classArray = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            classArray.add(readSystemObjectClass(reader));
        }
        reader.endArray();
        return classArray;
    }

    private static typeInterface readSystemObjectClass(JsonReader reader) throws IOException {
        String classType = "";
        typeInterface systemClass = null;
        reader.beginObject();
        while (reader.hasNext()) {

//            System.out.println(reader.nextName());
            reader.nextName();
            classType = reader.nextString();
//            System.out.println(classType);

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
                systemClass = (typeInterface) readMedicine(reader);
            } else if (classType.equals("Prescription")) {
                systemClass = readPrescription(reader);
            } else if (classType.equals("MedicineOrderRequest")) {
                systemClass = readMedicineOrderRequest(reader); //////////////////////////////////////////////
            }

        }
        reader.endObject();
        return systemClass;
    }

    private static IUser readAdministrator(JsonReader reader) throws IOException {
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

    private static IUser readDoctor(JsonReader reader) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        ArrayList feedback = null;
        ArrayList futureAppointments = null;
        ArrayList pastAppointments = null;

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
            }

        }

        return new Doctor(userId, name, address, username, password, feedback, futureAppointments, pastAppointments);
    }

    private static IUser readPatient(JsonReader reader) throws IOException {
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
            } else {
                reader.skipValue();
            }

        }

        return new Patient(userId, name, address, username, password, sex, age, futureAppointments, appointmentHistory, prescriptions);
    }

    private static IUser readSecretary(JsonReader reader) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        ArrayList appointmentRequests = null;
        ArrayList accountCreationRequests = null;
        ArrayList accountDeletionRequests = null;
        ArrayList medicineOrderRequests = null;

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
            }

        }

        return new Secretary(userId, name, address, username, password, appointmentRequests, accountCreationRequests, accountDeletionRequests, medicineOrderRequests);
    }

    private static typeInterface readAccountCreationRequest(JsonReader reader) throws IOException {
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
            }

        }

        return new AccountCreationRequest(objectId, name, address, username, password, age, sex);
    }

    private static typeInterface readAccountDeletionRequest(JsonReader reader) throws IOException {
        String objectId = null;
        String username = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("objectId")) {
                objectId = reader.nextString();
            } else if (nameValue.equals("username")) {
                username = reader.nextString();
            }

        }

        return new AccountDeletionRequest(objectId, username);

    }

    private static typeInterface readAccountRequest(JsonReader reader) throws IOException {
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
            }

        }

        return new AccountRequest(objectId, name, address);

    }

    private static typeInterface readAppointment(JsonReader reader) throws IOException {
        String objectId = null;
        String doctorId = null;
        String patientId = null;
        String status = null;
        String notes = null;
        Date appointmentDate = null;

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
                appointmentDate = null; //PLACEHOLDER
            }

        }
        return new Appointment(objectId, doctorId, patientId, status, notes, appointmentDate);

    }

    private static typeInterface readAppointmentRequest(JsonReader reader) throws IOException {
        String objectId = null;
        String doctorId = null;
        String patientId = null;
        Date appointmentDate = null;
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
                appointmentDate = null; //PLACEHOLDER
            } else if (nameValue.equals("approved")) {
                approved = reader.nextBoolean();
            }

        }
        return new AppointmentRequest(objectId, doctorId, patientId, appointmentDate, approved);

    }

    private static typeInterface readDoctorFeedback(JsonReader reader) throws IOException {
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
            }

        }
        return new DoctorFeedback(objectId, doctorId, title, feedbackNotes, rating);

    }

    private static IUser readMedicine(JsonReader reader) throws IOException {
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
            }

        }
        return new Medicine(objectId, name, amountInStock);

    }

    private static typeInterface readPrescription(JsonReader reader) throws IOException {
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
            }

        }
        return new Prescription(objectId, doctorId, patientId, doctorNotes, medicine, quantity, dosage);

    }

    private static typeInterface readMedicineOrderRequest(JsonReader reader) throws IOException {
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
            } 

        }
        return new MedicineOrderRequest(objectId, medicine, amountRequested);

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
