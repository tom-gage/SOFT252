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
public class Medicine implements typeInterface{

    private final String classType = "Medicine";
    private String name;

    public Medicine(String name) {
        this.name = name;
    }
}
