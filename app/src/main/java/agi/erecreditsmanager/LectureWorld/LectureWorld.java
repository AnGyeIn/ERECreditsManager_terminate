package agi.erecreditsmanager.LectureWorld;

import java.util.ArrayList;

import agi.erecreditsmanager.CreditManager;

import static agi.erecreditsmanager.ConstManager.LECTURE_WORLD;
import static agi.erecreditsmanager.ConstManager.OFF;

public class LectureWorld extends CreditManager {

    String name;
    int credits;
    final int code = LECTURE_WORLD;
    boolean viewSwitch = OFF;
    ArrayList<CreditManager> underManagers = new ArrayList<CreditManager>();

    public LectureWorld(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
        for(int i = 0; i < getSize(); i++)
            credits += underManagers.get(i).getCredits();
    }

    public void removeUnderManager(CreditManager creditManager) {
        underManagers.remove(creditManager);
    }
}
