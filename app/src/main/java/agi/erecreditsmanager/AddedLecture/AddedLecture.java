package agi.erecreditsmanager.AddedLecture;

import agi.erecreditsmanager.CreditManager;
import agi.erecreditsmanager.FreeLecture.FreeLecture;

import static agi.erecreditsmanager.DataManager.ADDED_LECTURE;

public class AddedLecture extends CreditManager {

    String name;
    int credit;
    int multiplier;
    int code = ADDED_LECTURE;
    CreditManager upperManager;

    public AddedLecture(String name, int credit, CreditManager upperManager) {
        this.name = name;
        this.credit = credit;
        this.upperManager = upperManager;
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

    public void removeThis() {
        upperManager.removeUnderManager(this);
        ((FreeLecture) upperManager.getUnderManager(upperManager.getSize()-1)).decNum();
    }
}
