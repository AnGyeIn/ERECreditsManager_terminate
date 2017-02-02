package agi.erecreditsmanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static agi.erecreditsmanager.DataManager.advEarChe;
import static agi.erecreditsmanager.DataManager.advResGeo;
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
import static agi.erecreditsmanager.DataManager.lenLit;
import static agi.erecreditsmanager.DataManager.lenLitFree;
import static agi.erecreditsmanager.DataManager.major;
import static agi.erecreditsmanager.DataManager.major_necessary_from16;
import static agi.erecreditsmanager.DataManager.major_necessary_to15;
import static agi.erecreditsmanager.DataManager.major_optNec;
import static agi.erecreditsmanager.DataManager.major_optional_from16;
import static agi.erecreditsmanager.DataManager.major_optional_to15;
import static agi.erecreditsmanager.DataManager.major_other;
import static agi.erecreditsmanager.DataManager.math1;
import static agi.erecreditsmanager.DataManager.math2;
import static agi.erecreditsmanager.DataManager.newRenEne;
import static agi.erecreditsmanager.DataManager.norFree;
import static agi.erecreditsmanager.DataManager.normal;
import static agi.erecreditsmanager.DataManager.numAnaInf;
import static agi.erecreditsmanager.DataManager.oilGasEngExp;
import static agi.erecreditsmanager.DataManager.optFree_from16;
import static agi.erecreditsmanager.DataManager.optFree_to15;
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
import static agi.erecreditsmanager.DataManager.society;
import static agi.erecreditsmanager.DataManager.stoDynExp;
import static agi.erecreditsmanager.DataManager.thiExp;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView totalCreditsTextView, studentNumTextView;
    LinearLayout studentNumLayout;
    RelativeLayout totalLayout;
    EditText studentNumEditText;

    String sdcardPath, filename;

    Total total;

    MainAdapter adapter;
    int totalCredits;

    int studentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        totalCreditsTextView = (TextView) findViewById(R.id.totalCreditsTextView);
        studentNumTextView = (TextView) findViewById(R.id.studentNumTextView);
        studentNumLayout = (LinearLayout) findViewById(R.id.studentNumLayout);
        studentNumEditText = (EditText) findViewById(R.id.studentNumEditText);
        totalLayout = (RelativeLayout) findViewById(R.id.totalLayout);

        File sdcardFolder = Environment.getExternalStorageDirectory();
        sdcardPath = sdcardFolder.getAbsolutePath();

        filename = sdcardPath + File.separator + "PersonalCredits";

        File file = new File(filename);

        total = new Total(-1);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            total = (Total) objectInputStream.readObject();

            objectInputStream.close();
            Toast.makeText(this, "불러오기 성공", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "불러오기 실패", Toast.LENGTH_LONG).show();
        }

        if(total.getStudentNum() == -1) {
            studentNumLayout.setVisibility(View.VISIBLE);
            totalLayout.setVisibility(View.INVISIBLE);
        } else {
            studentNumLayout.setVisibility(View.INVISIBLE);
            studentNumTextView.setText(total.getStudentNum() + "학번");
            setting();
        }
    }

    public void initialization(int studentNum) {
        if(studentNum <= 15) {  //15학번 이전
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

            major.addUnderManager(major_necessary_to15);
            major_necessary_to15.addUnderManager(eneResFut);
            major_necessary_to15.addUnderManager(eneResDyn);
            major_necessary_to15.addUnderManager(advResGeo);
            major_necessary_to15.addUnderManager(eneEcoTecAdm);
            major_necessary_to15.addUnderManager(earPhyEng);
            major_necessary_to15.addUnderManager(stoDynExp);
            major_necessary_to15.addUnderManager(oilGasEngExp);
            major_necessary_to15.addUnderManager(resEngPra);
            major_necessary_to15.addUnderManager(resProEng);
            major_necessary_to15.addUnderManager(eneResFigAna);

            major.addUnderManager(major_optNec);
            major_optNec.addUnderManager(driEng);
            major_optNec.addUnderManager(newRenEne);
            major_optNec.addUnderManager(advEarChe);
            major_optNec.addUnderManager(eneEcoEng);

            major.addUnderManager(major_optional_to15);
            major_optional_to15.addUnderManager(optFree_to15);

            major.addUnderManager(major_other);
            major_other.addUnderManager(othFree);

            normal.addUnderManager(norFree);
        } else if(studentNum >= 16) {   //16학번 이후
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

            major.addUnderManager(major_necessary_from16);
            major_necessary_from16.addUnderManager(eneResDyn);
            major_necessary_from16.addUnderManager(eneEcoTecAdm);
            major_necessary_from16.addUnderManager(earPhyEng);
            major_necessary_from16.addUnderManager(stoDynExp);
            major_necessary_from16.addUnderManager(oilGasEngExp);
            major_necessary_from16.addUnderManager(resEngPra);
            major_necessary_from16.addUnderManager(resProEng);

            major.addUnderManager(major_optional_from16);
            major_optional_from16.addUnderManager(optFree_from16);

            major.addUnderManager(major_other);
            major_other.addUnderManager(othFree);

            normal.addUnderManager(norFree);
        }
    }

    public void apply() {
        total.getCulture().sumCredits();
        total.getMajor().sumCredits();
        total.getNormal().sumCredits();

        totalCredits = total.getCulture().getCredits() + total.getMajor().getCredits() + total.getNormal().getCredits();
        totalCreditsTextView.setText("전체 학점 : " + totalCredits + "/130");
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
            initialization(studentNum);
            total.setCulture(culture);
            total.setMajor(major);
            total.setNormal(normal);
            total.setStudentNum(studentNum);
            studentNumTextView.setText(studentNum + "학번");
            setting();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "학번은 숫자 2자리로 입력해주세요.", Toast.LENGTH_LONG).show();
        }
    }
    public void studentNumSaveClicked(View view) {
        studentNumSave();
    }

    public void setting() {
        apply();

        adapter = new MainAdapter(this);

        adapter.setCreditManager(total.getCulture());
        adapter.setCreditManager(total.getMajor());
        adapter.setCreditManager(total.getNormal());

        listView.setAdapter(adapter);
    }
}
