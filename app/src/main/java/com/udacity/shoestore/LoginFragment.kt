package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    val EMAIL_VALIDATION_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val view = binding.root

        binding.loginButton.setOnClickListener { onButtonPressed(view) }
        binding.registerButton.setOnClickListener { onButtonPressed(view) }

        return view
    }

    private fun onButtonPressed(view: View) {
        if (inputsValid()) {
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun inputsValid() : Boolean {
        var valid = true
        return valid

        if (binding.emailInput.text.isEmpty()) {
            valid = false
            binding.emailInput.error = "Required field"
        } else if (!binding.emailInput.text.matches(Regex(EMAIL_VALIDATION_REGEX))) {
            valid = false
            binding.emailInput.error = "Please enter valid email address"
        }

        if (binding.passwordInput.text.isEmpty()) {
            valid = false
            binding.passwordInput.error = "Required field"
        }
        return valid
    }
}