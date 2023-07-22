package com.example.woodlorewizard.quiz

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.woodlorewizard.data.Tree
import com.example.woodlorewizard.data.TreeDataSource
import java.util.Locale
import kotlin.random.Random

open class QuizViewModel(private val context: Context) : ViewModel() {
    private var currentTreeIndex = getRandomTreeIndex()
    private val _currentTree = mutableStateOf(TreeDataSource.trees[currentTreeIndex])
    val currentTree: State<Tree> = _currentTree

    private val _userAnswer = mutableStateOf("")
    val userAnswer: State<String> = _userAnswer

    private val _lastAnswerResult = mutableStateOf("")
    val lastAnswerResult: State<String> = _lastAnswerResult

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _questionsAnswered = mutableStateOf(0)
    val questionsAnswered: State<Int> = _questionsAnswered

    private val _quizFinished = mutableStateOf(false)
    val quizFinished: State<Boolean> = _quizFinished

    private val sharedPreferences = context.getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)

    private val _scores = mutableStateOf(getScoresFromSharedPreferences())
    val scores: State<List<Pair<Int, Long>>> = _scores

    val showDialog = mutableStateOf(false)

    private fun getScoresFromSharedPreferences(): List<Pair<Int, Long>> {
        val scoresString = sharedPreferences.getString("scores", "") ?: ""
        return if (scoresString.isEmpty()) {
            listOf()
        } else {
            scoresString.split(";").map {
                val parts = it.split(",")
                Pair(parts[0].toInt(), parts[1].toLong())
            }
        }
    }

    private fun saveScoresToSharedPreferences(scores: List<Pair<Int, Long>>) {
        val scoresString = scores.joinToString(";") { "${it.first},${it.second}" }
        sharedPreferences.edit().putString("scores", scoresString).apply()
    }

    private fun getRandomTreeIndex(): Int {
        var newIndex: Int
        do {
            newIndex = Random.nextInt(TreeDataSource.trees.size)
        } while (newIndex == currentTreeIndex)
        return newIndex
    }

    fun submitAnswer() {
        _questionsAnswered.value++
        if (_userAnswer.value.lowercase(Locale.getDefault()) == _currentTree.value.name.lowercase(
                Locale.getDefault()
            )
        ) {
            _score.value++
            _lastAnswerResult.value = "Correct!"
        } else {
            _lastAnswerResult.value = "Incorrect. The correct answer is ${_currentTree.value.name}."
        }
        loadNextTree()
    }

    private fun loadNextTree() {
        if (_questionsAnswered.value < 15) {
            currentTreeIndex = getRandomTreeIndex()
            _currentTree.value = TreeDataSource.trees[currentTreeIndex]
            _userAnswer.value = ""
        } else {
            val newScores = _scores.value + Pair(_score.value, System.currentTimeMillis())
            _scores.value = newScores
            saveScoresToSharedPreferences(newScores)
            _quizFinished.value = true
        }
    }

    fun updateUserAnswer(answer: String) {
        _userAnswer.value = answer
    }

    fun resetQuiz() {
        _score.value = 0
        _questionsAnswered.value = 0
        _lastAnswerResult.value = ""
        _quizFinished.value = false
        loadNextTree()
    }

    fun resetProgress() {
        _scores.value = emptyList()
        saveScoresToSharedPreferences(emptyList())
    }
}

class QuizViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}