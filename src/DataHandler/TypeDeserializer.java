/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHandler;

import System.*;
import Users.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class TypeDeserializer implements JsonDeserializer<typeInterface> {

    @Override
    public typeInterface deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jArray = (JsonArray) json;
        System.out.println(jArray);
        JsonObject jObject = (JsonObject) jArray.get(0);
//        System.out.println(jObject);
        JsonElement typeObj = jObject.get("classType");
//        System.out.println(typeObj);

//        JsonObject jObject = (JsonObject) json;
//        JsonElement typeObj = jObject.get("TypeName");
        if (typeObj != null) {
            String typeVal = typeObj.getAsString();

            switch (typeVal) {
                case "AccountCreationRequest":
                    return context.deserialize(jObject, AccountCreationRequest.class);
                case "AccountDeletionRequest":
                    return context.deserialize(jObject, AccountDeletionRequest.class);
                case "Appointment":
                    return context.deserialize(jObject, Appointment.class);
                case "AppointmentRequest":
                    return context.deserialize(jObject, Appointment.class);
                case "DoctorFeedback":
                    return context.deserialize(jObject, DoctorFeedback.class);
                case "Medicine":
                    return context.deserialize(jObject, Medicine.class);
                case "Prescription":
                    return context.deserialize(jObject, Prescription.class);

                case "Administrator":
                    return context.deserialize(jObject, Administrator.class);
                case "Doctor":
                    return context.deserialize(jObject, Doctor.class);
                case "Patient":
                    return context.deserialize(jObject, Patient.class);
                case "Secretary":
                    return context.deserialize(jObject, Secretary.class);

            }
        }

        return null;
    }

//    @Override
//    public typeInterface deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
