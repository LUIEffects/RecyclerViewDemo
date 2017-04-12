package com.lewish.start.diffutildemo.entity;

/**
 * author: sundong
 * created at 2017/4/12 11:39
 */
public class Student implements Cloneable{
    private String stdId;
    private String stdName;
    private int stdAge;
    private String stdPhoneNum;
    private stdGender stdGender;
    public enum  stdGender{
        BOY,GIRL;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        Student std = null;
        try {
            std = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return std;
    }

    public Student(String stdId, String stdName, int stdAge, String stdPhoneNum, Student.stdGender stdGender) {
        this.stdId = stdId;
        this.stdName = stdName;
        this.stdAge = stdAge;
        this.stdPhoneNum = stdPhoneNum;
        this.stdGender = stdGender;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public int getStdAge() {
        return stdAge;
    }

    public void setStdAge(int stdAge) {
        this.stdAge = stdAge;
    }

    public String getStdPhoneNum() {
        return stdPhoneNum;
    }

    public void setStdPhoneNum(String stdPhoneNum) {
        this.stdPhoneNum = stdPhoneNum;
    }

    public Student.stdGender getStdGender() {
        return stdGender;
    }

    public void setStdGender(Student.stdGender stdGender) {
        this.stdGender = stdGender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (stdAge != student.stdAge) return false;
        if (stdId != null ? !stdId.equals(student.stdId) : student.stdId != null) return false;
        if (stdName != null ? !stdName.equals(student.stdName) : student.stdName != null)
            return false;
        if (stdPhoneNum != null ? !stdPhoneNum.equals(student.stdPhoneNum) : student.stdPhoneNum != null)
            return false;
        return stdGender == student.stdGender;

    }

    @Override
    public int hashCode() {
        int result = stdId != null ? stdId.hashCode() : 0;
        result = 31 * result + (stdName != null ? stdName.hashCode() : 0);
        result = 31 * result + stdAge;
        result = 31 * result + (stdPhoneNum != null ? stdPhoneNum.hashCode() : 0);
        result = 31 * result + (stdGender != null ? stdGender.hashCode() : 0);
        return result;
    }
}
