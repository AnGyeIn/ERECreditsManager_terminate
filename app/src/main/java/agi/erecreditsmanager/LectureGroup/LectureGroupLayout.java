package agi.erecreditsmanager.LectureGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import agi.erecreditsmanager.R;

public class LectureGroupLayout extends RelativeLayout {

    Context context;
    TextView lectureGroupCreditsTextView;

    public LectureGroupLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lecturegroup, this, true);

        lectureGroupCreditsTextView = (TextView) findViewById(R.id.lectureGroupCreditsTextView);
    }

    public void setLectureGroupCreditsTextView(String text) {
        lectureGroupCreditsTextView.setText(text);
    }
}
