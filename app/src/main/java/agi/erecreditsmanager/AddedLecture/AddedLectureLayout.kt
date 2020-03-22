package agi.erecreditsmanager.AddedLecture

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.addedlecture.view.*

class AddedLectureLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.addedlecture, this@AddedLectureLayout, true)

            addedLectureCreditTextView
            deleteButton
        }
    }

    //MainAdapter 재구현 후 삭제
    fun setAddedLectureCreditTextView(text : String) {
        addedLectureCreditTextView.text = text
    }

    fun getAddedLectureCreditTextView() : CheckedTextView {
        return addedLectureCreditTextView
    }

    fun getDeleteButton() : Button {
        return deleteButton
    }
}