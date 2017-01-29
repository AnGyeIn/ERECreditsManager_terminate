package agi.erecreditsmanager.LectureWorld;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import agi.erecreditsmanager.R;

public class LectureWorldLayout extends RelativeLayout {

    Context context;
    TextView lectureWorldCreditsTextView;

    public LectureWorldLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lectureworld, this, true);

        lectureWorldCreditsTextView = (TextView) findViewById(R.id.lectureWorldCreditsTextView);
    }

    public void setLectureWorldCreditsTextView(String text) {
        lectureWorldCreditsTextView.setText(text);
    }
}
