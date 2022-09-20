package com.example.grocerryapp

class GrocerryRepository(private val db:GrocerryDatabase ) {
    suspend fun insert(items: GrocerryItems)=db.getGrocerryDao().insert(items)
    suspend fun delete(items: GrocerryItems)=db.getGrocerryDao().delete(items)
    fun getAllItems()=db.getGrocerryDao().getAllGrocerryItems()
}