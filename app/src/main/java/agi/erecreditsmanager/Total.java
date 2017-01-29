package agi.erecreditsmanager;

import java.io.Serializable;

import agi.erecreditsmanager.Type.Type;

public class Total implements Serializable {

    Type culture, major, normal;
    boolean checker;

    public Total(boolean checker) {
        this.checker = checker;
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

    public boolean isChecker() {
        return checker;
    }
    public void setChecker(boolean checker) {
        this.checker = checker;
    }
}
