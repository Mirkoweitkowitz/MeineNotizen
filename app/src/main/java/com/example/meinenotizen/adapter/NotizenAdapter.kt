package com.example.meinenotizen.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.meinenotizen.OnDragStartListener
import com.example.meinenotizen.R
import com.example.meinenotizen.data.Notizen
import com.example.meinenotizen.notizen.NotizenFragmentDirections

class NotizenAdapter (



    private var dataset: MutableList<Notizen>,
    private val context: Context,
    private var onDragListener: OnDragStartListener?

): RecyclerView.Adapter<NotizenAdapter.ItemViewHolder>() {


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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notizen, parent, false)

        return ItemViewHolder(itemLayout)


    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        // hole das notizenItem aus dem dataset
        val notizen = dataset[position]

        // baue eine URI aus der Bild URL
        val imgUri = notizen.webLink.toUri().buildUpon().scheme("https").build()

        // lade das Bild mithilfe der URI in die ImageView und runde die Ecken ab

        holder.clnotizitem.setOnClickListener {
            Log.e("AAA", dataset[position].title)
        }

        try {
            //            holder.oImage.setImageResource(dataset[position].imgPath)
            holder.notizname.text = dataset[position].title
            holder.datetime.text = dataset[position].title
            holder.desc.text = dataset[position].title

            holder.ocard.setOnClickListener {
                holder.itemView.findNavController()
                    .navigate(NotizenFragmentDirections.actionNotizenFragmentToNotizDetailFragment())
            }
        } catch (ex: Exception) {
            Log.e("NotizyAdapter", ex.toString())
        }// Lade den Titel aus dem notizyItem in das XML Element


        holder.ocard.setOnTouchListener { view, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                try {
                    onDragListener!!.onDragStart(holder)
                } catch (ex: Exception) {
                    Log.e("LISTENER ERRROR", ex.toString())
                }
            }
            false
        }



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