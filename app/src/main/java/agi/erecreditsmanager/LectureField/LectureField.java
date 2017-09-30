package agi.erecreditsmanager.LectureField;

import java.util.ArrayList;

import agi.erecreditsmanager.CreditManager;

import static agi.erecreditsmanager.DataManager.LECTURE_FIELD;
import static agi.erecreditsmanager.DataManager.LECTURE_GROUP;
import static agi.erecreditsmanager.DataManager.LECTURE_WORLD;
import static agi.erecreditsmanager.DataManager.OFF;

public class LectureField extends CreditManager {

    String name;
    int minCredits;
    int credits;
    final int code = LECTURE_FIELD;
    boolean viewSwitch = OFF;
    ArrayList<CreditManager> underManagers = new ArrayList<CreditManager>();

    public LectureField(String name, int minCredits) {
        this.name = name;
        this.minCredits = minCredits;
    }

    public String getName() {
        return name;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public int getCredits() {
        return credits;
    }

    public int getCode() {
        return code;
    }

    public boolean getViewSwitch() {
        return viewSwitch;
    }
    public void setViewSwitch(boolean viewSwitch) {
        this.viewSwitch = viewSwitch;
    }

    public void addUnderManager(CreditManager creditManager) {
        underManagers.add(creditManager);
    }

    public void addUnderManager(int i, CreditManager creditManager) {
        underManagers.add(i, creditManager);
    }

    public int getSize() {
        return underManagers.size();
    }

    public CreditManager getUnderManager (int i) {
        return underManagers.get(i);
    }

    public void sumCredits() {
        credits = 0;
        for(int i = 0; i < getSize(); i++) {
            if(underManagers.get(i).getCode() == LECTURE_GROUP || underManagers.get(i).getCode() == LECTURE_WORLD)
                underManagers.get(i).sumCredits();
            credits += underManagers.get(i).getCredits();
        }
    }

    public void removeUnderManager(CreditManager creditManager) {
        underManagers.remove(creditManager);
    }
}
