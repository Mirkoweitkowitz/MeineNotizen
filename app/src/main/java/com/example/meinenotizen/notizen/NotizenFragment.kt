package com.example.meinenotizen.notizen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.meinenotizen.OnDragStartListener
import com.example.meinenotizen.R
import com.example.meinenotizen.adapter.NotizenAdapter
import com.example.meinenotizen.databinding.FragmentNotizenBinding
import com.example.meinenotizen.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext



/**
 * A simple [Fragment] subclass.
 * Use the [NotizenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/**
 * Fragment Notiz
 */
 class NotizenFragment : Fragment(R.layout.fragment_notizen) {


    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentNotizenBinding

    /** Das ViewModel zu diesem Fragment */
    private val viewModel: NotizenViewModel by viewModels()


    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notizen, container, false)
        binding= FragmentNotizenBinding.inflate(inflater)

        return binding.root
    }


    /**
     * Lifecycle Methode nachdem das View erstellt wurde
     *
     * @param view                    Das angezeigte View
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val neueNotiz = binding.neuenotizButton
        val recyclerView = binding.rvNotizen

        binding.neuenotizButton.setOnClickListener {
            findNavController().navigate(NotizenFragmentDirections.actionNotizenFragmentToNeueNotizenFragment())
        }


        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                    ItemTouchHelper.START or ItemTouchHelper.END, 0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val adapter: NotizenAdapter = recyclerView.getAdapter() as NotizenAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.moveNotizenViewItem(from, to)
                adapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            @RequiresApi(api = Build.VERSION_CODES.M)
            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder!!.itemView.setBackgroundColor(requireContext().getColor(R.color.redOrange))
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.setBackgroundColor(requireContext().getColor(R.color.transparent))
            }
        }
        val helper: ItemTouchHelper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerView)


        viewModel.notizenList.observe(
            viewLifecycleOwner
        ) {
            println("here")
            println(it)
            recyclerView.adapter = NotizenAdapter(it.toMutableList(), requireContext(),
                object : OnDragStartListener {
                    override fun onDragStart(holder: NotizenAdapter.ItemViewHolder?) {
                        helper.startDrag(holder!!)
                    }
                }
            )

        }
        viewModel.resetAllValues()
    }
}

