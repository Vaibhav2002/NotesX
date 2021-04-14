package com.vaibhav.notesappcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vaibhav.notesappcompose.R

val chivoBold = Font(R.font.chivo_bold)
val chivoRegular = Font(R.font.chivo_regular)
val overpass = Font(R.font.overpass_regular)

val Typography = Typography(
    h2=TextStyle(
        fontFamily = FontFamily(chivoRegular),
        fontWeight = FontWeight.Normal,
        fontSize = 65.sp
    ),
    h3=TextStyle(
        fontFamily = FontFamily(chivoBold),
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),

    h4 = TextStyle(
        fontFamily = FontFamily(chivoBold),
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily(chivoRegular),
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(chivoRegular),
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(chivoRegular),
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily(chivoRegular),
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(overpass),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(overpass),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(overpass),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(overpass),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = FontFamily(overpass),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),

    )