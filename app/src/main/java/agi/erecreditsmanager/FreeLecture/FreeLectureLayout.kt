package agi.erecreditsmanager.FreeLecture

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.freelecture.view.*

class FreeLectureLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.freelecture, this@FreeLectureLayout, true)

            nameText
            creditText
            addButton
        }
    }

    //MainAdapter 재구현 후 삭제
    fun getAddButton() : Button {
        return addButton
    }

    fun getFreeName() : String {
        return nameText.text.toString()
    }

    fun getFreeCredit() : Int {
        return Integer.parseInt(creditText.text.toString())
    }

    fun setInitiation() {
        nameText.setHint("      과목명 입력")
        creditText.setHint("학점 입력")
    }
}