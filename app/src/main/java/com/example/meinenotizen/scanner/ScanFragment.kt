package com.example.meinenotizen.scanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.meinenotizen.MainViewModel
import com.example.meinenotizen.R
import com.example.meinenotizen.databinding.FragmentScanBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScanFragment : Fragment() {

    private lateinit var binding: FragmentScanBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentScanBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentImageBitmap.observe(
            viewLifecycleOwner,
            Observer {
                binding.imgPreview.setImageBitmap(it)
            }
        )
    }
}