package com.firstproject.testvisualnovel.data


import android.content.Context
import kotlinx.serialization.json.Json

//read JSON and create object Scene also getSceneOfId

class ParsingStore(private val context: Context) {
    private val story = loadStory()

    private val sceneMap: Map<String, Scene> = story.scenes.associateBy { it.id }

    fun loadStory(): Story {
        val jsonString = context.assets
            .open("story/story.json")
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString(jsonString)
    }

    fun getSceneById(id: String): Scene? {
        return sceneMap[id]
    }
}