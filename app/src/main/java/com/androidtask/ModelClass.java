package com.androidtask;

public class ModelClass {

    private String Userid;
    private String Name;
    private String Email;


    public ModelClass(String userid,String name, String email) {
        Userid = userid;
        Name = name;
        Email = email;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
