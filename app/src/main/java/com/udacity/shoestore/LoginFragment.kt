package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val view = binding.root

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.login_title)

        binding.loginButton.setOnClickListener { onButtonPressed(view) }
        binding.registerButton.setOnClickListener { onButtonPressed(view) }

        return view
    }

    private fun onButtonPressed(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        view.findNavController().navigate(action)
    }
}