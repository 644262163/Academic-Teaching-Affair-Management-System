package com.niit.bean;

public class TeacherCourse {
    private String id;
    private String teacherId;
    private String courseId;
    private String term;
    private String information;

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }
    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
