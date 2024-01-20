package com.example.grocerylistapp.ui.useCase

data class GroceryListUseCase (
    val insertItem: InsertItem,
    val updateItem: UpdateItem,
    val deleteItem: DeleteItem,
    val getByIdItem: GetByIdItem,
    val getGroceryList: GetGroceryList
)