package com.shajt3ch.robi.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.robi.view.DocumentActivity
import com.shajt3ch.robi.R
import com.shajt3ch.robi.view.VideoActivity
import com.shajt3ch.robi.model.RowModel


/**
 * Created by Shakil Ahmed Shaj on 08,October,2020.
 * shakilahmedshaj@gmail.com
 */

class BaseAdapter(
    private val context: Context,
    private var rowModels: MutableList<RowModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var actionLock = false

    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvChapterTitle: TextView = itemView.findViewById(R.id.tv_chapter_title) as TextView
        var btnToggle: ImageButton = itemView.findViewById(R.id.btn_toggle) as ImageButton
    }

    class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSingleTitle: TextView = itemView.findViewById(R.id.tv_single_title) as TextView
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSingleTitle: TextView = itemView.findViewById(R.id.tv_single_title) as TextView
    }

    class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSingleTitle: TextView = itemView.findViewById(R.id.tv_single_title) as TextView
    }

    class DocumentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSingleTitle: TextView = itemView.findViewById(R.id.tv_single_title) as TextView
    }


    override fun getItemViewType(position: Int): Int {
        return rowModels[position].type
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RowModel.CHAPTER -> ChapterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.chapter_row, parent, false)
            )
            RowModel.VIDEO -> VideoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.single_item_row, parent, false)
            )
            RowModel.AUDIO -> AudioViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.single_item_row, parent, false)
            )
            RowModel.DOCUMENT -> DocumentViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.single_item_row, parent, false)
            )
            else -> SingleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.single_item_row, parent, false)
            )
        }
    }


    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        val row = rowModels[position]

        when (row.type) {

            RowModel.CHAPTER -> {
                (p0 as ChapterViewHolder).tvChapterTitle.text = row.chapter.name

                if (row.chapter.videoList == null || row.chapter.videoList!!.size == 0) {
                    p0.btnToggle.visibility = View.GONE
                } else {
                    if (p0.btnToggle.visibility == View.GONE) {
                        p0.btnToggle.visibility = View.VISIBLE
                    }

                    if (row.isExpanded) {
                        p0.btnToggle.background =
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_minus
                            )
                    } else {
                        p0.btnToggle.background =
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_plus
                            )
                    }

                    p0.btnToggle.setOnClickListener {
                        if (!actionLock) {
                            actionLock = true
                            if (row.isExpanded) {
                                row.isExpanded = false
                                collapse(position)
                            } else {
                                row.isExpanded = true
                                expand(position)
                            }
                        }
                    }
                }
            }

            RowModel.VIDEO -> {
                (p0 as VideoViewHolder).tvSingleTitle.text = row.video.name

                val vidTitle = row.video.name
                val intent = Intent(context, VideoActivity::class.java)
                intent.putExtra("videoTitle", vidTitle)

                p0.itemView.setOnClickListener {
                    context.startActivity(intent)
                }
            }
            RowModel.AUDIO -> {
                (p0 as AudioViewHolder).tvSingleTitle.text = row.audio.name

                p0.itemView.setOnClickListener {
                    Toast.makeText(context, "Audio : " + row.audio.name, Toast.LENGTH_SHORT).show()
                }
            }
            RowModel.DOCUMENT -> {
                (p0 as DocumentViewHolder).tvSingleTitle.text = row.document.name

                val documentTitle = row.document.name
                val intent = Intent(context, DocumentActivity::class.java)
                intent.putExtra("documentTitle", documentTitle)

                p0.itemView.setOnClickListener {
                    context.startActivity(intent)
                }
            }

        }
    }

    private fun expand(position: Int) {

        var nextPosition = position

        val row = rowModels[position]

        when (row.type) {

            RowModel.CHAPTER -> {

                /**
                 * add element just below of clicked row
                 */

                if (row.chapter.videoList != null) {
                    for (video in row.chapter.videoList!!) {
                        rowModels.add(++nextPosition, RowModel(RowModel.VIDEO, video))
                    }
                }


                if (row.chapter.audioList != null) {
                    for (audio in row.chapter.audioList!!) {
                        rowModels.add(++nextPosition, RowModel(RowModel.AUDIO, audio))
                    }
                }


                if (row.chapter.documentList != null) {
                    for (document in row.chapter.documentList!!) {
                        rowModels.add(++nextPosition, RowModel(RowModel.DOCUMENT, document))
                    }
                }

                notifyDataSetChanged()
            }


        }

        actionLock = false
    }

    private fun collapse(position: Int) {
        val row = rowModels[position]
        val nextPosition = position + 1

        when (row.type) {

            RowModel.CHAPTER -> {

                /**
                 * remove element from below until it ends or find another node of same type
                 */
                while (true) {
                    if (nextPosition == rowModels.size || rowModels[nextPosition].type === RowModel.CHAPTER) {
                        break
                    }

                    rowModels.removeAt(nextPosition)
                }

                notifyDataSetChanged()
            }


        }

        actionLock = false
    }

    override fun getItemCount() = rowModels.size


}