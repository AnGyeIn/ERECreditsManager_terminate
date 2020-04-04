package agi.erecreditsmanager

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.maildialog.view.*

class MailDialogLayout(context : Context) : LinearLayout(context) {
    init {
        LayoutInflater.from(context).run {
            inflate(R.layout.maildialog, this@MailDialogLayout, true)
            mailEditText
        }
    }
}