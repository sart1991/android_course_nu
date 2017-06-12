package com.exercises.sart1991.evaluacionfinal8s.data.apischool.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sart1 on 6/7/2017.
 */

public class Task {

    private int id;
    private String name;
    private @SerializedName("grade_point") double gradePoint;
    private transient String studentName;
    private transient String courseName;
    private @SerializedName("student_id") int studentId;
    private @SerializedName("course_id") int courseId;

    public Task(int id, String name, String studentName, String courseName, double gradePoint) {
        this.id = id;
        this.name = name;
        this.gradePoint = gradePoint;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public Task(int id, String name, double gradePoint, int studentId, int courseId) {
        this.id = id;
        this.name = name;
        this.gradePoint = gradePoint;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gradePoint=" + gradePoint +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
