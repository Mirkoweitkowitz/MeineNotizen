package com.example.meinenotizen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.meinenotizen.R
import com.example.meinenotizen.data.Notizen

class KategorienAdapter : RecyclerView.Adapter<KategorienAdapter.KategorienViewHolder>(){

    private var arrList  : ArrayList<Notizen>? = null
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategorienViewHolder {
        return KategorienViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notizen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: KategorienViewHolder, position: Int) {

//        holder.itemView.tvTitle.text = arrList[position].title
//
//        holder.itemView.tvDesc.text = arrList[position].noteText
//        holder.itemView.tvDateTime.text = arrList[position].dateTime
//
//        if (arrList[position].color != null) {
//            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(arrList[position].color))
//        } else {
//            null
//        }
//
//        if (arrList[position].imgPath != null) {
//            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(arrList[position].imgPath))
//            holder.itemView.imgNote.visibility = View.VISIBLE
//        } else {
//            holder.itemView.imgNote.visibility = View.GONE
//        }
//
//        if (arrList[position].webLink != null) {
//            holder.itemView.tvWebLink.text = arrList[position].webLink
//            holder.itemView.tvWebLink.visibility = View.VISIBLE
//        } else {
//            holder.itemView.tvWebLink.visibility = View.GONE
//        }
//
//        holder.itemView.cardView.setOnClickListener {
//            listener!!.onClicked(arrList[position].id!!)
//        }


    }
    override fun getItemCount(): Int {
        try {
            return arrList!!.size
        } catch (ex: Exception) {
            return 0
        }
    }


    fun setData(arrNotesList: LiveData<List<Notizen>>) {
        try {
            arrList = arrNotesList.value as ArrayList<Notizen>
        } catch (ex: Exception) {
            arrList = null
        }
    }


    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }


    inner class KategorienViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener {
        fun onClicked(notesId: Int)
    }
}