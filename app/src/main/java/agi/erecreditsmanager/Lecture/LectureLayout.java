package agi.erecreditsmanager.Lecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;

import agi.erecreditsmanager.R;

public class LectureLayout extends RelativeLayout {

    Context context;
    CheckedTextView lectureCreditTextView;

    public LectureLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lecture, this, true);

        lectureCreditTextView = (CheckedTextView) findViewById(R.id.lectureCreditTextView);
    }

    public void setLectureCreditTextView(String text) {
        lectureCreditTextView.setText(text);
    }

    public CheckedTextView getLectureCreditTextView() {
        return lectureCreditTextView;
    }
}
