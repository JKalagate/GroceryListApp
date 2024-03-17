package com.example.grocerylistapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "groceryList")
data class GroceryItem (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name:String = "",
    val productValue: String = "",
    val amount: String = "1",
    val picture: ByteArray = byteArrayOf()
)