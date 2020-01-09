/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.DataHandler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import DataHandler.DataHandler;
import SystemObjects.ISystemObject;
import Users.IUser;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author Tom
 */
public class DataHandlerTest {

    private ArrayList adminArray;
    private ArrayList doctorArray;
    private ArrayList patientArray;
    private ArrayList secretaryArray;

    private ArrayList classesArray;
    private ArrayList objectArray;

    private Gson gson;
    private String testJson;

    public DataHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        objectArray = new ArrayList();
        objectArray = new ArrayList();

        adminArray = new ArrayList();
        doctorArray = new ArrayList();
        patientArray = new ArrayList();
        secretaryArray = new ArrayList();

        classesArray = new ArrayList();

        testJson = "";

        gson = new Gson();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void readClssesArray() throws UnsupportedEncodingException, IOException {
        //set up test data
        adminArray.add(Factory.createUser("Administrator"));
        doctorArray.add(Factory.createUser("Doctor"));
        patientArray.add(Factory.createUser("Patient"));
        secretaryArray.add(Factory.createUser("Secretary"));

        classesArray.add(adminArray);
        classesArray.add(doctorArray);
        classesArray.add(patientArray);
        classesArray.add(secretaryArray);

        //convert to json string
        testJson = gson.toJson(classesArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList actual = DataHandler.readClassesArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects
        assertEquals("Fails to return array properly", gson.toJson(classesArray), gson.toJson(actual));

    }

    @Test
    public void readUserClassArrayAdmin() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createUser("Administrator"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<IUser> actual = DataHandler.readUserClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects
        assertEquals("Fails to return Administrator array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readUserClassArrayDoctor() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createUser("Doctor"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<IUser> actual = DataHandler.readUserClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects
        assertEquals("Fails to return Doctor array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readUserClassArrayPatient() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createUser("Patient"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<IUser> actual = DataHandler.readUserClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects
        assertEquals("Fails to return Patient array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readUserClassArraySecretary() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createUser("Secretary"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<IUser> actual = DataHandler.readUserClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects
        assertEquals("Fails to return Secretary array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readUserClassAdmin() throws UnsupportedEncodingException, IOException {
        //convert to json string
        testJson = gson.toJson(Factory.createUser("Administrator"));

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        IUser actual = DataHandler.readUserClass(reader);

        //compare results 
        assertEquals("Fails to return user class properly", Factory.createUser("Administrator").getClass(), actual.getClass());
    }

    @Test
    public void readUserClassDoctor() throws UnsupportedEncodingException, IOException {
        //convert to json string
        testJson = gson.toJson(Factory.createUser("Doctor"));

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        IUser actual = DataHandler.readUserClass(reader);

        //compare results 
        assertEquals("Fails to return Doctor properly", Factory.createUser("Doctor").getClass(), actual.getClass());
    }

    @Test
    public void readUserClassPatient() throws UnsupportedEncodingException, IOException {
        //convert to json string
        testJson = gson.toJson(Factory.createUser("Patient"));

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        IUser actual = DataHandler.readUserClass(reader);

        //compare results 
        assertEquals("Fails to return Patient properly", Factory.createUser("Patient").getClass(), actual.getClass());
    }

    @Test
    public void readUserClassSecretary() throws UnsupportedEncodingException, IOException {
        //convert to json string
        testJson = gson.toJson(Factory.createUser("Secretary"));

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        IUser actual = DataHandler.readUserClass(reader);

        //compare results 
        assertEquals("Fails to return Secretary properly", Factory.createUser("Secretary").getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassArrayAccCrRequest() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("AccountCreationRequest"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return AccountCreationRequest array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayAccDelRequest() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("AccountDeletionRequest"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return AccountDeletionRequest array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayAppointment() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("Appointment"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return Appointment array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayAppointmentRequest() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("AppointmentRequest"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return AppointmentRequest array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayDoctorFeedback() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("DoctorFeedback"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return DoctorFeedback array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayMedicine() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("Medicine"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return Medicine array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayMedicineOrderRequest() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("MedicineOrderRequest"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return MedicineOrderRequest array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayMessage() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("Message"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return Message array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassArrayPrescription() throws UnsupportedEncodingException, IOException {
        objectArray.add(Factory.createObject("Prescription"));

        //convert to json string
        testJson = gson.toJson(objectArray);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ArrayList<ISystemObject> actual = DataHandler.readSystemObjectClassArray(reader);

        //compare results as json because assert equals doesn't like comparing arrays of objects directly
        assertEquals("Fails to return Prescription array properly", gson.toJson(objectArray), gson.toJson(actual));
    }

    @Test
    public void readSystemObjectClassAccCrRequest() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("AccountCreationRequest");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return AccountCreationRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassAccDelRequest() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("AccountDeletionRequest");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return AccountDeletionRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassAppointment() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("Appointment");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return Appointment properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassAppointmentRequest() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("AppointmentRequest");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return AppointmentRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassDoctorFeedback() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("DoctorFeedback");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return DoctorFeedback properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassMedicine() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("Medicine");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return Medicine properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassMedicineOrderRequest() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("MedicineOrderRequest");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return MedicineOrderRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassMessage() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("Message");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return Message properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSystemObjectClassPrescription() throws UnsupportedEncodingException, IOException {
        //convert to json string
        ISystemObject object = Factory.createObject("Prescription");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        ISystemObject actual = DataHandler.readSystemObjectClass(reader);

        //compare results 
        assertEquals("Fails to return Prescription properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readAdministrator() throws UnsupportedEncodingException, IOException {
        IUser object = Factory.createUser("Administrator");
        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        IUser actual = DataHandler.readAdministrator(reader);
        reader.endObject();
        //compare results 
        assertEquals("Fails to return Administrator properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readDoctor() throws UnsupportedEncodingException, IOException {
        IUser object = Factory.createUser("Doctor");
        testJson = gson.toJson(object);
        System.out.println(testJson);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        IUser actual = DataHandler.readDoctor(reader);

        //compare results 
        assertEquals("Fails to return Doctor properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readPatient() throws UnsupportedEncodingException, IOException {
        IUser object = Factory.createUser("Patient");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        IUser actual = DataHandler.readPatient(reader);

        //compare results 
        assertEquals("Fails to return Patient properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readSecretary() throws UnsupportedEncodingException, IOException {
        IUser object = Factory.createUser("Secretary");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        IUser actual = DataHandler.readSecretary(reader);

        //compare results 
        assertEquals("Fails to return Secretary properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readAccountCreationRequest() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("AccountCreationRequest");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readAccountCreationRequest(reader);

        //compare results 
        assertEquals("Fails to return AccountCreationRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readAccountDeletionRequest() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("AccountDeletionRequest");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readAccountDeletionRequest(reader);

        //compare results 
        assertEquals("Fails to return AccountDeletionRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readAppointment() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("Appointment");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readAppointment(reader);

        //compare results 
        assertEquals("Fails to return Appointment properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readAppointmentRequest() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("AppointmentRequest");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readAppointmentRequest(reader);

        //compare results 
        assertEquals("Fails to return AppointmentRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readDoctorFeedback() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("DoctorFeedback");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readDoctorFeedback(reader);

        //compare results 
        assertEquals("Fails to return DoctorFeedback properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readMedicine() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("Medicine");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        IUser actual = DataHandler.readMedicine(reader);

        //compare results 
        assertEquals("Fails to return Medicine properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readMedicineOrderRequest() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("MedicineOrderRequest");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readMedicineOrderRequest(reader);

        //compare results 
        assertEquals("Fails to return MedicineOrderRequest properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readMessage() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("Message");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readMessage(reader);

        //compare results 
        assertEquals("Fails to return Message properly", object.getClass(), actual.getClass());
    }

    @Test
    public void readPrescription() throws UnsupportedEncodingException, IOException {
        ISystemObject object = Factory.createObject("Prescription");

        testJson = gson.toJson(object);

        //convert string to input stream
        InputStream inputStream = new ByteArrayInputStream(testJson.getBytes(Charset.forName("UTF-8")));
        //convert inputstream to jsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        //read json string into resultant
        reader.beginObject();
        ISystemObject actual = DataHandler.readPrescription(reader);

        //compare results 
        assertEquals("Fails to return Prescription properly", object.getClass(), actual.getClass());
    }

}
