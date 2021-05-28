package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
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
        setHasOptionsMenu(true)

        viewModel.shoeList.observe(viewLifecycleOwner, this::addShoesIntoView)

        binding.floatingButton.setOnClickListener { openDetailFragment(true) }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.popup_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.logout -> {
            val navigateTo = NavigationDirections.actionToLoginFragment()
            findNavController().navigate(navigateTo)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun addShoesIntoView(shoeList: List<Shoe>) {
        for ((index, shoe) in shoeList.withIndex()) {

            val shoeItemBinding = ItemShoeBinding.inflate(
                layoutInflater,
                null,
                false
            )

            shoeItemBinding.shoe = shoe

            shoeItemBinding.root.setOnClickListener {
                viewModel.chooseShoeItem(index)
                openDetailFragment(false)
            }

            binding.listLayout.addView(shoeItemBinding.root)
        }
    }

    private fun openDetailFragment(newShoe: Boolean) {
        if (newShoe) viewModel.newShoeItem()
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        view?.findNavController()?.navigate(action)
    }
}