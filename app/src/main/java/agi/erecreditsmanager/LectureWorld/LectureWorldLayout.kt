package agi.erecreditsmanager.LectureWorld

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.lectureworld.view.*

class LectureWorldLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.lectureworld, this@LectureWorldLayout, true)

            lectureWorldCreditsTextView
        }
    }

    fun setLectureWorldCreditsTextView(text : String) {
        lectureWorldCreditsTextView.text = text
    }
}