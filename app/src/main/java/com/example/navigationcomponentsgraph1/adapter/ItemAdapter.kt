package com.example.navigationcomponentsgraph1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationcomponentsgraph1.R
import com.example.navigationcomponentsgraph1.data.Product

class ItemAdapter(var context: Context, var products:ArrayList<Product>, var calee:String) : RecyclerView.Adapter<ItemAdapter.ItemHolder>(){

    interface ItemListener{
        fun onItemClick(pos: Int): Boolean
        fun onCheckboxClick(cb:CheckBox,pos: Int)
        fun onIncrementClick(pos: Int)
        fun onDecrementClick(pos: Int)
    }
    var itemListener: ItemListener?=null
    fun setListener(itemListener: ItemListener){
        this.itemListener = itemListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.card_item, parent,false)
        return ItemHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var product:Product = products.get(position)
        Glide.with(context).load(product.image).into(holder.ivItem)
        holder.tvTitle.text = product.title
        holder.tvDescription.text = product.description
        holder.tvPrice.text = "Price: " + product.price.toString()
        holder.checkBox.isChecked = product.isSelected
        holder.tvQuantity.text = product.quantity.toString()

        if(calee =="HomeFragment"){
            holder.quantityLayout.visibility = View.GONE
        }

    }

    inner class ItemHolder(v:View): RecyclerView.ViewHolder(v){

        var cardView: CardView = v.findViewById(R.id.card_view)
        var ivItem: ImageView = v.findViewById(R.id.iv_item)
        var tvTitle:TextView = v.findViewById(R.id.tv_title)
        var tvDescription:TextView = v.findViewById(R.id.tv_description)
        var tvPrice:TextView = v.findViewById(R.id.tv_price)
        var checkBox:CheckBox = v.findViewById(R.id.check_box)

        var quantityLayout: LinearLayout = v.findViewById(R.id.quantity_layout)
        var tvQuantity:TextView = v.findViewById(R.id.tv_quantity)
        var btnDecrement:ImageButton = v.findViewById(R.id.btn_decrement)
        var btnIncrement:ImageButton = v.findViewById(R.id.btn_increment)

        init {
            checkBox.setOnClickListener { itemListener?.onCheckboxClick(it as CheckBox, adapterPosition) }

            cardView.setOnLongClickListener { itemListener?.onItemClick( adapterPosition)!!}

            btnDecrement.setOnClickListener { itemListener?.onDecrementClick(adapterPosition) }

            btnIncrement.setOnClickListener { itemListener?.onIncrementClick(adapterPosition) }
        }
    }
}