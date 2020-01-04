/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class DoctorFeedback implements typeInterface {

    private final String classType = "DoctorFeedback";
    private String objectId;

    private String doctorId, title, feedbackNotes;
    private int rating;

    public DoctorFeedback(String objectId, String doctorId, String title, String feedbackNotes, int rating) {
        this.objectId = objectId;
        this.doctorId = doctorId;
        this.title = title;
        this.feedbackNotes = feedbackNotes;
        this.rating = rating;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getTitle() {
        return title;
    }

    public String getFeedbackNotes() {
        return feedbackNotes;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

}
