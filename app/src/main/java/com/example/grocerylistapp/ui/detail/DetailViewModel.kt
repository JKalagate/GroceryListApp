package com.example.grocerylistapp.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocerylistapp.data.models.GroceryItem
import com.example.grocerylistapp.useCase.GroceryListUseCase
import com.example.grocerylistapp.ui.utils.ProductValueUIState
import com.example.grocerylistapp.ui.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GroceryListUseCase
) : ViewModel() {

    var name by mutableStateOf("")
    var productValue by mutableStateOf("")
    var picture by mutableStateOf(byteArrayOf())

    var errorStatus by mutableStateOf(ProductValueUIState())

    fun addItem() = viewModelScope.launch {
        useCase.insertItem(
            GroceryItem(
                name = name,
                productValue = productValue,
                picture = picture
            )
        )
        errorStatus = errorStatus.copy(
            nameProductValueNotEmpty = false,
            productValueError = false
        )
    }

      fun validateDataWithRules() {
          viewModelScope.launch {
              val blankResult = Validator.validateEmptyNameValue(
                  name = name,
                  productValue = productValue
              )

              val formatResult = Validator.validateProductValue(
                  productValue = productValue
              )

              errorStatus = errorStatus.copy(
                  productValueError = formatResult.status
              )

              errorStatus = errorStatus.copy(
                  nameProductValueNotEmpty = blankResult.status
              )
          }
      }











}