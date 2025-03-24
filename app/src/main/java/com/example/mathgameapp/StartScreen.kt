package com.example.mathgameapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StartScreen(navController: NavController, onStartGame: (Int) -> Unit) {
    var questionCount by remember { mutableStateOf("") }

    Column {  }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(0.dp, 150.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text("Math Game", fontSize = 50.sp, fontWeight = FontWeight.SemiBold)
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("How many questions?", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = questionCount,
            onValueChange = { questionCount = it },
            label = { Text("Enter number of questions") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.8f)
                .height(60.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val count = questionCount.toIntOrNull() ?: 0
                if (count > 0) {
                    onStartGame(count)
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
                .height(50.dp)
        ) {
            Text("Start", fontSize = 22.sp)
        }
    }
}