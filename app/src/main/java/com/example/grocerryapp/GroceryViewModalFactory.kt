package com.example.grocerryapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class GroceryViewModalFactory  (private val repository: GrocerryRepository  ): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GrocerryViewModel(repository) as T
    }
}