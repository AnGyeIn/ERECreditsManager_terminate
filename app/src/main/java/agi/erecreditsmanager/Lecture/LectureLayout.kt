package agi.erecreditsmanager.Lecture

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.CheckedTextView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.lecture.view.*

class LectureLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.lecture, this@LectureLayout, true)

            lectureCreditTextView
        }
    }

    //MainAdapter 재구현 후 삭제
    fun setLectureCreditTextView(text : String) {
        lectureCreditTextView.text = text
    }

    fun getLectureCreditTextView() : CheckedTextView {
        return lectureCreditTextView
    }
}