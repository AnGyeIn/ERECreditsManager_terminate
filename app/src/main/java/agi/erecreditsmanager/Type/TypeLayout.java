package agi.erecreditsmanager.Type;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import agi.erecreditsmanager.R;

public class TypeLayout extends RelativeLayout {

    Context context;
    TextView typeCreditsTextView;

    public TypeLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.type, this, true);

        typeCreditsTextView = (TextView) findViewById(R.id.typeCreditsTextView);
    }

    public void setTypeCreditsTextView(String text) {
        typeCreditsTextView.setText(text);
    }
}
