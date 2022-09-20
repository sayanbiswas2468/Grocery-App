package com.example.grocerryapp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
class GrocerryViewModel (private val repository: GrocerryRepository): ViewModel(){
    fun insert(items: GrocerryItems)=GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items:GrocerryItems)=GlobalScope.launch {
        repository.delete(items)
    }
    fun getAllGrocerryItems()=repository.getAllItems()
}