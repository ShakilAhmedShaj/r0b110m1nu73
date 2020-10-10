package com.shajt3ch.robi.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.robi.R
import com.shajt3ch.robi.model.Audio
import com.shajt3ch.robi.model.Document
import com.shajt3ch.robi.model.Item
import com.shajt3ch.robi.model.Video
import com.shajt3ch.robi.view.DocumentActivity
import com.shajt3ch.robi.view.VideoActivity
import kotlinx.android.synthetic.main.document_item_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by Shakil Ahmed Shaj on 10,October,2020.
 * shakilahmedshaj@gmail.com
 */

class NestedAdapter(private val list: List<Item>) :
    RecyclerView.Adapter<NestedAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
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

            is Document -> {
                val item = list[position] as Document

                holder.view.iv_doc_image.setImageResource(item.icon)
                holder.view.tv_doc_title.text = item.title
                holder.view.tv_doc_author.text = item.authorName

                val intent = Intent(context, DocumentActivity::class.java)
                intent.putExtra("documentTitle", item.title)
                intent.putExtra("authorName", item.authorName)

                holder.view.setOnClickListener {

                    context.startActivity(intent)

                    Toast.makeText(context, item.title + " clicked", Toast.LENGTH_SHORT).show()
                }
            }
            is Video -> {
                val item = list[position] as Video

                holder.view.iv_item_image.setImageResource(item.icon)
                holder.view.tv_item_title.text = item.title

                val intent = Intent(context, VideoActivity::class.java)
                intent.putExtra("videoTitle", item.title)

                holder.view.setOnClickListener {

                    context.startActivity(intent)

                    Toast.makeText(context, item.title + " clicked", Toast.LENGTH_SHORT).show()
                }
            }
            is Audio -> {
                val item = list[position] as Audio

                holder.view.iv_item_image.setImageResource(item.icon)
                holder.view.tv_item_title.text = item.title

                holder.view.setOnClickListener {
                    Toast.makeText(context, item.title + " clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}