package com.firstproject.testvisualnovel.data

class SceneLogical (private val parsingStore: ParsingStore ) {
    private var currentSceneId: String = CURRENT_ID

    //check for null after parsing
    private var currentScene: Scene = requireNotNull(parsingStore.getSceneById(currentSceneId)) {
        "Start scene $CURRENT_ID was not found in story.json"
    }

    val backgroundImagePath: String
        get() = currentScene.background

    val currentSceneText: String
        get() = currentScene.text

    val choices: List<Choice>
        get() = currentScene.choices

    fun setNewScene(id: String): Boolean {
        val nextScene = parsingStore.getSceneById(id) ?: return false
        currentScene = nextScene
        currentSceneId = nextScene.id
        return true
    }
    companion object {
        private const val CURRENT_ID = "room_night"
    }
}