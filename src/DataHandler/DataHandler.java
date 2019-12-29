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
import Users.*;
import java.util.*;
import com.google.gson.*;
import java.io.*;

public class DataHandler {

    static String filePath = "C:\\Users\\Tom\\Documents\\SOFT252\\src\\Data\\data.json";

    public static typeInterface[] readUserData() {
        Gson gson = new Gson();

        GsonBuilder builder = new GsonBuilder();

// Register custom deserializer for CheckInterface.class
        builder.registerTypeAdapter(typeInterface.class, new TypeDeserializer());
        gson = builder.create();

//        typeInterface[] result2 = gson.fromJson(json, CheckInterface[].class);

        try (FileReader reader = new FileReader(filePath)) {
            typeInterface[] classesArray = gson.fromJson(reader, typeInterface[].class);
//            ArrayList classesArray = gson.fromJson(reader, ArrayList.class);
            //System.out.println(classesArray);

            return classesArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeUserData(ArrayList classesArray, Administrator admin) throws IOException {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(classesArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
