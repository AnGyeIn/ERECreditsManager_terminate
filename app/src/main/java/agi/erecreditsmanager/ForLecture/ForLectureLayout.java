package agi.erecreditsmanager.ForLecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import agi.erecreditsmanager.R;

public class ForLectureLayout extends RelativeLayout {

    Context context;
    TextView forLectureTextView;
    Button deleteButton;

    public ForLectureLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.forlecture, this, true);

        forLectureTextView = (TextView) findViewById(R.id.forLectureTextView);
        deleteButton = (Button) findViewById(R.id.deleteButton);
    }

    public void setForLectureTextView(String text) {
        forLectureTextView.setText(text);
    }

    public TextView getForLectureTextView() {
        return forLectureTextView;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
