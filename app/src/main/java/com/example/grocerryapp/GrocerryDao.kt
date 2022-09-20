package com.example.grocerryapp
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface GrocerryDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item:GrocerryItems)

    @Delete
    suspend fun delete(item:GrocerryItems)

    @Query(value = "SELECT * FROM grocerry_items")
    fun getAllGrocerryItems():LiveData<List<GrocerryItems>>

}