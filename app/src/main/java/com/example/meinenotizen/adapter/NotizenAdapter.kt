package com.example.meinenotizen.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.DragStartHelper
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.meinenotizen.R
import com.example.meinenotizen.data.Notizen

class NotizenAdapter(


    private var dataset: List<Notizen>,
    private var draglistener: DragStartHelper.OnDragStartListener,
    private var clicklistener: OnItemClickListener?

): RecyclerView.Adapter<NotizenAdapter.NotizenViewHolder>() {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class NotizenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ocard: CardView = itemView.findViewById<CardView>(R.id.cardView)
//        var clnotizitem: ConstraintLayout = itemView.findViewById(R.id.clnotiz_item)
        var inote:ImageView = itemView.findViewById(R.id.imgNote)
        var notizname: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        val desc: TextView = itemView.findViewById(R.id.tvDesc)
        val datetime: TextView = itemView.findViewById(R.id.tvDateTime)
        val web:TextView = itemView.findViewById(R.id.etWebLink)
        val noted: EditText = itemView.findViewById<EditText>(R.id.etNoteDesc)
        val noteT: EditText = itemView.findViewById<EditText>(R.id.etNoteTitle)

    }
    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotizenViewHolder {
        return NotizenViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_notizen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotizenViewHolder, position: Int) {

        holder.notizname.text = dataset[position].title

        holder.desc.text = dataset[position].noteText
        holder.datetime.text = dataset[position].dateTime

        if (dataset[position].color != null) {
            holder.ocard.setCardBackgroundColor(Color.parseColor(dataset[position].color))
        } else {
            null
        }

        if (dataset[position].imgPath != null) {
            holder.inote.setImageBitmap(BitmapFactory.decodeFile(dataset[position].imgPath))
            holder.inote.visibility = View.VISIBLE
        } else {
            holder.inote.visibility = View.GONE
        }

        if (dataset[position].webLink != null) {
            holder.web.text = dataset[position].webLink
            holder.web.visibility = View.VISIBLE
        } else {
            holder.web.visibility = View.GONE
        }

        holder.ocard.setOnClickListener {
//            listener!!.onClicked(dataset[position].id!!)
        }

    }

//    override fun getItemCount(): Int {
//        try {
//            return dataset!!.size
//        } catch (ex: Exception) {
//            return 0
//        }
//    }


    fun setData(arrNotesList: LiveData<List<Notizen>>) {
        try {
            dataset = arrNotesList.value as MutableList<Notizen>
        } catch (ex: Exception) {
            dataset = null !!
        }
    }


    fun setOnClickListener(listener1: OnItemClickListener) {
        clicklistener = listener1
    }

//    inner class NotizenViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onClicked(notesId: Int)
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }
    }

//    /**
//     * damit der LayoutManager weiß, wie lang die Liste ist
//     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    fun moveNotizyViewItem(from: Int, to: Int) {
        val notizenToMove: Notizen = dataset.get(from)
        dataset.removeAt(from)
        if (to < from) {
            dataset.add(to, notizenToMove)
        } else {
            dataset.add(to - 1, notizenToMove)
        }
    }
}