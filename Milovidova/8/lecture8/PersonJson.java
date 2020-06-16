package com.company.lecture8;

public class PersonJson {
    @JsonName("name")
    String firstName;

    @JsonName("years")
    double age;

    @JsonIgnore
    String password;

    public PersonJson() {
    }

    public PersonJson(String firstName, double age, String password) {
        this.firstName = firstName;
        this.age = age;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonJson{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}



