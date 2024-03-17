package com.example.grocerylistapp.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.models.GroceryItem
import com.example.grocerylistapp.useCase.GroceryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GroceryListUseCase
) : ViewModel() {

    val listItem = useCase.getGroceryList()

    private val _totalAmount = MutableStateFlow(0.0)
    //Observing and reacting to value changes: _totalAmount
    val totalAmount: StateFlow<Double> get() = _totalAmount


    fun increaseItemAmount(item: GroceryItem) = viewModelScope.launch {
        val updatedItem = item.copy(
            amount = (item.amount.toInt() + 1).toString()
        )
        useCase.updateItem(updatedItem)
    }

    fun decreaseItemAmount(item: GroceryItem) = viewModelScope.launch {
        if (item.amount.toInt() > 0) {
            val updatedItem = item.copy(
                amount = (item.amount.toInt() - 1).toString())
            useCase.updateItem(updatedItem)
        }
    }

    fun deleteNote(item: GroceryItem) = viewModelScope.launch {
        useCase.deleteItem(item)
    }

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

    fun decodeImage(
        context: Context,
        image: ByteArray
    ): ImageBitmap {
        var bitmap: Bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.empty_image
        )

        if (image.isNotEmpty()) {
            bitmap = BitmapFactory.decodeByteArray(
                image,
                0,
                image.size
            )
        }
        return bitmap.asImageBitmap()
    }
}