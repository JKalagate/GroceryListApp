package com.example.grocerylistapp.ui.useCase

import com.example.grocerylistapp.data.datasource.GroceryListImpl
import com.example.grocerylistapp.data.entity.GroceryItem
import com.example.grocerylistapp.ui.repository.Repository
import javax.inject.Inject

class DeleteItem @Inject constructor(private val repo: GroceryListImpl) {
    suspend operator fun invoke (item: GroceryItem) = repo.deleteItem(item)
}