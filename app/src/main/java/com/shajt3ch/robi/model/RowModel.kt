package com.shajt3ch.robi.model

import androidx.annotation.IntDef

/**
 * Created by Shakil Ahmed Shaj on 08,October,2020.
 * shakilahmedshaj@gmail.com
 */
class RowModel {

    @RowType
    var type: Int

    lateinit var chapter: Chapter
    lateinit var video: Video
    lateinit var audio: Audio
    lateinit var document: Document

    var isExpanded: Boolean = false

    constructor(@RowType type: Int, chapter: Chapter, isExpanded: Boolean = false) {
        this.type = type
        this.chapter = chapter
        this.isExpanded = isExpanded
    }

    constructor(@RowType type: Int, video: Video, isExpanded: Boolean = false) {
        this.type = type
        this.video = video
        this.isExpanded = isExpanded
    }

    constructor(@RowType type: Int, audio: Audio, isExpanded: Boolean = false) {
        this.type = type
        this.audio = audio
        this.isExpanded = isExpanded
    }

    constructor(@RowType type: Int, document: Document, isExpanded: Boolean = false) {
        this.type = type
        this.document = document
        this.isExpanded = isExpanded
    }


    companion object {

        @IntDef(CHAPTER, VIDEO, AUDIO, DOCUMENT)
        @Retention(AnnotationRetention.SOURCE)
        annotation class RowType

        const val CHAPTER = 1
        const val VIDEO = 2
        const val AUDIO = 3
        const val DOCUMENT = 4
    }


}