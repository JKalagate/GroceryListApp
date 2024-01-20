package com.example.grocerylistapp.ui.useCase

import com.example.grocerylistapp.data.datasource.GroceryListImpl
import javax.inject.Inject

class GetByIdItem @Inject constructor(private val repo: GroceryListImpl) {
    suspend operator fun invoke(id: Int) = repo.getByIdItem(id)
}