package com.example.grocerylistapp.ui.useCase

import com.example.grocerylistapp.data.datasource.GroceryListImpl
import com.example.grocerylistapp.data.entity.GroceryItem

class InsertItem constructor(private val repo: GroceryListImpl) {
    suspend operator fun invoke (item: GroceryItem) = repo.insertItem(item)
}