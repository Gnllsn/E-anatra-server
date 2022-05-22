package com.example.eanatramobile.modele;

public class Users {

    String name ;
    String firstname ;
    int age ;
    String pwd ;
    String email ;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return getName()+","+getFirstname()+","+getAge()+","+getEmail()+","+getPwd();
    }
}
