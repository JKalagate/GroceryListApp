package com.example.grocerylistapp.useCase

import com.example.grocerylistapp.data.impl.GroceryListImpl
import javax.inject.Inject

class GetGroceryList @Inject constructor(private val repo: GroceryListImpl) {
    operator fun invoke() = repo.getItem()
}