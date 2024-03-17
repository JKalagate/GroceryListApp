package com.example.grocerylistapp.useCase

import com.example.grocerylistapp.data.impl.GroceryListImpl
import com.example.grocerylistapp.data.models.GroceryItem
import javax.inject.Inject

class DeleteItem @Inject constructor(private val repo: GroceryListImpl) {
    suspend operator fun invoke (item: GroceryItem) = repo.deleteItem(item)
}