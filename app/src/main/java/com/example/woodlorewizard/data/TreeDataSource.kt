package com.example.woodlorewizard.data

import com.example.woodlorewizard.R

object TreeDataSource {
    val trees = listOf(
        Tree(
            id = 1,
            name = "Oak",
            description = "Oak trees are large and sturdy with broad, spreading crowns.",
            leafImageResId = R.drawable.oak_leaf,
            barkImageResId = R.drawable.oak_bark,
            fruitImageResId = R.drawable.oak_fruit,
            flowerImageResId = R.drawable.oak_flower,
            shapeImageResId = R.drawable.oak_shape
        ),
        Tree(
            id = 2,
            name = "Silver Birch",
            description = "Recognizable by their white bark, these trees are native to the UK and provide habitat for more than 300 species of insects.",
            leafImageResId = R.drawable.birch_leaf,
            barkImageResId = R.drawable.birch_bark,
            fruitImageResId = R.drawable.birch_fruit,
            flowerImageResId = R.drawable.birch_flower,
            shapeImageResId = R.drawable.birch_shape
        ),
        // Add more trees...
        Tree(
            id = 3,
            name = "White Willow",
            description = "Known for their long, slender leaves and their use in making cricket bats.",
            leafImageResId = R.drawable.willow_leaf,
            barkImageResId = R.drawable.willow_bark,
            fruitImageResId = R.drawable.willow_fruit,
            flowerImageResId = R.drawable.willow_flower,
            shapeImageResId = R.drawable.willow_shape
        )
    )
}
