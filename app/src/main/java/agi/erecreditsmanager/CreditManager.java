package agi.erecreditsmanager;

import java.io.Serializable;
import java.util.ArrayList;

public class CreditManager implements Serializable {

    int credits;
    int code;
    ArrayList<CreditManager> underManagers = new ArrayList<CreditManager>();

    public int getCredits() {
        return credits;
    }

    public int getCode() {
        return code;
    }

    public void addUnderManager(CreditManager creditManager) {
        underManagers.add(creditManager);
    }

    public void addUnderManager(int i, CreditManager creditManager) {
        underManagers.add(i, creditManager);
    }

    public void removeUnderManager(CreditManager creditManager) {
        underManagers.remove(creditManager);
    }

    public void sumCredits() {

    }

    public CreditManager getUnderManager(int i) {
        return underManagers.get(i);
    }

    public int getSize() {
        return underManagers.size();
    }
}
