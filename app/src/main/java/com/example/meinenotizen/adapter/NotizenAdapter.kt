package com.example.meinenotizen.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.meinenotizen.OnDragStartListener
import com.example.meinenotizen.R
import com.example.meinenotizen.data.Notizen

class NotizenAdapter (



    private var dataset: MutableList<Notizen>,
    private val context: Context,
    private var onDragListener: OnDragStartListener?

): RecyclerView.Adapter<NotizenAdapter.NotizenViewHolder>() {


    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ocard: CardView = itemView.findViewById<CardView>(R.id.cardView)
        var clnotizitem: ConstraintLayout = itemView.findViewById(R.id.clnotiz_item)
        var notizname: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        val desc: TextView = itemView.findViewById(R.id.tvDesc)
        val datetime: TextView = itemView.findViewById(R.id.tvDateTime)
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

//        holder.notizname.text = dataset[position].title
//
//        holder.itemView.tvDesc.text = dataset[position].noteText
//        holder.itemView.tvDateTime.text = dataset[position].dateTime
//
//        if (dataset[position].color != null) {
//            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(dataset[position].color))
//        } else {
//            null
//        }
//
//        if (dataset[position].imgPath != null) {
//            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(dataset[position].imgPath))
//            holder.itemView.imgNote.visibility = View.VISIBLE
//        } else {
//            holder.itemView.imgNote.visibility = View.GONE
//        }
//
//        if (dataset[position].webLink != null) {
//            holder.itemView.tvWebLink.text = dataset[position].webLink
//            holder.itemView.tvWebLink.visibility = View.VISIBLE
//        } else {
//            holder.itemView.tvWebLink.visibility = View.GONE
//        }
//
//        holder.itemView.cardView.setOnClickListener {
//            listener!!.onClicked(dataset[position].id!!)
//        }

    }

    override fun getItemCount(): Int {
        try {
            return dataset!!.size
        } catch (ex: Exception) {
            return 0
        }
    }


    fun setData(arrNotesList: LiveData<List<Notizen>>) {
        try {
            dataset = arrNotesList.value as ArrayList<Notizen>
        } catch (ex: Exception) {
//            dataset = null
        }
    }


//    OnClickListener onItemClicked

//    fun setOnClickListener(listener1: OnItemClickListener) {
//        onDragListener = listener1
//    }

    inner class NotizenViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onClicked(notesId: Int)
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }
    }

//    /**
//     * damit der LayoutManager weiß, wie lang die Liste ist
//     */
//    override fun getItemCount(): Int {
//        return dataset.size
//    }

    fun moveNotizyViewItem(from: Int, to: Int) {
        val notizyToMove: Notizen = dataset.get(from)
        dataset.removeAt(from)
        if (to < from) {
            dataset.add(to, notizyToMove)
        } else {
            dataset.add(to - 1, notizyToMove)
        }
    }
}