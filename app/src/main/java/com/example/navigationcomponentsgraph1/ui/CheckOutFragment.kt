package com.example.navigationcomponentsgraph1.ui


import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.navigationcomponentsgraph1.R
import com.example.navigationcomponentsgraph1.data.Product
import com.example.navigationcomponentsgraph1.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_check_out.*

/**
 * A simple [Fragment] subclass.
 */
class CheckOutFragment : Fragment() {

    var viewModel: ProductViewModel ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.title = "Check Out"
        return inflater.inflate(R.layout.fragment_check_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)

        tv_total_items.text = "Total Items: " + viewModel?.getTotalQuantity().toString()
        tv_total_price.text = "Total Price: " + viewModel?.getTotalPrice().toString()

        var listOfProducts:ArrayList<Product>? = viewModel?.getCheckoutItems()

        if(listOfProducts!=null){
            for(p in listOfProducts!!){
                var row: TableRow = TableRow(activity)
                var tv_item: TextView = TextView(activity)
                var tv_quantity: TextView = TextView(activity)
                var tv_price: TextView = TextView(activity)
                var tv_total: TextView = TextView(activity)

                tv_item.text = p.title
                tv_quantity.text = p.quantity.toString()
                tv_price.text = p.price.toString()
                tv_total.text = (p.quantity*p.price).toString()

                row.addView(tv_item, TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))
                row.addView(tv_quantity, TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))
                row.addView(tv_price, TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))
                row.addView(tv_total, TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))

                table_items.addView(row, TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            }
        }

    }


}
