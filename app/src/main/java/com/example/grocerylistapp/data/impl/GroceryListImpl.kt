package com.example.grocerylistapp.data.impl

import com.example.grocerylistapp.data.models.GroceryItem
import com.example.grocerylistapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroceryListImpl @Inject constructor(private val dao: Repository) : Repository {

    override suspend fun insertItem(item: GroceryItem) = dao.insertItem(item)

    override suspend fun updateItem(item: GroceryItem) = dao.updateItem(item)

    override suspend fun deleteItem(item: GroceryItem) = dao.deleteItem(item)

    override fun getItem(): Flow<List<GroceryItem>> = dao.getItem()

    override suspend fun getByIdItem(id: Int): GroceryItem = dao.getByIdItem(id)

}