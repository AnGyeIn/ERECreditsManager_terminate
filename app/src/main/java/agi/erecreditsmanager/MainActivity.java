package agi.erecreditsmanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
import static agi.erecreditsmanager.DataManager.earPhyEng;
import static agi.erecreditsmanager.DataManager.earSysSci;
import static agi.erecreditsmanager.DataManager.earSysSciExp;
import static agi.erecreditsmanager.DataManager.eneEcoTecAdm;
import static agi.erecreditsmanager.DataManager.eneResDyn;
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
import static agi.erecreditsmanager.DataManager.major_necessary;
import static agi.erecreditsmanager.DataManager.major_optional;
import static agi.erecreditsmanager.DataManager.major_other;
import static agi.erecreditsmanager.DataManager.math1;
import static agi.erecreditsmanager.DataManager.math2;
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
import static agi.erecreditsmanager.DataManager.society;
import static agi.erecreditsmanager.DataManager.stoDynExp;
import static agi.erecreditsmanager.DataManager.thiExp;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView totalCreditsTextView;

    String sdcardPath, filename;

    Total total;

    MainAdapter adapter;
    int totalCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        totalCreditsTextView = (TextView) findViewById(R.id.totalCreditsTextView);
        totalCreditsTextView.setText("전체 학점 : " + totalCredits + "/130");

        File sdcardFolder = Environment.getExternalStorageDirectory();
        sdcardPath = sdcardFolder.getAbsolutePath();

        filename = sdcardPath + File.separator + "PersonalCredits";

        File file = new File(filename);

        total = new Total(false);
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

        if(!total.isChecker()) {
            initialization();
            total.setCulture(culture);
            total.setMajor(major);
            total.setNormal(normal);
            total.setChecker(true);
        }

        apply();

        adapter = new MainAdapter(this);

        adapter.setCreditManager(total.getCulture());
        adapter.setCreditManager(total.getMajor());
        adapter.setCreditManager(total.getNormal());

        listView.setAdapter(adapter);
    }

    public void initialization() {
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

    public void applyClicked(View view) {
        apply();
    }

    public void apply() {
        total.getCulture().sumCredits();
        total.getMajor().sumCredits();
        total.getNormal().sumCredits();

        totalCredits = total.getCulture().getCredits() + total.getMajor().getCredits() + total.getNormal().getCredits();
        totalCreditsTextView.setText("전체 학점 : " + totalCredits + "/130");
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
}
