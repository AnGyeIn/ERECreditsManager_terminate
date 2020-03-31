package agi.erecreditsmanager.Type

import agi.erecreditsmanager.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.type.view.*

class TypeLayout(context : Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.type, this@TypeLayout, true)

            typeCreditsTextView
        }
    }

    fun setTypeCreditsTextView(text : String) {
        typeCreditsTextView.text = text
    }
}