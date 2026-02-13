package com.firstproject.testvisualnovel.data

data class Choice(
    val text: String,
    val nextScene: String
)

data class Scene(
    val id: String,
    val text: String,
    val background: String,
    val choices: List<Choice>
)