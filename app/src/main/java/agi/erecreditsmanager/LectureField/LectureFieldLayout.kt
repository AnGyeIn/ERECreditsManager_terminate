package agi.erecreditsmanager.LectureField

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.lecturefield.view.*

class LectureFieldLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.lecturefield, this@LectureFieldLayout, true)

            lectureFieldCreditsTextView
        }
    }

    fun setLectureFieldCreditsTextView(text : String) {
        lectureFieldCreditsTextView.text = text
    }
}