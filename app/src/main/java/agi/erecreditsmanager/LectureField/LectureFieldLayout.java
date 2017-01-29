package agi.erecreditsmanager.LectureField;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import agi.erecreditsmanager.R;

public class LectureFieldLayout extends RelativeLayout {

    Context context;
    TextView lectureFieldCreditsTextView;

    public LectureFieldLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lecturefield, this, true);

        lectureFieldCreditsTextView = (TextView) findViewById(R.id.lectureFieldCreditsTextView);
    }

    public void setLectureFieldCreditsTextView(String text) {
        lectureFieldCreditsTextView.setText(text);
    }
}
