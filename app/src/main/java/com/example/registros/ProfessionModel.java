package com.example.registros;

public class ProfessionModel {
     String id, name, birthday, gender, address, consultingoom, specialty, phone, professionalid;

    public ProfessionModel() {

    }

    public ProfessionModel(String id, String name, String birthday, String gender, String address, String consultingoom, String specialty, String phone, String professionalid) {

        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.consultingoom = consultingoom;
        this.specialty = specialty;
        this.phone = phone;
        this.professionalid = professionalid;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsultingoom() {
        return consultingoom;
    }

    public void setConsultingoom(String consultingoom) {
        this.consultingoom = consultingoom;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfessionalid() {
        return professionalid;
    }

    public void setProfessionalid(String professionalid) {
        this.professionalid = professionalid;
    }
}
