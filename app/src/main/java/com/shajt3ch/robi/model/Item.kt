package com.shajt3ch.robi.model

/**
 * Created by Shakil Ahmed Shaj on 10,October,2020.
 * shakilahmedshaj@gmail.com
 */

abstract class Item
data class Chapter(
    var isExpanded: Boolean,
    val items: List<Item>
) : Item()

data class Video(
    val icon: Int,
    val title: String
) : Item()

data class Document(
    val icon: Int,
    val title: String,
    val authorName: String
) : Item()

data class Audio(
    val icon: Int,
    val title: String
) : Item()