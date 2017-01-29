package agi.erecreditsmanager.AddedLecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;

import agi.erecreditsmanager.R;

public class AddedLectureLayout extends RelativeLayout {

    Context context;
    CheckedTextView addedLectureCreditTextView;
    Button deleteButton;

    public AddedLectureLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.addedlecture, this, true);

        addedLectureCreditTextView = (CheckedTextView) findViewById(R.id.addedLectureCreditTextView);
        deleteButton = (Button) findViewById(R.id.deleteButton);
    }

    public void setAddedLectureCreditTextView(String text) {
        addedLectureCreditTextView.setText(text);
    }

    public CheckedTextView getAddedLectureCreditTextView() {
        return addedLectureCreditTextView;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
