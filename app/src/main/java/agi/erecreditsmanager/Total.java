package agi.erecreditsmanager;

import java.io.Serializable;

import agi.erecreditsmanager.Type.Type;

public class Total implements Serializable {

    Type culture, major, normal;

    int studentNum;
    String dep; //과 이름 - 추후 타 과 사용자를 위한 업데이트를 고려해 설정해둔 변수.

    public Total(int studentNum) {
        this.studentNum = studentNum;
    }

    public Type getCulture() {
        return culture;
    }
    public void setCulture(Type culture) {
        this.culture = culture;
    }

    public Type getMajor() {
        return major;
    }
    public void setMajor(Type major) {
        this.major = major;
    }

    public Type getNormal() {
        return normal;
    }
    public void setNormal(Type normal) {
        this.normal = normal;
    }

    public int getStudentNum() {
        return studentNum;
    }
    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getDep() {
        return dep;
    }
    public void setDep(String dep) {
        this.dep = dep;
    }
}
