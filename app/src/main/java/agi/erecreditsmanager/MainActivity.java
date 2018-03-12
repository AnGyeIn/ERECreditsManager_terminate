package agi.erecreditsmanager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import agi.erecreditsmanager.ForLecture.ForLecture;
import agi.erecreditsmanager.FreeLecture.FreeLecture;
import agi.erecreditsmanager.Lecture.Lecture;
import agi.erecreditsmanager.LectureField.LectureField;
import agi.erecreditsmanager.LectureGroup.LectureGroup;
import agi.erecreditsmanager.LectureWorld.LectureWorld;
import agi.erecreditsmanager.Type.Type;

import static agi.erecreditsmanager.DataManager.EREOnly;
import static agi.erecreditsmanager.DataManager.EREnOther;
import static agi.erecreditsmanager.DataManager.OthernERE;
import static agi.erecreditsmanager.DataManager.OthernSubERE;
import static agi.erecreditsmanager.DataManager.advEarChe;
import static agi.erecreditsmanager.DataManager.advResGeo;
import static agi.erecreditsmanager.DataManager.bioExp;
import static agi.erecreditsmanager.DataManager.bioExp1;
import static agi.erecreditsmanager.DataManager.bioExp2;
import static agi.erecreditsmanager.DataManager.biology;
import static agi.erecreditsmanager.DataManager.biology1;
import static agi.erecreditsmanager.DataManager.biology2;
import static agi.erecreditsmanager.DataManager.cheExp;
import static agi.erecreditsmanager.DataManager.cheExp1;
import static agi.erecreditsmanager.DataManager.cheExp2;
import static agi.erecreditsmanager.DataManager.chemistry;
import static agi.erecreditsmanager.DataManager.chemistry1;
import static agi.erecreditsmanager.DataManager.chemistry2;
import static agi.erecreditsmanager.DataManager.comInfApp;
import static agi.erecreditsmanager.DataManager.computer;
import static agi.erecreditsmanager.DataManager.creFree;
import static agi.erecreditsmanager.DataManager.creativity;
import static agi.erecreditsmanager.DataManager.culArt;
import static agi.erecreditsmanager.DataManager.culArtFree;
import static agi.erecreditsmanager.DataManager.culture;
import static agi.erecreditsmanager.DataManager.culture_basic;
import static agi.erecreditsmanager.DataManager.culture_engineering;
import static agi.erecreditsmanager.DataManager.culture_world;
import static agi.erecreditsmanager.DataManager.driEng;
import static agi.erecreditsmanager.DataManager.earPhyEng;
import static agi.erecreditsmanager.DataManager.earSysSci;
import static agi.erecreditsmanager.DataManager.earSysSciExp;
import static agi.erecreditsmanager.DataManager.eneEcoEng;
import static agi.erecreditsmanager.DataManager.eneEcoTecAdm;
import static agi.erecreditsmanager.DataManager.eneResDyn;
import static agi.erecreditsmanager.DataManager.eneResFigAna;
import static agi.erecreditsmanager.DataManager.eneResFut;
import static agi.erecreditsmanager.DataManager.engMat1;
import static agi.erecreditsmanager.DataManager.engMat2;
import static agi.erecreditsmanager.DataManager.foreign;
import static agi.erecreditsmanager.DataManager.foreignFree;
import static agi.erecreditsmanager.DataManager.hisPhi;
import static agi.erecreditsmanager.DataManager.hisPhiFree;
import static agi.erecreditsmanager.DataManager.humSoc;
import static agi.erecreditsmanager.DataManager.humSocFree;
import static agi.erecreditsmanager.DataManager.keyCulture;
import static agi.erecreditsmanager.DataManager.korean;
import static agi.erecreditsmanager.DataManager.lenLit;
import static agi.erecreditsmanager.DataManager.lenLitFree;
import static agi.erecreditsmanager.DataManager.litArt;
import static agi.erecreditsmanager.DataManager.litArtFree;
import static agi.erecreditsmanager.DataManager.major;
import static agi.erecreditsmanager.DataManager.majorFree;
import static agi.erecreditsmanager.DataManager.majorOptOrNecFree;
import static agi.erecreditsmanager.DataManager.major_necessary;
import static agi.erecreditsmanager.DataManager.major_optNec;
import static agi.erecreditsmanager.DataManager.major_optOrNec;
import static agi.erecreditsmanager.DataManager.major_optional;
import static agi.erecreditsmanager.DataManager.major_other;
import static agi.erecreditsmanager.DataManager.math1;
import static agi.erecreditsmanager.DataManager.math2;
import static agi.erecreditsmanager.DataManager.newRenEne;
import static agi.erecreditsmanager.DataManager.norFree;
import static agi.erecreditsmanager.DataManager.normal;
import static agi.erecreditsmanager.DataManager.numAnaInf;
import static agi.erecreditsmanager.DataManager.oilGasEngExp;
import static agi.erecreditsmanager.DataManager.optFree;
import static agi.erecreditsmanager.DataManager.othFree;
import static agi.erecreditsmanager.DataManager.phyExp;
import static agi.erecreditsmanager.DataManager.phyExp1;
import static agi.erecreditsmanager.DataManager.phyExp2;
import static agi.erecreditsmanager.DataManager.physics;
import static agi.erecreditsmanager.DataManager.physics1;
import static agi.erecreditsmanager.DataManager.physics2;
import static agi.erecreditsmanager.DataManager.polEco;
import static agi.erecreditsmanager.DataManager.polEcoFree;
import static agi.erecreditsmanager.DataManager.resEngPra;
import static agi.erecreditsmanager.DataManager.resProEng;
import static agi.erecreditsmanager.DataManager.sciEngWri;
import static agi.erecreditsmanager.DataManager.sciThiExp;
import static agi.erecreditsmanager.DataManager.socFree;
import static agi.erecreditsmanager.DataManager.socIde;
import static agi.erecreditsmanager.DataManager.socIdeFree;
import static agi.erecreditsmanager.DataManager.society;
import static agi.erecreditsmanager.DataManager.staExp;
import static agi.erecreditsmanager.DataManager.statistics;
import static agi.erecreditsmanager.DataManager.stoDynExp;
import static agi.erecreditsmanager.DataManager.thiExp;

public class MainActivity extends AppCompatActivity {

    ListView listView, forLecListView;
    TextView totalCreditsTextView, forLecTextView;
    Button changeStudentNumButton, multiMajorButton;
    LinearLayout studentNumLayout, forLecLayout;
    RelativeLayout totalLayout;
    EditText studentNumEditText, forLecSpinnerEditText;

    String sdcardPath, filename, forLectureType, progressingMajor;

    Total total;

    MainAdapter adapter;
    int totalCredits, minTotalCredits;

    int studentNum;

    Spinner forLecSpinner;
    String[] types = {"전공", "교양"};
    ForLecAdapter forLecAdapter;

    ArrayList<ForLecture> forLectures;

    CheckBox lifeRespectCheckBox;
    boolean isLifeChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        totalCreditsTextView = (TextView) findViewById(R.id.totalCreditsTextView);
        changeStudentNumButton = (Button) findViewById(R.id.changeStudentNumButton);
        studentNumLayout = (LinearLayout) findViewById(R.id.studentNumLayout);
        studentNumEditText = (EditText) findViewById(R.id.studentNumEditText);
        totalLayout = (RelativeLayout) findViewById(R.id.totalLayout);
        multiMajorButton = (Button) findViewById(R.id.multiMajorButton);
        forLecLayout = (LinearLayout) findViewById(R.id.forLecLayout);
        forLecTextView = (TextView) findViewById(R.id.forLecTextView);
        forLecListView = (ListView) findViewById(R.id.forLecListView);
        forLecLayout.setVisibility(View.INVISIBLE);
        forLecSpinner = (Spinner) findViewById(R.id.forLecSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        forLecSpinner.setAdapter(adapter);
        forLecSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                forLectureType = types[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        forLecSpinnerEditText = (EditText) findViewById(R.id.forLecSpinnerEditText);
        forLecAdapter = new ForLecAdapter(this);
        forLecListView.setAdapter(forLecAdapter);
        lifeRespectCheckBox = (CheckBox) findViewById(R.id.lifeRespectCheckBox);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        File sdcardFolder = Environment.getExternalStorageDirectory();
        sdcardPath = sdcardFolder.getAbsolutePath();

        filename = sdcardPath + File.separator + "PersonalCredits";

        File file = new File(filename);

        total = new Total(-1);
        forLectures = new ArrayList<ForLecture>();
        progressingMajor = EREOnly;
        isLifeChecked = false;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            total = (Total) objectInputStream.readObject();
            forLectures = (ArrayList<ForLecture>) objectInputStream.readObject();
            progressingMajor = (String) objectInputStream.readObject();
            isLifeChecked = (boolean) objectInputStream.readObject();

            objectInputStream.close();
            Toast.makeText(this, "불러오기 성공", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "불러오기 실패.", Toast.LENGTH_LONG).show();
        }

        if(total.getStudentNum() == -1) {
            studentNumLayout.setVisibility(View.VISIBLE);
            totalLayout.setVisibility(View.INVISIBLE);
        } else {
            studentNumLayout.setVisibility(View.INVISIBLE);
            changeStudentNumButton.setText(total.getStudentNum() + "학번");
            setting();

            if(total.getStudentNum() >= 16) {
                lifeRespectCheckBox.setVisibility(View.VISIBLE);
                lifeRespectCheckBox.setChecked(isLifeChecked);
            } else {
                lifeRespectCheckBox.setVisibility(View.INVISIBLE);
            }
        }

        forLecAdapter.setForLectures(forLectures);
        forLecListView.setAdapter(forLecAdapter);
    }

    public void initialization(int studentNum) {
        if (studentNum <= 13) {     //13학번 이전
            culture.setMinCredits(47);
            culture_basic.setMinCredits(38);
            culture.addUnderManager(culture_basic);

            culture_basic.addUnderManager(thiExp);

            thiExp.addUnderManager(korean);

            foreign.setName("대학영어 또는 고급영어");
            foreign.setMinCredits(2);
            culture_basic.addUnderManager(foreign);
            foreign.addUnderManager(foreignFree);

            culture_basic.addUnderManager(numAnaInf);
            numAnaInf.addUnderManager(math1);
            numAnaInf.addUnderManager(math2);
            numAnaInf.addUnderManager(engMat1);
            numAnaInf.addUnderManager(engMat2);

            sciThiExp.setMinCredits(16);
            culture_basic.addUnderManager(sciThiExp);
            sciThiExp.addUnderManager(physics1);
            sciThiExp.addUnderManager(phyExp1);
            sciThiExp.addUnderManager(physics2);
            sciThiExp.addUnderManager(phyExp2);
            sciThiExp.addUnderManager(physics);
            sciThiExp.addUnderManager(phyExp);
            chemistry1.setName("화학1(화학의 기본1)");
            sciThiExp.addUnderManager(chemistry1);
            sciThiExp.addUnderManager(cheExp1);
            chemistry2.setName("화학2(화학의 기본2)");
            sciThiExp.addUnderManager(chemistry2);
            sciThiExp.addUnderManager(cheExp2);
            sciThiExp.addUnderManager(chemistry);
            sciThiExp.addUnderManager(cheExp);
            sciThiExp.addUnderManager(biology1);
            sciThiExp.addUnderManager(bioExp1);
            sciThiExp.addUnderManager(biology2);
            sciThiExp.addUnderManager(bioExp2);
            sciThiExp.addUnderManager(biology);
            sciThiExp.addUnderManager(bioExp);
            sciThiExp.addUnderManager(statistics);
            sciThiExp.addUnderManager(staExp);
            sciThiExp.addUnderManager(earSysSci);
            sciThiExp.addUnderManager(earSysSciExp);

            culture_basic.addUnderManager(comInfApp);
            comInfApp.addUnderManager(computer);

            culture.addUnderManager(keyCulture);

            keyCulture.addUnderManager(litArt);
            litArt.addUnderManager(litArtFree);

            keyCulture.addUnderManager(hisPhi);
            hisPhi.addUnderManager(hisPhiFree);

            keyCulture.addUnderManager(socIde);
            socIde.addUnderManager(socIdeFree);

            culture.addUnderManager(culture_engineering);

            culture_engineering.addUnderManager(society);
            society.addUnderManager(socFree);

            culture_engineering.addUnderManager(creativity);
            creativity.addUnderManager(creFree);

            major_necessary.setMinCredits(27);
            major.addUnderManager(major_necessary);
            major_necessary.addUnderManager(eneResFut);
            major_necessary.addUnderManager(eneResDyn);
            major_necessary.addUnderManager(advResGeo);
            major_necessary.addUnderManager(eneEcoTecAdm);
            major_necessary.addUnderManager(earPhyEng);
            major_necessary.addUnderManager(stoDynExp);
            major_necessary.addUnderManager(oilGasEngExp);
            major_necessary.addUnderManager(resEngPra);
            major_necessary.addUnderManager(resProEng);
            major_necessary.addUnderManager(eneResFigAna);

            major.addUnderManager(major_optNec);
            major_optNec.addUnderManager(driEng);
            major_optNec.addUnderManager(newRenEne);
            major_optNec.addUnderManager(advEarChe);
            major_optNec.addUnderManager(eneEcoEng);

            major_optional.setMinCredits(23);
            major.addUnderManager(major_optional);
            major_optional.addUnderManager(optFree);

            major.addUnderManager(major_other);
            major_other.addUnderManager(othFree);

            normal.setMinCredits(21);
            normal.addUnderManager(norFree);
        } else if (studentNum <= 15) {  //14, 15학번
            culture.addUnderManager(culture_basic);

            culture_basic.addUnderManager(thiExp);

            thiExp.addUnderManager(sciEngWri);

            culture_basic.addUnderManager(foreign);
            foreign.addUnderManager(foreignFree);

            culture_basic.addUnderManager(numAnaInf);
            numAnaInf.addUnderManager(math1);
            numAnaInf.addUnderManager(math2);
            numAnaInf.addUnderManager(engMat1);
            numAnaInf.addUnderManager(engMat2);

            culture_basic.addUnderManager(sciThiExp);
            sciThiExp.addUnderManager(physics1);
            sciThiExp.addUnderManager(phyExp1);
            sciThiExp.addUnderManager(physics2);
            sciThiExp.addUnderManager(phyExp2);
            sciThiExp.addUnderManager(physics);
            sciThiExp.addUnderManager(phyExp);
            sciThiExp.addUnderManager(chemistry1);
            sciThiExp.addUnderManager(cheExp1);
            sciThiExp.addUnderManager(chemistry2);
            sciThiExp.addUnderManager(cheExp2);
            sciThiExp.addUnderManager(chemistry);
            sciThiExp.addUnderManager(cheExp);
            sciThiExp.addUnderManager(earSysSci);
            sciThiExp.addUnderManager(earSysSciExp);

            culture_basic.addUnderManager(comInfApp);
            comInfApp.addUnderManager(computer);

            culture.addUnderManager(culture_world);

            culture_world.addUnderManager(lenLit);
            lenLit.addUnderManager(lenLitFree);

            culture_world.addUnderManager(culArt);
            culArt.addUnderManager(culArtFree);

            culture_world.addUnderManager(hisPhi);
            hisPhi.addUnderManager(hisPhiFree);

            culture_world.addUnderManager(polEco);
            polEco.addUnderManager(polEcoFree);

            culture_world.addUnderManager(humSoc);
            humSoc.addUnderManager(humSocFree);

            culture.addUnderManager(culture_engineering);

            culture_engineering.addUnderManager(society);
            society.addUnderManager(socFree);

            culture_engineering.addUnderManager(creativity);
            creativity.addUnderManager(creFree);

            major_necessary.setMinCredits(27);
            major.addUnderManager(major_necessary);
            major_necessary.addUnderManager(eneResFut);
            major_necessary.addUnderManager(eneResDyn);
            major_necessary.addUnderManager(advResGeo);
            major_necessary.addUnderManager(eneEcoTecAdm);
            major_necessary.addUnderManager(earPhyEng);
            major_necessary.addUnderManager(stoDynExp);
            major_necessary.addUnderManager(oilGasEngExp);
            major_necessary.addUnderManager(resEngPra);
            major_necessary.addUnderManager(resProEng);
            major_necessary.addUnderManager(eneResFigAna);

            major.addUnderManager(major_optNec);
            major_optNec.addUnderManager(driEng);
            major_optNec.addUnderManager(newRenEne);
            major_optNec.addUnderManager(advEarChe);
            major_optNec.addUnderManager(eneEcoEng);

            major_optional.setMinCredits(23);
            major.addUnderManager(major_optional);
            major_optional.addUnderManager(optFree);

            major.addUnderManager(major_other);
            major_other.addUnderManager(othFree);

            normal.addUnderManager(norFree);
        } else if (studentNum >= 16) {   //16학번 이후
            culture.addUnderManager(culture_basic);

            culture_basic.addUnderManager(thiExp);

            thiExp.addUnderManager(sciEngWri);

            culture_basic.addUnderManager(foreign);
            foreign.addUnderManager(foreignFree);

            culture_basic.addUnderManager(numAnaInf);
            numAnaInf.addUnderManager(math1);
            numAnaInf.addUnderManager(math2);
            numAnaInf.addUnderManager(engMat1);
            numAnaInf.addUnderManager(engMat2);

            culture_basic.addUnderManager(sciThiExp);
            sciThiExp.addUnderManager(physics1);
            sciThiExp.addUnderManager(phyExp1);
            sciThiExp.addUnderManager(physics2);
            sciThiExp.addUnderManager(phyExp2);
            sciThiExp.addUnderManager(physics);
            sciThiExp.addUnderManager(phyExp);
            sciThiExp.addUnderManager(chemistry1);
            sciThiExp.addUnderManager(cheExp1);
            sciThiExp.addUnderManager(chemistry2);
            sciThiExp.addUnderManager(cheExp2);
            sciThiExp.addUnderManager(chemistry);
            sciThiExp.addUnderManager(cheExp);
            sciThiExp.addUnderManager(earSysSci);
            sciThiExp.addUnderManager(earSysSciExp);

            culture_basic.addUnderManager(comInfApp);
            comInfApp.addUnderManager(computer);

            culture.addUnderManager(culture_world);

            culture_world.addUnderManager(lenLit);
            lenLit.addUnderManager(lenLitFree);

            culture_world.addUnderManager(culArt);
            culArt.addUnderManager(culArtFree);

            culture_world.addUnderManager(hisPhi);
            hisPhi.addUnderManager(hisPhiFree);

            culture_world.addUnderManager(polEco);
            polEco.addUnderManager(polEcoFree);

            culture_world.addUnderManager(humSoc);
            humSoc.addUnderManager(humSocFree);

            culture.addUnderManager(culture_engineering);

            culture_engineering.addUnderManager(society);
            society.addUnderManager(socFree);

            culture_engineering.addUnderManager(creativity);
            creativity.addUnderManager(creFree);

            major.addUnderManager(major_necessary);
            major_necessary.addUnderManager(eneResDyn);
            major_necessary.addUnderManager(eneEcoTecAdm);
            major_necessary.addUnderManager(earPhyEng);
            major_necessary.addUnderManager(stoDynExp);
            major_necessary.addUnderManager(oilGasEngExp);
            major_necessary.addUnderManager(resEngPra);
            major_necessary.addUnderManager(resProEng);

            major.addUnderManager(major_optional);
            major_optional.addUnderManager(optFree);

            major.addUnderManager(major_other);
            major_other.addUnderManager(othFree);

            normal.addUnderManager(norFree);
        }
    }

    public void apply() {

        switch(progressingMajor) {
            case EREOnly:
            case EREnOther:
                total.getCulture().sumCredits();
                total.getMajor().sumCredits();
                total.getNormal().sumCredits();
                totalCredits = total.getCulture().getCredits() + total.getMajor().getCredits() + total.getNormal().getCredits();
                break;

            case OthernERE:
            case OthernSubERE:
                total.getMajor().sumCredits();
                totalCredits = total.getMajor().getCredits();
                break;
        }

        totalCreditsTextView.setText("전체 학점 : " + totalCredits + "/" + minTotalCredits);
    }
    public void applyClicked(View view) {
        apply();
    }

    public void save() {
        File file = new File(filename);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(total);
            objectOutputStream.writeObject(forLectures);
            objectOutputStream.writeObject(progressingMajor);
            isLifeChecked = lifeRespectCheckBox.isChecked();
            objectOutputStream.writeObject(isLifeChecked);

            objectOutputStream.flush();
            objectOutputStream.close();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "저장 실패", Toast.LENGTH_LONG).show();
        }
    }
    public void saveClicked(View view) {
        save();
    }

    public void closeClicked(View view) {
        save();
        finish();
    }

    public void studentNumSave() {
        try {
            studentNum = Integer.parseInt(studentNumEditText.getText().toString());
            studentNumLayout.setVisibility(View.INVISIBLE);
            totalLayout.setVisibility(View.VISIBLE);
            changeMajorProcess(progressingMajor);
            total.setStudentNum(studentNum);
            changeStudentNumButton.setText(studentNum + "학번");

            if(total.getStudentNum() >= 16) {
                lifeRespectCheckBox.setVisibility(View.VISIBLE);
            } else {
                lifeRespectCheckBox.setVisibility(View.INVISIBLE);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "학번은 숫자 2자리로 입력해주세요.", Toast.LENGTH_LONG).show();
        }
    }
    public void studentNumSaveClicked(View view) {
        studentNumSave();
    }

    public void setting() {
        adapter = new MainAdapter(this);

        switch(progressingMajor) {
            case EREOnly:
            case EREnOther:
                adapter.setCreditManager(total.getCulture());
                adapter.setCreditManager(total.getMajor());
                adapter.setCreditManager(total.getNormal());
                minTotalCredits = 130;
                break;

            case OthernERE:
                adapter.setCreditManager(total.getMajor());
                minTotalCredits = 39;
                break;

            case OthernSubERE:
                adapter.setCreditManager(total.getMajor());
                minTotalCredits = 21;
                break;
        }

        apply();
        listView.setAdapter(adapter);
    }

    public void changeStudentNum() {
        totalLayout.setVisibility(View.INVISIBLE);
        studentNumLayout.setVisibility(View.VISIBLE);
        clearAll();
    }
    public void changeStudentNumClicked(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("학번 수정 안내");
        builder.setMessage("학번 설정을 수정할 경우 과목들을 체크 및 추가해 놓은 데이터가 사라집니다. 그래도 괜찮으시면 [확인] 버튼을 눌러주세요.");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                changeStudentNum();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void clearAll() {
        //Type
        culture = new Type("교양", 40);
        major = new Type("전공", 62);
        normal = new Type("그 외", 28);

        //LectureField
        culture_basic = new LectureField("학문의 기초", 34);
        keyCulture = new LectureField("핵심교양", 9);
        culture_world = new LectureField("학문의 세계(2개 영역 이상)", 6);
        culture_engineering = new LectureField("공대 사회/창의성", 6);
        major_necessary = new LectureField("전공필수", 19);
        major_optNec = new LectureField("전공선택필수", 9);
        major_optional = new LectureField("전공선택", 40);
        major_other = new LectureField("공대 타학과개론", 3);

        //LectureGroup
        thiExp = new LectureGroup("사고와 표현", 3);
        foreign = new LectureGroup("외국어 2개 교과목\n    (TEPS 900점 이하 영어 1과목 필수)", 4);
        numAnaInf = new LectureGroup("수량적 분석과 추론", 12);
        sciThiExp = new LectureGroup("과학적 사고와 실험", 12);
        comInfApp = new LectureGroup("컴퓨터와 정보 활용", 3);
        society = new LectureGroup("사회성 교과목군 or 인간과 사회 영역", 3);
        creativity = new LectureGroup("창의성 교과목군 or 문화와 예술 영역", 3);

        //LectureWorld
        litArt = new LectureWorld("문학과 예술");
        socIde = new LectureWorld("사회와 이념");
        lenLit = new LectureWorld("언어와 문학");
        culArt = new LectureWorld("문화와 예술");
        hisPhi = new LectureWorld("역사와 철학");
        polEco = new LectureWorld("정치와 경제");
        humSoc = new LectureWorld("인간과 사회");

        //Lecture
        sciEngWri = new Lecture("과학과 기술 글쓰기", 3);
        korean = new Lecture("대학국어", 3);
        math1 = new Lecture("(고급)수학 및 연습1", 3);
        math2 = new Lecture("(고급)수학 및 연습2", 3);
        engMat1 = new Lecture("공학수학1", 3);
        engMat2 = new Lecture("공학수학2", 3);
        physics1 = new Lecture("(고급)물리학1(물리의 기본1)", 3);
        phyExp1 = new Lecture("물리학실험1", 1);
        physics2 = new Lecture("(고급)물리학2(물리의 기본2)", 3);
        phyExp2 = new Lecture("물리학실험2", 1);
        physics = new Lecture("물리학", 3);
        phyExp = new Lecture("물리학실험", 1);
        chemistry1 = new Lecture("화학1", 3);
        cheExp1 = new Lecture("화학실험1", 1);
        chemistry2 = new Lecture("화학2", 3);
        cheExp2 = new Lecture("화학실험2", 1);
        chemistry = new Lecture("화학", 3);
        cheExp = new Lecture("화학실험", 1);
        biology1 = new Lecture("생물학1", 3);
        bioExp1 = new Lecture("생물학실험1", 1);
        biology2 = new Lecture("생물학2", 3);
        bioExp2 = new Lecture("생물학실험2", 1);
        biology = new Lecture("생물학", 3);
        bioExp = new Lecture("생물학실험", 1);
        statistics = new Lecture("통계학", 3);
        staExp = new Lecture("통계학실험", 1);
        earSysSci = new Lecture("지구시스템과학", 3);
        earSysSciExp = new Lecture("지구시스템과학실험", 1);
        computer = new Lecture("컴퓨터의 개념 및 실습", 3);
        eneResFut = new Lecture("에너지자원과미래", 2);
        advResGeo = new Lecture("응용자원지질", 3);
        eneResFigAna = new Lecture("에너지자원수치해석", 3);
        driEng = new Lecture("시추공학", 3);
        newRenEne = new Lecture("신재생에너지", 3);
        advEarChe = new Lecture("응용지구화학", 3);
        eneEcoEng = new Lecture("에너지환경공학", 3);
        eneResDyn = new Lecture("에너지자원역학", 3);
        eneEcoTecAdm = new Lecture("에너지환경기술경영", 3);
        earPhyEng = new Lecture("지구물리공학", 3);
        stoDynExp = new Lecture("암석역학및실험", 3);
        oilGasEngExp = new Lecture("석유가스공학및실험", 3);
        resEngPra = new Lecture("자원공학실습", 1);
        resProEng = new Lecture("자원처리공학", 3);

        //FreeLecture
        foreignFree = new FreeLecture(foreign, 0);
        litArtFree = new FreeLecture(litArt, 0);
        socIdeFree = new FreeLecture(socIde, 0);
        lenLitFree = new FreeLecture(lenLit, 0);
        culArtFree = new FreeLecture(culArt, 0);
        hisPhiFree = new FreeLecture(hisPhi, 0);
        polEcoFree = new FreeLecture(polEco, 0);
        humSocFree = new FreeLecture(humSoc, 0);
        socFree = new FreeLecture(society, 0);
        creFree = new FreeLecture(creativity, 0);
        optFree = new FreeLecture(major_optional, 0);
        othFree = new FreeLecture(major_other, 0);
        norFree = new FreeLecture(normal, 0);
    }

    public void openForLecLayout(View v) {
        totalLayout.setVisibility(View.INVISIBLE);
        forLecLayout.setVisibility(View.VISIBLE);
    }
    public void closeForLecLayout(View v) {
        forLecLayout.setVisibility(View.INVISIBLE);
        totalLayout.setVisibility(View.VISIBLE);
    }

    public void addForLecture(View v) {
        String forLectureName = forLecSpinnerEditText.getText().toString();
        if((forLectureType != null) && (forLectureName != null)) {
            ForLecture forLecture = new ForLecture(forLectureType, forLectureName);
            forLecAdapter.setForLecture(forLecture);
        } else {
            Toast.makeText(this, "과목의 종류와 과목명을 설정해주세요.", Toast.LENGTH_LONG).show();
        }
    }

    public void onMultiMajorButtonClicked(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전공과정 변경 안내");
        builder.setMessage("전공과정 변경 시 과목들을 체크 및 추가해 놓은 데이터가 사라집니다. 변경 후 다시 설정해 주세요.");

        final MultiMajorDialogLayout multiMajorDialogLayout = new MultiMajorDialogLayout(this);
        builder.setView(multiMajorDialogLayout);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedMajor = multiMajorDialogLayout.checkSelectedMajor();
                Toast.makeText(getApplicationContext(), selectedMajor + "을(를) 선택하셨습니다.", Toast.LENGTH_LONG).show();
                multiMajorButton.setText(selectedMajor);
                progressingMajor = selectedMajor;
                changeMajorProcess(selectedMajor);
            }
        });


        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog multiMajorDialog = builder.create();
        multiMajorDialog.show();
    }

    public void changeMajorProcess(String selectedMajor) {
        clearAll();
        initialization(studentNum);

        if(studentNum <= 15) {  //15학번 이전
            switch(selectedMajor) {
                case EREOnly: break;

                case EREnOther:
                    major = new Type("전공", 42);
                    major.addUnderManager(major_necessary);
                    major_optOrNec.addUnderManager(majorOptOrNecFree);
                    major.addUnderManager(major_optOrNec);
                    major.addUnderManager(major_optNec);
                    major.addUnderManager(major_other);
                    if(studentNum <= 13) {  //13학번 이전
                        normal.setMinCredits(41);
                    } else if(studentNum <= 15) {   //14, 15학번
                        normal.setMinCredits(48);
                    }
                    break;

                case OthernERE:
                    total = new Total(studentNum);
                    major = new Type("전공", 39);
                    major.addUnderManager(major_necessary);
                    major_optOrNec.addUnderManager(majorOptOrNecFree);
                    major.addUnderManager(major_optOrNec);
                    major.addUnderManager(major_optNec);
                    culture = null;
                    normal = null;
                    break;

                case OthernSubERE:
                    total = new Total(studentNum);
                    major = new Type("전공", 21);
                    major.addUnderManager(majorFree);
                    culture = null;
                    normal = null;
                    break;

                default:
            }

        } else if (studentNum >= 16) {   //16학번 이후
            switch(selectedMajor) {
                case EREOnly: break;

                case EREnOther:
                    major = new Type("전공", 42);
                    major.addUnderManager(major_necessary);
                    major_optional.setMinCredits(20);
                    major.addUnderManager(major_optional);
                    major.addUnderManager(major_other);
                    normal.setMinCredits(48);
                    break;

                case OthernERE:
                    total = new Total(studentNum);
                    major = new Type("전공", 39);
                    major.addUnderManager(major_necessary);
                    major_optional.setMinCredits(20);
                    major.addUnderManager(major_optional);
                    culture = null;
                    normal = null;
                    break;

                case OthernSubERE:
                    total = new Total(studentNum);
                    major = new Type("전공", 21);
                    major_necessary = new LectureField("전공필수", 15);
                    major_necessary.addUnderManager(eneEcoTecAdm);
                    major_necessary.addUnderManager(earPhyEng);
                    major_necessary.addUnderManager(resProEng);
                    major_necessary.addUnderManager(stoDynExp);
                    major_necessary.addUnderManager(oilGasEngExp);
                    major.addUnderManager(major_necessary);
                    major_optional.setMinCredits(6);
                    major.addUnderManager(major_optional);
                    culture = null;
                    normal = null;
                    break;

                default:
            }
        }

        total.setCulture(culture);
        total.setMajor(major);
        total.setNormal(normal);
        setting();
    }

    public void onAdviceButtonClicked(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("도움말");
        builder.setMessage("본 앱은 에자공 학부생 개인이 만든 것으로 각 항목들은 정확하지 않을 수 있습니다. 마이스누 > 학사정보 > 졸업 > 졸업사정(자가진단)처리 전공내역에서 '이수규정 및 내규조회' 및 졸업시뮬레이션 또는 학과사무실에 문의를 통해 정확한 졸업 요건을 확인하시기 바랍니다.\n학과사무실 : 02-880-7219");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog adviceDialog = builder.create();
        adviceDialog.show();
    }
}
