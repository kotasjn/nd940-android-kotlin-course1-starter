package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        val view = binding.root

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.instruction_title)
        (activity as AppCompatActivity).supportActionBar?.show()

        binding.nextButton.setOnClickListener { onButtonPressed(view) }

        return view
    }

    private fun onButtonPressed(view: View) {

    }
}