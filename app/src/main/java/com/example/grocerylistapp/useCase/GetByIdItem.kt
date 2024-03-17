package com.example.grocerylistapp.useCase

import com.example.grocerylistapp.data.impl.GroceryListImpl
import javax.inject.Inject

class GetByIdItem @Inject constructor(private val repo: GroceryListImpl) {
    suspend operator fun invoke(id: Int) = repo.getByIdItem(id)
}