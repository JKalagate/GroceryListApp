package com.example.grocerylistapp.ui.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.grocerylistapp.data.entity.GroceryItem
import kotlinx.coroutines.flow.Flow


@Dao
interface Repository {

    @Insert
    suspend fun insertItem(item: GroceryItem)

    @Update
    suspend fun updateItem(item: GroceryItem)

    @Delete
    suspend fun deleteItem(item: GroceryItem)

    @Query("SELECT * FROM groceryList")
    fun getItem(): Flow<List<GroceryItem>>

    @Query("SELECT * FROM groceryList where id =:id")
    suspend fun getByIdItem(id: Int): GroceryItem


}