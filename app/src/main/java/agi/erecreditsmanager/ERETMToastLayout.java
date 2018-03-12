package agi.erecreditsmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ERETMToastLayout extends LinearLayout {

    Context context;
    TextView toastTextView;

    public ERETMToastLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.eretm_toastborder, this, true);

        toastTextView = (TextView) findViewById(R.id.toastTextView);
    }

    public void setToastTextView(String text) {
        toastTextView.setText(text);
    }
}
