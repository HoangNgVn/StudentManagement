package com.student.management.frontend.model;

public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Integer score;
    private String classId;

    public Student(){}
    public Student(String firstName, String lastName, String email, String gender, Integer score, String classId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.score = score;
        this.classId = classId;
    }

    public Student(Long id, String firstName, String lastName, String email, String gender, Integer score, String classId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.score = score;
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", score=" + score +
                ", classId=" + classId +
                '}';
    }
}
