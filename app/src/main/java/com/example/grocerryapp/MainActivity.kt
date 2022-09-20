package com.example.grocerryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list:List<GrocerryItems>
    lateinit var groceryRvAdapter: GroceryRvAdapter
    lateinit var groceryViewModel: GrocerryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsRV=findViewById(R.id.idRVItems)
        addFAB=findViewById(R.id.idFABAdd)
        list=ArrayList<GrocerryItems>()
        groceryRvAdapter= GroceryRvAdapter(list,this)
        itemsRV.layoutManager=LinearLayoutManager(this)
        itemsRV.adapter=groceryRvAdapter
        val grocerryRepository=GrocerryRepository(GrocerryDatabase(this))
        val factory=GroceryViewModalFactory(grocerryRepository)
        groceryViewModel=ViewModelProvider(this,factory).get(GrocerryViewModel::class.java)
        groceryViewModel.getAllGrocerryItems().observe(this, Observer {
            groceryRvAdapter.list=it
            groceryRvAdapter.notifyDataSetChanged()
        })
        addFAB.setOnClickListener{
            openDialog()
        }

    }
    fun openDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.grocerry_add_dialog)
        val cancelBtn =dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn=dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt=dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemPriceEdt=dialog.findViewById<EditText>(R.id.idEditItemPrice)
        val itemQuantityEdt=dialog.findViewById<EditText>(R.id.idEditItemQuantity)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val  itemName: String=itemEdt.text.toString()
            val itemPrice: String=itemPriceEdt.text.toString()
            val itemQuantity: String=itemQuantityEdt.text.toString()
            val qty:Int =itemQuantity.toInt()
            val pr:Int =itemPrice.toInt()
            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items=GrocerryItems(itemName,qty,pr)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Inserted..", Toast.LENGTH_SHORT).show()
                groceryRvAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Please enter all the data..",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()

    }
    fun onItemClick(grocerryItems: GrocerryItems){
        groceryViewModel.delete(grocerryItems)
        groceryRvAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted..",Toast.LENGTH_SHORT).show()
    }


}