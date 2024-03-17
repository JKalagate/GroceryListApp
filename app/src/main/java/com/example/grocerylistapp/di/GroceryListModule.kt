package com.example.grocerylistapp.di

import android.content.Context
import androidx.room.Room
import com.example.grocerylistapp.data.database.GroceryListDatabase
import com.example.grocerylistapp.data.impl.GroceryListImpl
import com.example.grocerylistapp.useCase.DeleteItem
import com.example.grocerylistapp.useCase.GetByIdItem
import com.example.grocerylistapp.useCase.GetGroceryList
import com.example.grocerylistapp.useCase.GroceryListUseCase
import com.example.grocerylistapp.useCase.InsertItem
import com.example.grocerylistapp.useCase.UpdateItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GroceryListModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): GroceryListDatabase =
        Room.databaseBuilder(
            context = context,
            klass = GroceryListDatabase::class.java,
            name = "GroceryDB"
        ).build()

    @Provides
    fun provideDao (dao: GroceryListDatabase) = dao.groceryItemDao()

    @Provides
    fun providesGroceryListUseCase(repo: GroceryListImpl) = GroceryListUseCase(
        insertItem = InsertItem(repo),
        updateItem = UpdateItem(repo),
        deleteItem = DeleteItem(repo),
        getGroceryList = GetGroceryList(repo),
        getByIdItem = GetByIdItem(repo)
    )

}