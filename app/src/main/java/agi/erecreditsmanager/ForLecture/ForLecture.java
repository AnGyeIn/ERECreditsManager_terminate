package agi.erecreditsmanager.ForLecture;

import java.io.Serializable;

public class ForLecture implements Serializable {

    String type, name;

    public ForLecture(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
