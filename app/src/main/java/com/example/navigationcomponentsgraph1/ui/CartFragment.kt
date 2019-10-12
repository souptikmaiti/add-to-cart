package com.example.navigationcomponentsgraph1.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationcomponentsgraph1.R
import com.example.navigationcomponentsgraph1.adapter.ItemAdapter
import com.example.navigationcomponentsgraph1.data.Product
import com.example.navigationcomponentsgraph1.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment(), ItemAdapter.ItemListener {
    var viewModel: ProductViewModel?=null
    var itemAdapter: ItemAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = "Modify Your Products"
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)
        buildRecyclerView()
        modifyTotal()
    }

    private fun modifyTotal() {
        tv_total_items.text = "Total Items: ${viewModel?.getTotalQuantity()}"
        tv_total_price.text = "Total Price: ${viewModel?.getTotalPrice()}"
    }

    fun buildRecyclerView(){
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(context)
        var items: ArrayList<Product>? = null
        if(viewModel?.getRefreshFlag()!!){
            items = viewModel?.getSelectedProducts()
        } else {
            items = viewModel?.getCheckoutItems()
        }
        itemAdapter = ItemAdapter(context!!,items!!, "CartFragment")
        recycler_view.adapter = itemAdapter
        itemAdapter?.setListener(this)
    }

    override fun onItemClick(pos: Int): Boolean {
        return true
    }

    override fun onCheckboxClick(cb: CheckBox, pos: Int) {
        viewModel?.setShopCartChecked(cb.isChecked, pos)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cart_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_btn -> {
                viewModel?.deleteFromShopList()
                itemAdapter?.notifyDataSetChanged()
                modifyTotal()
            }

            R.id.check_out_btn -> {
                viewModel?.resetRefreshFlag()
                findNavController().navigate(R.id.action_cartFragment_to_checkOutFragment)
            }
        }
        return true
    }

    override fun onIncrementClick(pos: Int) {
        viewModel?.incrementQuantity(pos)
        modifyTotal()
        itemAdapter?.notifyItemChanged(pos)
    }

    override fun onDecrementClick(pos: Int) {
        viewModel?.decrementQuantity(pos)
        modifyTotal()
        itemAdapter?.notifyItemChanged(pos)
    }

}
