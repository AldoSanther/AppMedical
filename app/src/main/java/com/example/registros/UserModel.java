package com.example.registros;
public class UserModel {
    String id, user, password, phone, mail, family, proceedings, allergies, status;

    public UserModel(String id, String user, String password, String phone, String mail, String family, String proceedings, String allergies,String status) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.phone = phone;
        this.mail = mail;
        this.family = family;
        this.proceedings = proceedings;
        this.allergies = allergies;
        this.status = status;
    }
    public UserModel() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public String getProceedings() {
        return proceedings;
    }
    public void setProceedings(String proceedings) {
        this.proceedings = proceedings;
    }
    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}