package com.example.woodlorewizard.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(viewModel: QuizViewModel, navController: NavController) {
    val tree by viewModel.currentTree
    val userAnswer by viewModel.userAnswer
    val lastAnswerResult by viewModel.lastAnswerResult
    val score by viewModel.score
    val questionsAnswered by viewModel.questionsAnswered
    val quizFinished by viewModel.quizFinished

    DisposableEffect(key1 = true) {
        onDispose {
            viewModel.resetQuiz()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (quizFinished) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quiz Finished!",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Your score is $score out of $questionsAnswered",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = viewModel::resetQuiz) {
                Text(text = "Restart Quiz")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("menu") }) {
                Text(text = "Back to Menu")
            }
        } else {
            Image(
                painter = painterResource(id = tree.leafImageResId),
                contentDescription = "Leaf of ${tree.name}",
                modifier = Modifier
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = userAnswer,
                onValueChange = viewModel::updateUserAnswer,
                label = { Text("Enter tree name") },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { viewModel.submitAnswer() })
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = lastAnswerResult)

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = viewModel::submitAnswer) {
                Text(text = "Submit Answer")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("menu") }) {
                Text(text = "Exit Quiz")
            }
        }
    }
}

class MockQuizViewModel : QuizViewModel(ApplicationProvider.getApplicationContext())

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen(MockQuizViewModel(), rememberNavController())
}

