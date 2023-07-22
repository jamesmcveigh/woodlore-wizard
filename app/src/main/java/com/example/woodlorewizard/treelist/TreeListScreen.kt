package com.example.woodlorewizard.treelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.woodlorewizard.data.TreeDataSource

@Composable
fun TreeListScreen(navController: NavController) {
    val trees = TreeDataSource.trees

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tree List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(trees) { tree ->
                Button(
                    onClick = { navController.navigate("treeDetail/${tree.id}")},
                        modifier = Modifier
                            .fillMaxWidth() // This makes the button fill the available width
                            .padding(top = 8.dp)
                    ) {
                    Text(tree.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("menu") }) {
            Text(text = "Back to Menu")
        }
    }
}
