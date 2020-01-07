/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

/**
 *
 * @author Tom
 */
public class AccountCreationRequest implements ISystemObject {

    private final String classType = "AccountCreationRequest";
    private String objectId;
    private String name, address, username, password, sex;
    private int age;

    public AccountCreationRequest(String objectId, String name, String Address, String username, String password, int age, String sex) {
        this.objectId = objectId;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getClassType() {
        return classType;
    }

}
