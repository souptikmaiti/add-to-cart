package com.example.navigationcomponentsgraph1.data

data class Product(var image: Int, var title:String, var description:String, var price:Int, var isSelected:Boolean=false,
                   var quantity:Int = 1) {

}