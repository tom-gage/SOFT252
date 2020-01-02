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
                userClass = readAdministrator(reader, classType);
            } else if (classType.equals("Doctor")) {
                userClass = readDoctor(reader, classType);
            } else if (classType.equals("Patient")) {
                userClass = readPatient(reader, classType);
            } else if (classType.equals("Secretary")) {
                userClass = readSecretary(reader, classType);
            } else if (classType.equals("Medicine")) {
                userClass = readMedicine(reader, classType);
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
                systemClass = readAccountCreationRequest(reader, classType);
            } else if (classType.equals("AccountDeletionRequest")) {
                systemClass = readAccountDeletionRequest(reader, classType);
            } else if (classType.equals("AccountRequest")) {
                systemClass = readAccountRequest(reader, classType);
            } else if (classType.equals("Appointment")) {
                systemClass = readAppointment(reader, classType);
            } else if (classType.equals("AppointmentRequest")) {
                systemClass = readAppointmentRequest(reader, classType);
            } else if (classType.equals("DoctorFeedback")) {
                systemClass = readDoctorFeedback(reader, classType);
            } else if (classType.equals("Medicine")) {
                systemClass = (typeInterface) readMedicine(reader, classType);
            } else if (classType.equals("Prescription")) {
                systemClass = readPrescription(reader, classType);
            }

        }
        reader.endObject();
        return systemClass;
    }

    private static IUser readAdministrator(JsonReader reader, String classType) throws IOException {
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

    private static IUser readDoctor(JsonReader reader, String classType) throws IOException {
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

    private static IUser readPatient(JsonReader reader, String classType) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        String sex = null;
        int age = -1;
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
            } else if (nameValue.equals("prescriptions")) {
                prescriptions = readSystemObjectClassArray(reader);
            } else {
                reader.skipValue();
            }

        }

        return new Patient(userId, name, address, username, password, sex, age, appointmentHistory, prescriptions);
    }

    private static IUser readSecretary(JsonReader reader, String classType) throws IOException {
        String userId = null;
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        ArrayList appointmentRequests = null;
        ArrayList accountCreationRequests = null;
        ArrayList accountDeletionRequests = null;

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
            }

        }

        return new Secretary(userId, name, address, username, password, appointmentRequests, accountCreationRequests, accountDeletionRequests);
    }

    private static typeInterface readAccountCreationRequest(JsonReader reader, String classType) throws IOException {
        String name = null;
        String address = null;
        String username = null;
        String password = null;

        int age = -1;
        String sex = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("name")) {
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

        return new AccountCreationRequest(name, address, username, password, age, sex);
    }

    private static typeInterface readAccountDeletionRequest(JsonReader reader, String classType) throws IOException {
        String username = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("username")) {
                username = reader.nextString();
            }

        }

        return new AccountDeletionRequest(username);

    }

    private static typeInterface readAccountRequest(JsonReader reader, String classType) throws IOException {
        String name = null;
        String address = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("name")) {
                name = reader.nextString();
            } else if (nameValue.equals("address")) {
                address = reader.nextString();
            }

        }

        return new AccountRequest(name, address);

    }

    private static typeInterface readAppointment(JsonReader reader, String classType) throws IOException {
        String doctorId = null;
        String patientId = null;
        Date appointmentDate = null;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("patientId")) {
                patientId = reader.nextString();
            } else if (nameValue.equals("appointmentDate")) {
                appointmentDate = null; //PLACEHOLDER
            }

        }
        return new Appointment(doctorId, patientId);

    }

    private static typeInterface readAppointmentRequest(JsonReader reader, String classType) throws IOException {
        String doctorId = null;
        String patientId = null;
        Date appointmentDate = null;
        boolean approved = false;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("patientId")) {
                patientId = reader.nextString();
            } else if (nameValue.equals("appointmentDate")) {
                appointmentDate = null; //PLACEHOLDER
            } else if (nameValue.equals("approved")) {
                approved = reader.nextBoolean();
            }

        }
        return new AppointmentRequest(doctorId, patientId, approved);

    }

    private static typeInterface readDoctorFeedback(JsonReader reader, String classType) throws IOException {
        String doctorId = null;
        String title = null;
        String feedbackNotes = null;
        int rating = -1;

        while (reader.hasNext()) {

            String nameValue = reader.nextName();

            if (nameValue.equals("doctorId")) {
                doctorId = reader.nextString();
            } else if (nameValue.equals("feedbackNotes")) {
                feedbackNotes = reader.nextString();
            } else if (nameValue.equals("title")) {
                title = reader.nextString();
            } else if (nameValue.equals("rating")) {
                rating = reader.nextInt();
            }

        }
        return new DoctorFeedback(doctorId, title, feedbackNotes, rating);

    }

    private static IUser readMedicine(JsonReader reader, String classType) throws IOException {
        String name = null;

        while (reader.hasNext()) {

            String nameValue = reader.nextName();

            if (nameValue.equals("name")) {
                name = reader.nextString();
            }

        }
        return new Medicine(name);

    }

    private static typeInterface readPrescription(JsonReader reader, String classType) throws IOException {
        String doctorId = null;
        String patientId = null;
        String doctorNotes = null;

        Medicine medicine = null;

        int quantity = -1;
        int dosage = -1;

        while (reader.hasNext()) {
            String nameValue = reader.nextName();

            if (nameValue.equals("doctorId")) {
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
        return new Prescription(doctorId, patientId, doctorNotes, medicine, quantity, dosage);

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
