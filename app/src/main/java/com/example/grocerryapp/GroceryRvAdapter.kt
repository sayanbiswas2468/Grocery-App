package com.example.grocerryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GroceryRvAdapter(var list: List<GrocerryItems>, val grocerryItemClickInterface: MainActivity): RecyclerView.Adapter<GroceryRvAdapter.GrocerryViewHolder>() {
inner class GrocerryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val nameTV=itemView.findViewById<TextView>(R.id.idTVItemName)
    val quantityTV=itemView.findViewById<TextView>(R.id.idTVQuantity)
    val rateTV=itemView.findViewById<TextView>(R.id.idTVRate)
    val amountTV=itemView.findViewById<TextView>(R.id.idTVToatalAmt)
    val deleteIV=itemView.findViewById<ImageView>(R.id.idIVDelete)


}




    interface GrocerryItemClickInterface{
        fun onItemClick(grocerryItems: GrocerryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrocerryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.grocerry_rv_item,parent,false)
        return GrocerryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrocerryViewHolder, position: Int) {
        holder.nameTV.text=list.get(position).itemName
        holder.quantityTV.text=list.get(position).itemQuality.toString()
        holder.rateTV.text="Rs. "+list.get(position).itemPrice.toString()
        val itemTotal:Int =list.get(position).itemPrice*list.get(position).itemQuality
        holder.amountTV.text="Rs. "+itemTotal.toString()
        holder.deleteIV.setOnClickListener{
            grocerryItemClickInterface.onItemClick( list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}