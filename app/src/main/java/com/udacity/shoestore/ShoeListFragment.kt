package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.shoe_list_title)

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        viewModel.shoeList.observe(viewLifecycleOwner, this::addShoesIntoView)

        return binding.root
    }

    private fun addShoesIntoView(shoeList: List<Shoe>) {
        for (shoe in shoeList) {
            val textView = TextView(context, null, 0, R.style.ShoeItemTextView)
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            textView.text = String.format("%s, %s, %.1f", shoe.name, shoe.company, shoe.size)
            binding.listLayout.addView(textView)
        }
    }

}