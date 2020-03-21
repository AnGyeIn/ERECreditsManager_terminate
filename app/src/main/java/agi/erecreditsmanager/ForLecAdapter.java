package agi.erecreditsmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import agi.erecreditsmanager.ForLecture.ForLecture;
import agi.erecreditsmanager.ForLecture.ForLectureLayout;

public class ForLecAdapter extends BaseAdapter {

    ArrayList<ForLecture> forLectures = new ArrayList<>();
    Context context;

    public ForLecAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return forLectures.size();
    }

    @Override
    public Object getItem(int position) {
        return forLectures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        ForLectureLayout layout = new ForLectureLayout(context);
        ForLecture forLecture = forLectures.get(position);

        layout.setForLectureTextView("[" + forLecture.getType() + "]" + forLecture.getName());

        Button deleteButton = layout.getDeleteButton();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    forLectures.remove(position);
                    notifyDataSetChanged();
                    MainActivity.Companion.ERETMToast(context, "외국어진행강좌가 삭제되었습니다.", Toast.LENGTH_LONG);
                } catch (Exception e) {
                    e.printStackTrace();
                    MainActivity.Companion.ERETMToast(context, "외국어진행강좌 삭제 실패", Toast.LENGTH_LONG);
                }
            }
        });

        return layout;
    }

    public void setForLecture(ForLecture forLecture) {
        forLectures.add(forLecture);
        notifyDataSetChanged();
    }

    public void setForLectures(ArrayList<ForLecture> forLectures) {
        this.forLectures = forLectures;
    }
}
