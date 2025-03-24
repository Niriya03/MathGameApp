package com.example.mathgameapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResultScreen(navController: NavController, correctCount: Int, wrongCount: Int) {

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(0.dp, 150.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Game Over", fontSize = 40.sp, fontWeight = FontWeight.SemiBold)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("Correct: $correctCount", fontSize = 35.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Wrong: $wrongCount", fontSize = 35.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate("start") },
            modifier = Modifier.fillMaxWidth(0.8f)
                .height(50.dp)) {
            Text("Play Again", fontSize = 22.sp)
        }
    }
}