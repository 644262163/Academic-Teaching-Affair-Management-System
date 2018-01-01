package com.niit.bean;

public class StudentCourse {
    private String id;
    private String studentId;
    private String courseId;
    private String term;
    private String information;

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }

}
