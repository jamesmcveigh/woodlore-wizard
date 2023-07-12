package com.example.woodlorewizard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.woodlorewizard.flashcard.FlashcardScreen
import com.example.woodlorewizard.flashcard.FlashcardViewModel
import com.example.woodlorewizard.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_WoodloreWizard)
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val navController = rememberNavController()
                        NavHost(navController, startDestination = "menu") {
                            composable("menu") { Menu(navController) }
                            composable("flashcardScreen") {
                                FlashcardScreen(
                                    FlashcardViewModel(),
                                    navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Menu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace with your actual logo
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("flashcardScreen") }) {
            Text(text = "Flashcards")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle Quiz click */ }) {
            Text(text = "Quiz")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle Progress click */ }) {
            Text(text = "Progress")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "menu") {
                    composable("menu") { Menu(navController) }
                    composable("flashcardScreen") {
                        FlashcardScreen(
                            FlashcardViewModel(),
                            navController
                        )
                    }
                }
            }
        }
    }
}
