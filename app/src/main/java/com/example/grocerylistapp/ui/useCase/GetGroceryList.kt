package com.example.grocerylistapp.ui.useCase

import com.example.grocerylistapp.data.datasource.GroceryListImpl
import javax.inject.Inject

class GetGroceryList @Inject constructor(private val repo: GroceryListImpl) {
    operator fun invoke() = repo.getItem()
}