package com.example.meinenotizen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meinenotizen.R
import com.example.meinenotizen.adapter.NotizenAdapter
import com.example.meinenotizen.data.Notizen
import com.example.meinenotizen.data.NotizenDataBase
import com.example.meinenotizen.databinding.FragmentHomeBinding
import com.example.meinenotizen.notizen.NeueNotizenFragment
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentHomeBinding


    /** Das ViewModel zu diesem Fragment */
    private val viewModel: HomeViewModel by viewModels()

    var arrNotes: ArrayList<Notizen>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        // damit LiveData automatisch observed wird vom layout
        binding.lifecycleOwner = this.viewLifecycleOwner

        // Inflate the layout for this fragment
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

        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)



        viewModel.notizenList.observe(
            viewLifecycleOwner
        ) {

        }




        // FAB CREATE NOTE FRAGMENT
        binding.fabCreateNoteBtn.setOnClickListener {
            replaceFragment(NeueNotizenFragment.newInstance(), true)
        }

        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {


                if (arrNotes != null) {
                    for (arr in arrNotes!!) {
                        if (arr.title!!.toLowerCase(Locale.getDefault()).contains(p0.toString())) {

                        }
                    }
                }

                notizenAdapter.notifyDataSetChanged()
                return true
            }
        })
        viewModel.resetAllValues()
    }

    private val onClicked = object : NotizenAdapter.OnItemClickListener {
        override fun onClicked(notesId: Int) {

            val fragment: Fragment
            val bundle = Bundle()
            bundle.putInt("noteId", notesId)
            fragment = NeueNotizenFragment.newInstance()
            fragment.arguments = bundle

            replaceFragment(fragment, true)
        }
    }

    fun replaceFragment(fragment: Fragment, istransition: Boolean) {

        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.fragmentMain, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
        fragmentTransition.commit()
    }

//        val dataset = Datasource().loadrvdata()


}