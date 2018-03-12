package agi.erecreditsmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import agi.erecreditsmanager.AddedLecture.AddedLecture;
import agi.erecreditsmanager.AddedLecture.AddedLectureLayout;
import agi.erecreditsmanager.FreeLecture.FreeLecture;
import agi.erecreditsmanager.FreeLecture.FreeLectureLayout;
import agi.erecreditsmanager.Lecture.Lecture;
import agi.erecreditsmanager.Lecture.LectureLayout;
import agi.erecreditsmanager.LectureField.LectureField;
import agi.erecreditsmanager.LectureField.LectureFieldLayout;
import agi.erecreditsmanager.LectureGroup.LectureGroup;
import agi.erecreditsmanager.LectureGroup.LectureGroupLayout;
import agi.erecreditsmanager.LectureWorld.LectureWorld;
import agi.erecreditsmanager.LectureWorld.LectureWorldLayout;
import agi.erecreditsmanager.Type.Type;
import agi.erecreditsmanager.Type.TypeLayout;

import static agi.erecreditsmanager.DataManager.ADDED_LECTURE;
import static agi.erecreditsmanager.DataManager.FREE_LECTURE;
import static agi.erecreditsmanager.DataManager.LECTURE;
import static agi.erecreditsmanager.DataManager.LECTURE_FIELD;
import static agi.erecreditsmanager.DataManager.LECTURE_GROUP;
import static agi.erecreditsmanager.DataManager.LECTURE_WORLD;
import static agi.erecreditsmanager.DataManager.OFF;
import static agi.erecreditsmanager.DataManager.ON;
import static agi.erecreditsmanager.DataManager.TYPE;
import static agi.erecreditsmanager.MainActivity.ERETMToast;

public class MainAdapter extends BaseAdapter {

    ArrayList<CreditManager> creditManagers = new ArrayList<CreditManager>();
    Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return creditManagers.size();
    }

    @Override
    public Object getItem(int i) {
        return creditManagers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CreditManager creditManager = creditManagers.get(i);
        int code = creditManager.getCode();

        RelativeLayout layout = new RelativeLayout(context);    //return 시 nullPointer를 반환하지 않기 위한 형식적인 초기화

        if(code == TYPE)
            layout = (RelativeLayout) getTypeLayout(i);
        else if(code == LECTURE_FIELD)
            layout = (RelativeLayout) getLectureFieldLayout(i);
        else if(code == LECTURE_GROUP)
            layout = (RelativeLayout) getLectureGroupLayout(i);
        else if(code == LECTURE_WORLD)
            layout = (RelativeLayout) getLectureWorldLayout(i);
        else if(code == LECTURE)
            layout = (RelativeLayout) getLectureLayout(i);
        else if(code == FREE_LECTURE)
            layout = (RelativeLayout) getFreeLectureLayout(i);
        else if(code == ADDED_LECTURE)
            layout = (RelativeLayout) getAddedLectureLayout(i);

        return layout;
    }


    private View getTypeLayout(int i) {
        TypeLayout layout = new TypeLayout(context);
        final Type type = (Type) creditManagers.get(i);

        layout.setTypeCreditsTextView(type.getName() + " 학점 : " + type.getCredits() + "/" + type.getMinCredits());

        final int j = i;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((Type) creditManagers.get(j)).getViewSwitch())
                    for( ; ; ) {
                        if(((j+1) < getCount()) && (creditManagers.get(j).getCode() < creditManagers.get(j+1).getCode())) {
                            removeCreditManager(j+1);
                            notifyDataSetChanged();
                        } else {
                            ((Type) creditManagers.get(j)).setViewSwitch(OFF);
                            break;
                        }
                    }
                else {
                    for(int k = 0, l = j; k < type.getSize(); k++, l++)
                        creditManagers.add(l+1, type.getUnderManager(k));
                    ((Type) creditManagers.get(j)).setViewSwitch(ON);
                    notifyDataSetChanged();
                }
            }
        });

        return layout;
    }

    private View getLectureFieldLayout(int i) {
        LectureFieldLayout layout = new LectureFieldLayout(context);
        final LectureField lectureField = (LectureField) creditManagers.get(i);

        layout.setLectureFieldCreditsTextView("  " + lectureField.getName() + " : " + lectureField.getCredits() + "/" + lectureField.getMinCredits());

        final int j = i;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((LectureField) creditManagers.get(j)).getViewSwitch())
                    for( ; ; ) {
                        if(((j+1) < getCount()) && (creditManagers.get(j).getCode() < creditManagers.get(j+1).getCode())) {
                            removeCreditManager(j+1);
                            notifyDataSetChanged();
                        } else {
                            ((LectureField) creditManagers.get(j)).setViewSwitch(OFF);
                            break;
                        }
                    }
                else {
                    for(int k = 0, l = j; k < lectureField.getSize(); k++, l++)
                        creditManagers.add(l+1, lectureField.getUnderManager(k));
                    ((LectureField) creditManagers.get(j)).setViewSwitch(ON);
                    notifyDataSetChanged();
                }
            }
        });

        return layout;
    }

    private View getLectureGroupLayout(int i) {
        LectureGroupLayout layout = new LectureGroupLayout(context);
        final LectureGroup lectureGroup = (LectureGroup) creditManagers.get(i);

        layout.setLectureGroupCreditsTextView("    " + lectureGroup.getName() + " : " + lectureGroup.getCredits() + "/" + lectureGroup.getMinCredits());

        final int j = i;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((LectureGroup) creditManagers.get(j)).getViewSwitch())
                    for( ; ; ) {
                        if(((j+1) < getCount()) && (creditManagers.get(j).getCode() < creditManagers.get(j+1).getCode())) {
                            removeCreditManager(j+1);
                            notifyDataSetChanged();
                        } else {
                            ((LectureGroup) creditManagers.get(j)).setViewSwitch(OFF);
                            break;
                        }
                    }
                else {
                    for(int k = 0, l = j; k < lectureGroup.getSize(); k++, l++)
                        creditManagers.add(l+1, lectureGroup.getUnderManager(k));
                    ((LectureGroup) creditManagers.get(j)).setViewSwitch(ON);
                    notifyDataSetChanged();
                }
            }
        });

        return layout;
    }

    private View getLectureWorldLayout(int i) {
        LectureWorldLayout layout = new LectureWorldLayout(context);
        final LectureWorld lectureWorld = (LectureWorld) creditManagers.get(i);

        layout.setLectureWorldCreditsTextView("    " + lectureWorld.getName());

        final int j = i;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((LectureWorld) creditManagers.get(j)).getViewSwitch())
                    for( ; ; ) {
                        if(((j+1) < getCount()) && (creditManagers.get(j).getCode() < creditManagers.get(j+1).getCode())) {
                            removeCreditManager(j+1);
                            notifyDataSetChanged();
                        } else {
                            ((LectureWorld) creditManagers.get(j)).setViewSwitch(OFF);
                            break;
                        }
                    }
                else {
                    for (int k = 0, l = j; k < lectureWorld.getSize(); k++, l++)
                        creditManagers.add(l+1, lectureWorld.getUnderManager(k));
                    ((LectureWorld) creditManagers.get(j)).setViewSwitch(ON);
                    notifyDataSetChanged();
                }
            }
        });

        return layout;
    }

    private View getLectureLayout(int i) {
        LectureLayout layout = new LectureLayout(context);
        final Lecture lecture = (Lecture) creditManagers.get(i);

        layout.setLectureCreditTextView("        " + lecture.getName() + "(" + lecture.getCredit() + "학점)");

        final CheckedTextView lectureCreditTextView = layout.getLectureCreditTextView();

        if(lecture.getMultiplier() == 1)    //스크롤 작동 중 체크박스가 화면에서 벗어난 뒤 다시 나타날 때 체크가 해제되는 현상 방지
            lectureCreditTextView.setChecked(true);
        else
            lectureCreditTextView.setChecked(false);

        lectureCreditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lectureCreditTextView.isChecked()) {
                    lectureCreditTextView.setChecked(false);
                    lecture.setMultiplier(0);
                } else {
                    lectureCreditTextView.setChecked(true);
                    lecture.setMultiplier(1);
                }
                notifyDataSetChanged();
            }
        });

        return layout;
    }

    private View getFreeLectureLayout(int i) {
        final FreeLectureLayout layout = new FreeLectureLayout(context);
        final FreeLecture freeLecture = (FreeLecture) creditManagers.get(i);

        final int j = i;
        Button addButton = layout.getAddButton();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AddedLecture addedLecture = new AddedLecture(layout.getFreeName(), layout.getFreeCredit(), freeLecture.getUpperManager());
                    freeLecture.getUpperManager().addUnderManager(freeLecture.getNum(), addedLecture);
                    freeLecture.incNum();
                    layout.setInitiation();
                    creditManagers.add(j, addedLecture);
                    notifyDataSetChanged();
                    ERETMToast(context, "과목이 추가되었습니다.", Toast.LENGTH_LONG);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    ERETMToast(context, "학점은 반드시 숫자로 입력해주세요.", Toast.LENGTH_LONG);
                }
            }
        });

        return layout;
    }

    private View getAddedLectureLayout(int i) {
        AddedLectureLayout layout = new AddedLectureLayout(context);
        final AddedLecture addedLecture = (AddedLecture) creditManagers.get(i);

        layout.setAddedLectureCreditTextView("      " + addedLecture.getName() + "(" + addedLecture.getCredit() + "학점)");

        final CheckedTextView addedLectureCreditTextView = layout.getAddedLectureCreditTextView();

        if(addedLecture.getMultiplier() == 1)   //스크롤 작동 중 체크박스가 화면에서 벗어난 뒤 다시 나타날 때 체크가 해제되는 현상 방지
            addedLectureCreditTextView.setChecked(true);
        else
            addedLectureCreditTextView.setChecked(false);

        addedLectureCreditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addedLectureCreditTextView.isChecked()) {
                    addedLectureCreditTextView.setChecked(false);
                    addedLecture.setMultiplier(0);
                } else {
                    addedLectureCreditTextView.setChecked(true);
                    addedLecture.setMultiplier(1);
                }
                notifyDataSetChanged();
            }
        });

        final int j = i;
        final Button deleteButton = layout.getDeleteButton();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addedLecture.removeThis();
                    removeCreditManager(j);
                    notifyDataSetChanged();
                    ERETMToast(context, "과목이 삭제되었습니다.", Toast.LENGTH_LONG);
                } catch (Exception e) {
                    e.printStackTrace();
                    ERETMToast(context, "과목 삭제 실패", Toast.LENGTH_LONG);
                }
            }
        });

        return layout;
    }


    public void setCreditManager(CreditManager creditManager) {
        creditManagers.add(creditManager);
    }

    public void removeCreditManager(int i) {
        creditManagers.remove(i);
    }
}
