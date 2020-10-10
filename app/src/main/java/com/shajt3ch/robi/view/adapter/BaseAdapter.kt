package com.shajt3ch.robi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.robi.R
import com.shajt3ch.robi.model.*
import kotlinx.android.synthetic.main.chapter_item_layout.view.*
import kotlinx.android.synthetic.main.document_item_layout.view.*
import kotlinx.android.synthetic.main.document_item_layout.view.image
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by Shakil Ahmed Shaj on 10,October,2020.
 * shakilahmedshaj@gmail.com
 */

const val CHAPTER_LAYOUT = 1
const val DOCUMENT_LAYOUT = 2
const val ITEM_LAYOUT = 3

class BaseAdapter(private val list: List<Item>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Chapter -> {
                CHAPTER_LAYOUT
            }
            is Document -> {
                DOCUMENT_LAYOUT
            }
            else -> {
                ITEM_LAYOUT
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return when (viewType) {
            CHAPTER_LAYOUT -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.chapter_item_layout,
                        parent, false
                    )
                )
            }
            DOCUMENT_LAYOUT -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.document_item_layout,
                        parent, false
                    )
                )
            }
            else -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_layout,
                        parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (list[position]) {
            is Chapter -> {
                val item = list[position] as Chapter

                holder.view.itemType.text = item.title

                if (item.isExpanded) {
                    holder.view.recyclerView.visibility = View.VISIBLE
                    val nestedAdapter = NestedAdapter(item.items)
                    holder.view.recyclerView.layoutManager = LinearLayoutManager(context)
                    holder.view.recyclerView.setHasFixedSize(true)
                    holder.view.recyclerView.adapter = nestedAdapter
                } else {
                    holder.view.recyclerView.visibility = View.GONE
                }

                holder.view.setOnClickListener {
                    item.isExpanded = !(list[position] as Chapter).isExpanded
                    notifyItemChanged(position)
                }
            }
            is Document -> {
                val item = list[position] as Document

                holder.view.itemType2.text = "Document"

                holder.view.image.setImageResource(item.icon)
                holder.view.titleTV.text = item.title
                holder.view.authorTV.text = item.authorName

                holder.view.setOnClickListener {
                    Toast.makeText(context, "document clicked", Toast.LENGTH_SHORT).show()
                }
            }
            is Video -> {
                val item = list[position] as Video

                holder.view.itemType1.text = "Video"

                holder.view.image.setImageResource(item.icon)
                holder.view.title.text = item.title

                holder.view.setOnClickListener {
                    Toast.makeText(context, "video clicked", Toast.LENGTH_SHORT).show()
                }
            }
            is Audio -> {
                val item = list[position] as Audio

                holder.view.itemType1.text = "Audio"

                holder.view.image.setImageResource(item.icon)
                holder.view.title.text = item.title

                holder.view.setOnClickListener {
                    Toast.makeText(context, "audio clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}