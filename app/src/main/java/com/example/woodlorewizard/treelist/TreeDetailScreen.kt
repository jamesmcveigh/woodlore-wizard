package com.example.woodlorewizard.treelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.woodlorewizard.data.TreeDataSource
import kotlinx.coroutines.launch

@Composable
fun TreeDetailScreen(treeId: Int, navController: NavController) {
    val tree = TreeDataSource.trees.find { it.id == treeId }

    val coroutineScope = rememberCoroutineScope()

    tree?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    var totalDrag = 0f

                    detectHorizontalDragGestures { change, dragAmount ->
                        totalDrag += dragAmount
                        if (totalDrag > 100) {
                            // User swiped to the right, show previous tree
                            val previousTreeId = if (treeId - 1 < 1) TreeDataSource.trees.size else treeId - 1
                            coroutineScope.launch {
                                navController.navigate("treeDetail/$previousTreeId")
                            }
                            totalDrag = 0f // reset the total drag amount
                        } else if (totalDrag < -100) {
                            // User swiped to the left, show next tree
                            val nextTreeId = if (treeId + 1 > TreeDataSource.trees.size) 1 else treeId + 1
                            coroutineScope.launch {
                                navController.navigate("treeDetail/$nextTreeId")
                            }
                            totalDrag = 0f // reset the total drag amount
                        }
                    }

                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
                )

                Text("Leaf", style = MaterialTheme.typography.headlineSmall)

                Image(
                    painter = painterResource(id = it.leafImageResId),
                    contentDescription = "Leaf of ${it.name}",
                    modifier = Modifier.height(200.dp)
                )

                Text(
                    text = "Bark",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Image(
                    painter = painterResource(id = it.barkImageResId),
                    contentDescription = "Bark of ${it.name}",
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                )

                Text("Fruit", style = MaterialTheme.typography.headlineSmall)

                Image(
                    painter = painterResource(id = it.fruitImageResId),
                    contentDescription = "Fruit of ${it.name}",
                    modifier = Modifier.height(200.dp)
                )

                Text("Flower", style = MaterialTheme.typography.headlineSmall)

                Image(
                    painter = painterResource(id = it.flowerImageResId),
                    contentDescription = "Flower of ${it.name}",
                    modifier = Modifier.height(200.dp)
                )

                Text("Shape", style = MaterialTheme.typography.headlineSmall)

                Image(
                    painter = painterResource(id = it.shapeImageResId),
                    contentDescription = "Shape of ${it.name}",
                    modifier = Modifier.height(200.dp)
                )

                Button(onClick = { navController.navigate("treeList") }) {
                    Text(text = "Back to Tree List")
                }
            }
        }
    }
}

