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
import com.example.meinenotizen.databinding.FragmentFireBaseLoginBinding
import com.example.meinenotizen.home.HomeViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [FireBaseLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/**
 * LoginFragment enthält das Login UI
 */
class FireBaseLoginFragment : Fragment() {


    private lateinit var binding: FragmentFireBaseLoginBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fire_base_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginSignupButton.setOnClickListener {
            findNavController().navigate(FireBaseLoginFragmentDirections.actionFireBaseLoginFragmentToFireBaseSignUpFragment())
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmailEdit.text.toString()
            val password = binding.loginPasswordEdit.text.toString()

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                viewModel.login(email, password)
            }
        }

        viewModel.currentUser.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        )
    }
}