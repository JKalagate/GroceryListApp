package com.example.grocerylistapp.data.datasource

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.grocerylistapp.data.entity.GroceryItem

import com.example.grocerylistapp.ui.repository.Repository


@Database(entities = [GroceryItem::class], exportSchema = true, version = 2)
abstract class GroceryListDatabase : RoomDatabase() {
    abstract fun groceryItemDao(): Repository
}
