package com.example.bottomnav.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(){
    Box(modifier= Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Settings Screen")
    }
}
@Composable
@Preview
fun preS(){
    SettingsScreen()
}