package agi.erecreditsmanager

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.eretm_toastborder.view.*

class ERETMToastLayout(context : Context) : LinearLayout(context) {
    init {
        LayoutInflater.from(context).apply {
            inflate(R.layout.eretm_toastborder, this@ERETMToastLayout, true)
            toastTextView
        }
    }
}