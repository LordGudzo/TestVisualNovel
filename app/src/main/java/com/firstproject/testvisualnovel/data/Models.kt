package com.firstproject.testvisualnovel.data

import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val text: String,
    val nextScene: String
)
@Serializable
data class Scene(
    val id: String,
    val text: String,
    val background: String,
    val choices: List<Choice>
)
@Serializable
data class Story(
    val scenes: List<Scene>
)