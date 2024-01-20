package com.example.grocerylistapp.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val White95 = Color(	0xFFFFFFF6)
val Gray95 = Color(0xFFd2d2d2)
val MediumGray95 = Color(0xA9D2D2D2)
val DarkGray95 = Color(	0xFF919294)

val DarkBlue95 = Color(0xFF01017d)
val LightBlue95 = Color(0xFF122fa7)

val Teal95 = Color(0xFF008282)


val gradientWhiteToDarkGray = Brush.linearGradient(
        0.0f to White95,
        1.0f to DarkGray95,
        start = Offset.Zero,
        end = Offset.Infinite
)

val gradientDarkGrayToWhite = Brush.linearGradient(
        0.09f to DarkGray95,
        20.0f to White95,
        start = Offset.Zero,
        end = Offset.Infinite
)


val gradientDarkBlueToLightBlue = Brush.linearGradient(
        0.0f to DarkBlue95,
        500.0f to LightBlue95,
        start = Offset.Zero,
        end = Offset.Infinite
)
