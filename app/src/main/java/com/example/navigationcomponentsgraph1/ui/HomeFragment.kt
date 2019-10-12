package com.example.navigationcomponentsgraph1.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationcomponentsgraph1.R
import com.example.navigationcomponentsgraph1.adapter.ItemAdapter
import com.example.navigationcomponentsgraph1.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ItemAdapter.ItemListener {

    var itemAdapter: ItemAdapter?=null
    var viewModel: ProductViewModel?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Select Products for Shopping Cart"
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)

        buildRecyclerView()
    }

    fun buildRecyclerView(){
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(context)
        itemAdapter = ItemAdapter(context!!,viewModel!!.getProducts(),"HomeFragment")
        recycler_view.adapter = itemAdapter
        itemAdapter?.setListener(this)
    }

    override fun onItemClick(pos: Int): Boolean {
        var bundle = bundleOf("position" to pos)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        return true
    }

    override fun onCheckboxClick(cb: CheckBox, pos: Int) {
        viewModel?.setProductChecked(cb.isChecked, pos)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.shop_cart_btn -> {
                viewModel?.setRefreshFlag()
                this.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }
        }
        return true
    }

    override fun onIncrementClick(pos: Int) {
    }

    override fun onDecrementClick(pos: Int) {
    }

}
