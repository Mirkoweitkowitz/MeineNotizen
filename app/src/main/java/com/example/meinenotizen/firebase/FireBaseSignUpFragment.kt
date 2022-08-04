package com.example.meinenotizen.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.meinenotizen.R
import com.example.meinenotizen.databinding.FragmentFireBaseSignUpBinding
import com.example.meinenotizen.home.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FireBaseSignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/**
 * SignUpFragment enthält das UI um einen neuen User zu registrieren
 */
class FireBaseSignUpFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var binding: FragmentFireBaseSignUpBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fire_base_sign_up, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupCancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.signupSignupButton.setOnClickListener {
            signUp()
        }

        viewModel.currentUser.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(R.id.fragmentMain)
                }
            }
        )
    }

    private fun signUp() {
        val email = binding.signupMail.text.toString()
        val password = binding.signupPassword.text.toString()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            viewModel.signUp(email, password)
        }
    }


}