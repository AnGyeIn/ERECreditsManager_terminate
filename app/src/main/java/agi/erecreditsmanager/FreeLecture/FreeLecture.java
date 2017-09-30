package agi.erecreditsmanager.FreeLecture;

import agi.erecreditsmanager.CreditManager;

import static agi.erecreditsmanager.DataManager.FREE_LECTURE;

public class FreeLecture extends CreditManager {

    final int code = FREE_LECTURE;
    CreditManager upperManager;
    int num;

    public FreeLecture(CreditManager upperManager, int num) {
        this.upperManager = upperManager;
        this.num = num;
    }

    public int getCode() {
        return code;
    }

    public CreditManager getUpperManager() {
        return upperManager;
    }

    public int getNum() {
        return num;
    }
    public void incNum() {
        num++;
    }
    public void decNum() {
        num--;
    }
}
