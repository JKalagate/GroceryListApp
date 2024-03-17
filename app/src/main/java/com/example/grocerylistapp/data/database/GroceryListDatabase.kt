package com.example.grocerylistapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.grocerylistapp.data.models.GroceryItem

import com.example.grocerylistapp.data.repository.Repository


@Database(entities = [GroceryItem::class], exportSchema = true, version = 2)
abstract class GroceryListDatabase : RoomDatabase() {
    abstract fun groceryItemDao(): Repository
}
