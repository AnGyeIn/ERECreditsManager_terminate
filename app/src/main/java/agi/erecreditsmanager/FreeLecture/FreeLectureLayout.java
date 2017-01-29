package agi.erecreditsmanager.FreeLecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import agi.erecreditsmanager.R;

public class FreeLectureLayout extends RelativeLayout {

    Context context;
    EditText nameText, creditText;
    Button addButton;

    public FreeLectureLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.freelecture, this, true);

        nameText = (EditText) findViewById(R.id.nameText);
        creditText = (EditText) findViewById(R.id.creditText);
        addButton = (Button) findViewById(R.id.addButton);
    }

    public Button getAddButton() {
        return addButton;
    }

    public String getFreeName() {
        return nameText.getText().toString();
    }

    public int getFreeCredit() {
        return Integer.parseInt(creditText.getText().toString());
    }

    public void setInitiation() {
        nameText.setHint("      과목명 입력");
        creditText.setHint("학점 입력");
    }
}
