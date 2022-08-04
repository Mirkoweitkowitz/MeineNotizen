package com.example.meinenotizen.color

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.meinenotizen.R
import com.example.meinenotizen.databinding.FragmentColorBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [ColorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColorFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentColorBinding
    private var selectedColor = "#3e434e"


    companion object {

        var noteId = -1

        fun newInstance(id: Int): ColorFragment {
            val args = Bundle()
            val fragment = ColorFragment()
            fragment.arguments = args
            noteId = id
            return fragment
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_color, null)
        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams

        val behavior = param.behavior

        if (behavior is BottomSheetBehavior<*>) {

            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    var state = ""
                    while (newState == newState) {
                        BottomSheetBehavior.STATE_DRAGGING.apply {
                            state = "DRAGGING"
                        }
                        BottomSheetBehavior.STATE_SETTLING.apply {
                            state = "SETTILING"
                        }
                        BottomSheetBehavior.STATE_EXPANDED.apply {
                            state = "EXPANDED"
                        }
                        BottomSheetBehavior.STATE_COLLAPSED.apply {
                            state = "COLLAPSED"
                        }
                        BottomSheetBehavior.STATE_HIDDEN.apply {
                            state = "HIDDEN"
                            dismiss()
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_color, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (noteId != -1) {
            binding.layoutDeleteNote.visibility = View.VISIBLE
        } else {
            binding.layoutDeleteNote.visibility = View.GONE
        }
        setListener()
    }

    private fun setListener() {

        binding.fNoteBlue.setOnClickListener {

            binding.imgNoteBlue.setImageResource(R.drawable.donecheck)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#2196f3"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Blue")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteCyan.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(R.drawable.donecheck)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#00e5ff"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Cyan")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteGreen.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(R.drawable.donecheck)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#00c853"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Green")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteOrange.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(R.drawable.donecheck)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#ff6d00"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Orange")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNotePurple.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(R.drawable.donecheck)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#aa00ff"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Purple")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteRed.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(R.drawable.donecheck)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#d50000"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Red")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteYellow.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(R.drawable.donecheck)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#ffeb3b"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Yellow")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteBrown.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(R.drawable.donecheck)
            binding.imgNoteIndigo.setImageResource(0)
            selectedColor = "#3e434e"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Brown")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        binding.fNoteIndigo.setOnClickListener {

            binding.imgNoteBlue.setImageResource(0)
            binding.imgNoteCyan.setImageResource(0)
            binding.imgNoteGreen.setImageResource(0)
            binding.imgNoteOrange.setImageResource(0)
            binding.imgNotePurple.setImageResource(0)
            binding.imgNoteRed.setImageResource(0)
            binding.imgNoteYellow.setImageResource(0)
            binding.imgNoteBrown.setImageResource(0)
            binding.imgNoteIndigo.setImageResource(R.drawable.donecheck)
            selectedColor = "#3e434e"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Indigo")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
        // FINISH COLORS

        // ADD IMAGE
        binding.layoutImage.setOnClickListener {

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "Image")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
//            dismiss()
        }

        // ADD URL
        binding.layoutWebUrl.setOnClickListener {

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "WebUrl")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
//            dismiss()
        }

        // Delete Notes
        binding.layoutDeleteNote.setOnClickListener {

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("action", "DeleteNote")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
//            dismiss()
        }
    }
}
