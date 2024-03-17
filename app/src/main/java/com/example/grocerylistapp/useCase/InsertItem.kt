package com.example.grocerylistapp.useCase

import com.example.grocerylistapp.data.impl.GroceryListImpl
import com.example.grocerylistapp.data.models.GroceryItem

class InsertItem constructor(private val repo: GroceryListImpl) {
    suspend operator fun invoke (item: GroceryItem) = repo.insertItem(item)
}