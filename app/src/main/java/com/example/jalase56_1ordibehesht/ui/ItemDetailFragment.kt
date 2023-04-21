package com.example.jalase56_1ordibehesht.ui


import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventory.InventoryApplication
import com.example.jalase56_1ordibehesht.ItemListAdapter
import com.example.jalase56_1ordibehesht.R
import com.example.jalase56_1ordibehesht.data.Item
import com.example.jalase56_1ordibehesht.data.getFormattedPrice
import com.example.jalase56_1ordibehesht.databinding.FragmentItemDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {
    private lateinit var binding: FragmentItemDetailBinding
    private val navigationArgs: ItemDetailFragmentArgs by navArgs()

    private val inventoryViewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).dataBase.itemDao()
        )
    }

    lateinit var item: Item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        val id = navigationArgs.itemId
        inventoryViewModel.getItemWithID(id).observe(this.viewLifecycleOwner) { Item ->
            item = Item
            bind(item)
        }


        val adapter = ItemListAdapter {
            val action =
                ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
    }

    fun bind(item: Item) {
        binding.itemName.text = item.itemName
        binding.itemPrice.text = item.getFormattedPrice()
        binding.itemCount.text = item.quantityInStock.toString()
    }

    /**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    /**
     * Deletes the current item and navigates to the list fragment.
     */
    private fun deleteItem() {
        findNavController().navigateUp()
    }

    /**
     * Called when fragment is destroyed.
     */
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

}
