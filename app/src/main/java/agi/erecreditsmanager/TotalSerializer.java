package agi.erecreditsmanager;

import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import agi.erecreditsmanager.AddedLecture.AddedLecture;
import agi.erecreditsmanager.FreeLecture.FreeLecture;
import agi.erecreditsmanager.Lecture.Lecture;
import agi.erecreditsmanager.LectureField.LectureField;
import agi.erecreditsmanager.LectureGroup.LectureGroup;
import agi.erecreditsmanager.LectureWorld.LectureWorld;
import agi.erecreditsmanager.Type.Type;

public class TotalSerializer {
    private ArrayList<CreditManager> tot = new ArrayList<>();
    private CreditManager initCreditManager;

    TotalSerializer(CreditManager initCreditManager) {
        this.initCreditManager = initCreditManager;
    }

    JsonObject totalize() throws JSONException {
        tot.add(initCreditManager);
        for(int idx = 0; idx < tot.size(); idx++)
            switch(tot.get(idx).getCode()) {
                case 0:
                    Type type = (Type) tot.get(idx);
                    for(int j = 0; j < type.getSize(); j++)
                        tot.add(idx + 1 + j, type.getUnderManager(j));
                    break;
                case 1:
                    LectureField lectureField = (LectureField) tot.get(idx);
                    for(int j = 0; j < lectureField.getSize(); j++)
                        tot.add(idx + 1 + j, lectureField.getUnderManager(j));
                    break;
                case 2:
                    LectureGroup lectureGroup = (LectureGroup) tot.get(idx);
                    for(int j = 0; j < lectureGroup.getSize(); j++)
                        tot.add(idx + 1 + j, lectureGroup.getUnderManager(j));
                    break;
                case 3:
                    LectureWorld lectureWorld = (LectureWorld) tot.get(idx);
                    for(int j = 0; j < lectureWorld.getSize(); j++)
                        tot.add(idx + 1 + j, lectureWorld.getUnderManager(j));
                    break;
            }

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> creditss = new ArrayList<>();
        ArrayList<Integer> minCredits = new ArrayList<>();
        ArrayList<Integer> codes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> underManagers = new ArrayList<>();
        ArrayList<Boolean> viewSwitches = new ArrayList<>();
        ArrayList<Integer> credits = new ArrayList<>();
        ArrayList<Integer> upperManagers = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();

        HashMap<Integer, Integer> match = new HashMap<>();

        for(int idx = 0; idx < tot.size(); idx++) {
            CreditManager curManager = tot.get(idx);
            codes.add(curManager.getCode());

            switch(curManager.getCode()) {
                case 0:
                    Type type = (Type) curManager;
                    names.add("\""+type.getName()+"\"");
                    creditss.add(type.getCredits());
                    minCredits.add(type.getMinCredits());
                    ArrayList<Integer> typeList = new ArrayList<>();
                    for(int j = 0; j < type.getSize(); j++) {
                        typeList.add(tot.indexOf(type.getUnderManager(j)));
                        if(type.getUnderManager(j) instanceof AddedLecture)
                            match.put(typeList.get(j), idx);
                    }
                    underManagers.add(typeList);
                    viewSwitches.add(type.getViewSwitch());
                    credits.add(null);
                    upperManagers.add(null);
                    nums.add(null);
                    break;
                case 1:
                    LectureField lectureField = (LectureField) curManager;
                    names.add("\""+lectureField.getName()+"\"");
                    creditss.add(lectureField.getCredits());
                    minCredits.add(lectureField.getMinCredits());
                    ArrayList<Integer> lfList = new ArrayList<>();
                    for(int j = 0; j < lectureField.getSize(); j++) {
                        lfList.add(tot.indexOf(lectureField.getUnderManager(j)));
                        if(lectureField.getUnderManager(j) instanceof AddedLecture)
                            match.put(lfList.get(j), idx);
                    }
                    underManagers.add(lfList);
                    viewSwitches.add(lectureField.getViewSwitch());
                    credits.add(null);
                    upperManagers.add(null);
                    nums.add(null);
                    break;
                case 2:
                    LectureGroup lectureGroup = (LectureGroup) curManager;
                    if(lectureGroup.getName().contains("\n"))
                        names.add("\""+lectureGroup.getName().replaceFirst("\n", "n")+"\"");
                    else
                        names.add("\""+lectureGroup.getName()+"\"");
                    creditss.add(lectureGroup.getCredits());
                    minCredits.add(lectureGroup.getMinCredits());
                    ArrayList<Integer> lgList = new ArrayList<>();
                    for(int j = 0; j < lectureGroup.getSize(); j++) {
                        lgList.add(tot.indexOf(lectureGroup.getUnderManager(j)));
                        if(lectureGroup.getUnderManager(j) instanceof AddedLecture)
                            match.put(lgList.get(j), idx);
                    }
                    underManagers.add(lgList);
                    viewSwitches.add(lectureGroup.getViewSwitch());
                    credits.add(null);
                    upperManagers.add(null);
                    nums.add(null);
                    break;
                case 3:
                    LectureWorld lectureWorld = (LectureWorld) curManager;
                    names.add("\""+lectureWorld.getName()+"\"");
                    creditss.add(lectureWorld.getCredits());
                    minCredits.add(null);
                    ArrayList<Integer> lwList = new ArrayList<>();
                    for(int j = 0; j < lectureWorld.getSize(); j++) {
                        lwList.add(tot.indexOf(lectureWorld.getUnderManager(j)));
                        if(lectureWorld.getUnderManager(j) instanceof AddedLecture)
                            match.put(lwList.get(j), idx);
                    }
                    underManagers.add(lwList);
                    viewSwitches.add(lectureWorld.getViewSwitch());
                    credits.add(null);
                    upperManagers.add(null);
                    nums.add(null);
                    break;
                case 4:
                    Lecture lecture = (Lecture) curManager;
                    names.add("\""+lecture.getName()+"\"");
                    creditss.add(lecture.getMultiplier() == 1 ? lecture.getCredit() : 0);
                    minCredits.add(null);
                    underManagers.add(new ArrayList<Integer>());
                    viewSwitches.add(false);
                    credits.add(lecture.getCredit());
                    upperManagers.add(null);
                    nums.add(null);
                    break;
                case 5:
                    FreeLecture freeLecture = (FreeLecture) curManager;
                    names.add(null);
                    creditss.add(0);
                    minCredits.add(null);
                    underManagers.add(new ArrayList<Integer>());
                    viewSwitches.add(false);
                    credits.add(null);
                    upperManagers.add(tot.indexOf(freeLecture.getUpperManager()));
                    nums.add(freeLecture.getNum());
                    break;
                case 6:
                    AddedLecture addedLecture = (AddedLecture) curManager;
                    names.add("\""+addedLecture.getName()+"\"");
                    creditss.add(addedLecture.getMultiplier() == 1 ? addedLecture.getCredit() : 0);
                    minCredits.add(null);
                    underManagers.add(new ArrayList<Integer>());
                    viewSwitches.add(false);
                    credits.add(addedLecture.getCredit());
                    upperManagers.add(match.get(idx));
                    nums.add(null);
                    break;
            }
        }

        JsonObject object = new JsonObject();
        object.addProperty("names", names.toString());
        object.addProperty("creditss", creditss.toString());
        object.addProperty("minCredits", minCredits.toString());
        object.addProperty("codes", codes.toString());
        object.addProperty("underManagers", underManagers.toString());
        object.addProperty("viewSwitches", viewSwitches.toString());
        object.addProperty("credits", credits.toString());
        object.addProperty("upperManagers", upperManagers.toString());
        object.addProperty("nums", nums.toString());

        return object;
    }
}
