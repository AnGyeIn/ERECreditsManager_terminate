package agi.erecreditsmanager.Type;

import java.util.ArrayList;

import agi.erecreditsmanager.CreditManager;

import static agi.erecreditsmanager.DataManager.LECTURE_FIELD;
import static agi.erecreditsmanager.DataManager.OFF;
import static agi.erecreditsmanager.DataManager.TYPE;

public class Type extends CreditManager {

    String name;
    int minCredits;
    int credits;
    final int code = TYPE;
    boolean viewSwitch = OFF;
    ArrayList<CreditManager> underManagers = new ArrayList<CreditManager>();

    public Type(String name, int minCredits) {
        this.name = name;
        this.minCredits = minCredits;
    }

    public String getName() {
        return name;
    }

    public int getMinCredits() {
        return minCredits;
    }
    public void setMinCredits(int minCredits) {
        this.minCredits = minCredits;
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
            if(underManagers.get(i).getCode() == LECTURE_FIELD)
                underManagers.get(i).sumCredits();
            credits += underManagers.get(i).getCredits();
        }
    }

    public void removeUnderManager(CreditManager creditManager) {
        underManagers.remove(creditManager);
    }
}
