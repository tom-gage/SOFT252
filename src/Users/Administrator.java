/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author Tom
 */
public class Administrator extends User implements typeInterface{

    private final String classType = "Administrator";

    private String userId, name, address;

    public Administrator(String userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    //private String adminUserId; adminName, adminAddress;
//    public Administrator(String adminUserId, String name, String address) {
//
//        this.adminUserId = adminUserId;
//        this.adminName = name;
//        this.adminAddress = address;
//    }
}
