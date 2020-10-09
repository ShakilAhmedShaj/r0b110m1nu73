package com.shajt3ch.robi.model

/**
 * Created by Shakil Ahmed Shaj on 08,October,2020.
 * shakilahmedshaj@gmail.com
 */

data class Chapter(
    val name: String,
    var videoList: MutableList<Video>?,
    var audioList: MutableList<Audio>?,
    var documentList: MutableList<Document>?

) {}