package agi.erecreditsmanager.LectureGroup

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.lecturegroup.view.*

class LectureGroupLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.lecturegroup, this@LectureGroupLayout, true)

            lectureGroupCreditsTextView
        }
    }

    fun setLectureGroupCreditsTextView(text : String) {
        lectureGroupCreditsTextView.text = text
    }
}