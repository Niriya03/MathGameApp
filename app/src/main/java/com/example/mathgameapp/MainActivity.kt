package com.example.mathgameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathGameApp()
        }
    }
}

@Composable
fun MathGameApp() {
    val navController = rememberNavController()
    var totalQuestions by remember { mutableStateOf(0) } // Total number of questions
    var correctCount by remember { mutableStateOf(0) } // Correct answers count
    var wrongCount by remember { mutableStateOf(0) } // Wrong answers count

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController) { count ->
                totalQuestions = count
                correctCount = 0 // Reset counts when starting a new game
                wrongCount = 0
                navController.navigate("question/0")
            }
        }
        composable("question/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
            QuestionScreen(
                navController = navController,
                index = index,
                totalQuestions = totalQuestions,
                correctCount = correctCount,
                wrongCount = wrongCount,
                onCorrectCountUpdated = { correctCount = it },
                onWrongCountUpdated = { wrongCount = it }
            )
        }
        composable("result/{correctCount}/{wrongCount}") { backStackEntry ->
            val correct = backStackEntry.arguments?.getString("correctCount")?.toInt() ?: 0
            val wrong = backStackEntry.arguments?.getString("wrongCount")?.toInt() ?: 0
            ResultScreen(navController, correct, wrong)
        }
    }
}