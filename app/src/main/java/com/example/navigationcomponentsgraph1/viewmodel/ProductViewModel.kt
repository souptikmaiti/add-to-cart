package com.example.navigationcomponentsgraph1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigationcomponentsgraph1.data.Product
import com.example.navigationcomponentsgraph1.data.ProductData

class ProductViewModel: ViewModel() {
    private var allProducts: ArrayList<Product> = arrayListOf()
    private var shopCartProducts: ArrayList<Product> = arrayListOf()
    private var refreshFlag: Boolean = false
    init{
        for( index:Int in 0..ProductData.productImage.size-1){
            allProducts.add(
                Product(
                    ProductData.productImage.get(index),
                    ProductData.productTitles.get(index),
                    ProductData.productDescriptions.get(index),
                    ProductData.productPrices.get(index)
                )
            )
        }
    }

    fun setRefreshFlag(){
        refreshFlag = true
    }

    fun resetRefreshFlag(){
        refreshFlag = false
    }

    fun getRefreshFlag(): Boolean = refreshFlag

    fun getProducts() = allProducts

    fun setProductChecked(checked:Boolean, pos: Int){
        allProducts.get(pos).isSelected = checked
    }

    fun getSelectedProducts(): ArrayList<Product>{
        shopCartProducts.clear()
        for(a in allProducts){
            if(a.isSelected) shopCartProducts.add(a.copy())
        }
        //shopCartProducts = allProducts.filter {it.isSelected} as ArrayList<Product>
        for(s in shopCartProducts) s.isSelected = false
        return shopCartProducts
    }

    fun getCheckoutItems():ArrayList<Product>{
        return shopCartProducts
    }

    fun setShopCartChecked(checked:Boolean, pos: Int){
        shopCartProducts.get(pos).isSelected = checked
    }

    fun deleteFromShopList(){
        shopCartProducts.removeIf { it.isSelected }
    }

    fun incrementQuantity(pos: Int){
        shopCartProducts.get(pos).quantity +=1
    }

    fun decrementQuantity(pos: Int){
        if(shopCartProducts.get(pos).quantity>1) shopCartProducts.get(pos).quantity -=1
    }

    fun getTotalPrice(): Int{
        var sum: Int = 0
        for (p in shopCartProducts) sum += p.price * p.quantity
        return sum
    }

    fun getTotalQuantity(): Int{
        var items: Int = 0
        for (p in shopCartProducts) items += p.quantity
        return items
    }

}