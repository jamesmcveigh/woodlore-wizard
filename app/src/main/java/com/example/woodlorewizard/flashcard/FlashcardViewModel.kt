package com.example.woodlorewizard.flashcard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woodlorewizard.data.Tree
import com.example.woodlorewizard.data.TreeDataSource
import kotlin.random.Random

class FlashcardViewModel : ViewModel() {
    private val _currentTree = MutableLiveData<Tree>()
    val currentTree: LiveData<Tree> = _currentTree

    private val _isFlipped = MutableLiveData(false)
    val isFlipped: LiveData<Boolean> = _isFlipped

    private var currentTreeIndex = 0

    private val _currentImageResId = MutableLiveData<Int>()
    val currentImageResId: LiveData<Int> = _currentImageResId

    init {
        loadNextTree()
    }

    fun loadNextTree() {
        if (TreeDataSource.trees.isNotEmpty()) {
            var newIndex: Int
            do {
                newIndex = Random.nextInt(TreeDataSource.trees.size)
            } while (newIndex == currentTreeIndex)

            currentTreeIndex = newIndex
            _currentTree.value = TreeDataSource.trees[currentTreeIndex]
            _isFlipped.value = false

            val tree = _currentTree.value!!
            val images = listOf(tree.leafImageResId, tree.barkImageResId, tree.fruitImageResId, tree.flowerImageResId, tree.shapeImageResId)
            _currentImageResId.value = images.random()
            Log.i("selectedImageID", _currentImageResId.value.toString())
        }
    }

    fun flipCard() {
        _isFlipped.value = !(_isFlipped.value ?: false)
    }
}
