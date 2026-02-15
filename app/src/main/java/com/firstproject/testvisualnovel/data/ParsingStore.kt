package com.firstproject.testvisualnovel.data


import android.content.Context
import kotlinx.serialization.json.Json

//read JSON and create object Scene also getSceneOfId

class ParsingStore(private val context: Context) {
    val story = loadStory()
    fun loadStory(): Story {
        val jsonString = context.assets
            .open("story/story.json")
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString(jsonString)
    }

    fun getSceneById(id: String): Scene? {
        for (i in story.scenes) {
            if (id == i.id) {
                return i
            }
        }
        return null
    }
}