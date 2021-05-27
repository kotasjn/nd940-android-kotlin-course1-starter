package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.viewModel = viewModel
        binding.saveButton.setOnClickListener {
            if (inputsValid()) {
                viewModel.saveShoeItem()
                findNavController(this).navigateUp()
            }
        }
        return binding.root
    }

    private fun inputsValid() : Boolean {
        var valid = true

        if (binding.shoeNameInput.text.isEmpty()) {
            valid = false
            binding.shoeNameInput.error = "Required field"
        }
        if (binding.shoeCompanyInput.text.isEmpty()) {
            valid = false
            binding.shoeCompanyInput.error = "Required field"
        }
        if (binding.shoeSizeInput.text.isEmpty()) {
            valid = false
            binding.shoeSizeInput.error = "Required field"
        }
        if (binding.shoeDescriptionInput.text.isEmpty()) {
            valid = false
            binding.shoeDescriptionInput.error = "Required field"
        }
        return valid
    }
}