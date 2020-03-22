package agi.erecreditsmanager

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.multimajordialog.view.*

class MultiMajorDialogLayout(context : Context) : LinearLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.multimajordialog, this@MultiMajorDialogLayout, true)
            multiMajorRadioGroup
        }
    }

    fun checkSelectedMajor() : String {
        val id = multiMajorRadioGroup.checkedRadioButtonId
        val selectedMajorButton = findViewById<Button>(id)
        return selectedMajorButton.text.toString()
    }
}