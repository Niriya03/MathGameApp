package com.example.mathgameapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun QuestionScreen(
    navController: NavController,
    index: Int,
    totalQuestions: Int,
    correctCount: Int,
    wrongCount: Int,
    onCorrectCountUpdated: (Int) -> Unit,
    onWrongCountUpdated: (Int) -> Unit
) {
    // Generate the question numbers only once per question
    val number1 by remember(index) { mutableStateOf((1..10).random()) }
    val number2 by remember(index) { mutableStateOf((1..10).random()) }
    val correctAnswer = number1 + number2

    var userAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(0.dp, 150.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Correct: $correctCount | Wrong: $wrongCount", fontSize = 30.sp)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("$number1 + $number2 = ?", fontSize = 60.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column {
            Button(onClick = {
                val userAns = userAnswer.toIntOrNull() ?: -1
                if (userAns == correctAnswer) {
                    onCorrectCountUpdated(correctCount + 1) // Update correct count
                } else {
                    onWrongCountUpdated(wrongCount + 1) // Update wrong count
                }

                // Use the updated counts for navigation
                val updatedCorrectCount = if (userAns == correctAnswer) correctCount + 1 else correctCount
                val updatedWrongCount = if (userAns != correctAnswer) wrongCount + 1 else wrongCount

                if (index + 1 < totalQuestions) {
                    navController.navigate("question/${index + 1}")
                } else {
                    // Navigate to the result screen with the final counts
                    navController.navigate("result/$updatedCorrectCount/$updatedWrongCount")
                }
            },
                modifier = Modifier.fillMaxWidth(0.8f)
                    .height(50.dp)) {
                Text("Next", fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Button(onClick = { navController.navigate("start") },
                modifier = Modifier.fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text("Cancel", fontSize = 22.sp)
            }
        }
    }
}