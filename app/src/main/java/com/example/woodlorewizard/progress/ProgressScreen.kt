package com.example.woodlorewizard.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.woodlorewizard.quiz.QuizViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProgressScreen(viewModel: QuizViewModel, navController: NavController) {
    val scores by viewModel.scores
    val showDialog by viewModel.showDialog

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Progress",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        scores.forEach { (score, timestamp) ->
            val date = Date(timestamp)
            val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date)
            Text(text = "Score: $score, Date: $formattedDate")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate("menu") }) {
            Text(text = "Back to Menu")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.showDialog.value = true }) {
            Text(text = "Reset Progress")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.showDialog.value = false },
                title = { Text("Reset Progress") },
                text = { Text("Are you sure you want to reset your progress?") },
                confirmButton = {
                    Button(onClick = {
                        viewModel.resetProgress()
                        viewModel.showDialog.value = false
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(onClick = { viewModel.showDialog.value = false }) {
                        Text("No")
                    }
                }
            )
        }
    }
}
