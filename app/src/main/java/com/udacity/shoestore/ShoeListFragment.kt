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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel.shoeList.observe(viewLifecycleOwner, this::addShoesIntoView)

        binding.floatingButton.setOnClickListener { openDetailFragment(true) }

        return binding.root
    }

    private fun addShoesIntoView(shoeList: List<Shoe>) {
        for ((index, shoe) in shoeList.withIndex()) {
            val textView = TextView(context, null, 0, R.style.ShoeItemTextView)
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            textView.text = String.format("%s, %s, %.1f", shoe.name, shoe.company, shoe.size)
            textView.setOnClickListener {
                viewModel.chooseShoeItem(index)
                openDetailFragment(false)
            }
            binding.listLayout.addView(textView)
        }
    }

    private fun openDetailFragment(newShoe: Boolean) {
        if (newShoe) viewModel.newShoeItem()
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        view?.findNavController()?.navigate(action)
    }
}