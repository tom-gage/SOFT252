/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Users.typeInterface;

/**
 *
 * @author Tom
 */
public class DoctorFeedback implements typeInterface{

    private final String classType = "DoctorFeedback";
    private String doctorId, feedbackNotes;
    private int rating;

    public DoctorFeedback(String doctorId, String feedbackNotes, int rating) {
        this.doctorId = doctorId;
        this.feedbackNotes = feedbackNotes;
        this.rating = rating;
    }
}
