package com.example.woodlorewizard.flashcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.woodlorewizard.data.TreeDataSource

@Composable
fun FlashcardScreen(viewModel: FlashcardViewModel, navController: NavController) {
    val tree by viewModel.currentTree.observeAsState(TreeDataSource.trees[0])
    val imageResId by viewModel.currentImageResId.observeAsState(tree.leafImageResId)
    val isFlipped by viewModel.isFlipped.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            if (isFlipped) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { viewModel.flipCard() }
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = tree.name,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = tree.description,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            } else {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Image of ${tree.name}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { viewModel.flipCard() }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = viewModel::loadNextTree) {
            Text(text = "Next")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("menu") } ) {
            Text(text = "Back to Menu")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashcardScreenPreview() {
    FlashcardScreen(FlashcardViewModel(), rememberNavController())
}
