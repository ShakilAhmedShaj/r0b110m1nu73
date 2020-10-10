package com.shajt3ch.robi.model

/**
 * Created by Shakil Ahmed Shaj on 10,October,2020.
 * shakilahmedshaj@gmail.com
 */

abstract class Item
data class Chapter(
    val title: String,
    var isExpanded: Boolean,
    val items: List<Item>
) : Item()

data class Video(
    val title: String
) : Item()

data class Document(
    val title: String,
    val authorName: String
) : Item()

data class Audio(
    val title: String
) : Item()