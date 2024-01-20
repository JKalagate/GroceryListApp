package com.example.grocerylistapp.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "groceryList")
data class GroceryItem (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name:String,
    val productValue: String,
    val amount: String = "1",
    val picture: ByteArray
)