package com.firstproject.testvisualnovel.data

class SceneLogical (private val parsingStore: ParsingStore ) {

    var currentSceneId = "room_night"
    private var currentScene: Scene = parsingStore.getSceneById(currentSceneId)!!

    var backgroundImagePath: String =  currentScene.background
    var currentSceneText: String= currentScene.text
    var choises: List<Choice> = currentScene.choices

    fun setNewScene (id: String) {
        backgroundImagePath = parsingStore.getSceneById(id)!!.background
        currentSceneText = parsingStore.getSceneById(id)!!.text
        choises = parsingStore.getSceneById(id)!!.choices
    }
}