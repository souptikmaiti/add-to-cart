package com.example.navigationcomponentsgraph1.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.navigationcomponentsgraph1.R
import com.example.navigationcomponentsgraph1.data.Product
import com.example.navigationcomponentsgraph1.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    var viewModel: ProductViewModel ?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Product Details"
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)
        var pos: Int ?= arguments?.getInt("position")
        var pr: Product ?= viewModel?.getProducts()?.get(pos!!)

        Glide.with(this).load(pr?.image).into(iv_icon)
        tv_title.text = "Title: " + pr?.title
        tv_description.text = "Description: " + pr?.description
        tv_price.text = "Price: " + pr?.price.toString()
    }

}
