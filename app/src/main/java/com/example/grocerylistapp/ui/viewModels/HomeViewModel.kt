package com.example.grocerylistapp.ui.viewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocerylistapp.data.entity.GroceryItem
import com.example.grocerylistapp.ui.useCase.GroceryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GroceryListUseCase
) : ViewModel() {

    val listItem = useCase.getGroceryList()

    fun increaseItemAmount(id: Int) {
        viewModelScope.launch {
            val currentItem = listItem.firstOrNull()?.find { it.id == id }

            if (currentItem != null) {

                var increaseAmount = currentItem.amount.toInt()
                increaseAmount += 1

                useCase.updateItem(
                    GroceryItem(
                        id,
                        currentItem.name,
                        currentItem.productValue,
                        increaseAmount.toString(),
                        currentItem.picture
                    )
                )
            }
        }
    }

    fun decreaseItemAmount(id: Int) {
        viewModelScope.launch {
            val currentItem = listItem.firstOrNull()?.find { it.id == id }

            if (currentItem != null) {

                var decreaseAmount = currentItem.amount.toInt()
                if (decreaseAmount > 0) {
                    decreaseAmount -= 1
                }

                useCase.updateItem(
                    GroceryItem(
                        id,
                        currentItem.name,
                        currentItem.productValue,
                        decreaseAmount.toString(),
                        currentItem.picture
                    )
                )
            }
        }
    }



    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double> get() = _totalAmount

    fun updateTotalAmount() {
        viewModelScope.launch {

            useCase.getGroceryList().collect { groceryList ->
                val calculatedTotalAmount = groceryList.map {
                    BigDecimal(it.amount.toDouble() * it.productValue.toDouble())
                        .setScale(2, RoundingMode.HALF_EVEN)
                        .toDouble()
                }.sum()

                _totalAmount.value = BigDecimal(calculatedTotalAmount)
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .toDouble()
            }
        }
    }


    fun deleteNote(item: GroceryItem)=viewModelScope.launch {
        useCase.deleteItem(item)
    }


}