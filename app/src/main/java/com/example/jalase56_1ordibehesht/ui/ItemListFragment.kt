package com.example.jalase56_1ordibehesht.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory.InventoryApplication
import com.example.jalase56_1ordibehesht.ItemListAdapter
import com.example.jalase56_1ordibehesht.R
import com.example.jalase56_1ordibehesht.databinding.ItemListFragmentBinding

/**
 * Main fragment displaying details for all items in the database.
 */
class ItemListFragment : Fragment(R.layout.item_list_fragment) {

    private lateinit var binding: ItemListFragmentBinding

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).dataBase.itemDao()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 1)
        binding.floatingActionButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }
        val adapter = ItemListAdapter {
            val action =
                ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(itemId = it.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = GridLayoutManager(this.context,2)

        viewModel.allItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

    }
}
