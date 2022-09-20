package com.example.grocerryapp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocerry_items")
data class GrocerryItems
    (@ColumnInfo(name = "itemName")
    var itemName: String,
     @ColumnInfo(name = "itemQuantity")
     var itemQuality:Int,
    @ColumnInfo(name="itemPrice")
    var itemPrice:Int
){
        @PrimaryKey(autoGenerate = true)
        var id:Int? =null
}