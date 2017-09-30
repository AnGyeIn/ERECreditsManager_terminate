package agi.erecreditsmanager.Lecture;

import agi.erecreditsmanager.CreditManager;

import static agi.erecreditsmanager.DataManager.LECTURE;

public class Lecture extends CreditManager {

    String name;
    int credit;
    int multiplier;
    final int code = LECTURE;

    public Lecture(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public int getMultiplier() {
        return multiplier;
    }
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getCode() {
        return code;
    }

    public int getCredits() {
        return credit * multiplier;
    }
}
