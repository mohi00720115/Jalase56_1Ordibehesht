package com.example.jalase56_1ordibehesht.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jalase56_1ordibehesht.R
import com.example.jalase56_1ordibehesht.databinding.ItemListFragmentBinding

/**
 * Main fragment displaying details for all items in the database.
 */
class ItemListFragment : Fragment(R.layout.item_list_fragment) {

    private lateinit var binding: ItemListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.recyclerView.layoutManager = GridLayoutManager(this.context,2)
        binding.floatingActionButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }
    }
}
