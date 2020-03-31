package agi.erecreditsmanager.ForLecture


import agi.erecreditsmanager.MainActivity.Companion.ERETMToast
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.forlecture.view.*

class ForLecAdapter(context : Context) : RecyclerView.Adapter<ForLecAdapter.ForLectureViewHolder>() {

    var forLectures = arrayListOf<ForLecture>()
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForLectureViewHolder {
        return ForLectureViewHolder(ForLectureLayout(context))
    }

    override fun getItemCount(): Int {
        return forLectures.size
    }

    override fun onBindViewHolder(holder: ForLectureViewHolder, position: Int) {
        holder.containerView.run {
            val forLecture = forLectures[position]
            val layout = this as ForLectureLayout
            layout.run {
                forLectureTextView.text = "[${forLecture.type}]${forLecture.name}"
                deleteButton.setOnClickListener {
                    try {
                        forLectures.removeAt(position)
                        notifyDataSetChanged()
                        ERETMToast(context, "외국어진행강좌가 삭제되었습니다.", Toast.LENGTH_LONG)
                    } catch(e : Exception) {
                        e.printStackTrace()
                        ERETMToast(context, "외국어진행강좌 삭제 실패", Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }

    fun setForLecture(forLecture : ForLecture) {
        forLectures.add(forLecture)
        notifyDataSetChanged()
    }

    class ForLectureViewHolder(override val containerView : View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}