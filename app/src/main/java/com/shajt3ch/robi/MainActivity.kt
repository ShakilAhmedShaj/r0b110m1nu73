package com.shajt3ch.robi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.robi.model.*
import com.shajt3ch.robi.view.adapter.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        populateData()
    }


    private fun populateData() {

        val chapter1 = ArrayList<Item>()
        chapter1.add(Video(R.mipmap.ic_launcher_round, "one"))
        chapter1.add(Document(R.mipmap.ic_launcher_round, "one", "Author"))
        chapter1.add(Video(R.mipmap.ic_launcher_round, "two"))

        val chapter2 = ArrayList<Item>()
        chapter2.add(Document(R.mipmap.ic_launcher_round, "one", "Author"))
        chapter2.add(Video(R.mipmap.ic_launcher_round, "one"))
        chapter2.add(Video(R.mipmap.ic_launcher_round, "two"))
        chapter2.add(Audio(R.mipmap.ic_launcher_round, "one"))

        val video3 = Video(R.mipmap.ic_launcher_round, "zero")
        val document4 = Document(R.mipmap.ic_launcher_round, "zero", "author")

        val items = ArrayList<Item>()
        items.add(Chapter(false, chapter1))
        items.add(Chapter(false, chapter2))
        items.add(video3)
        items.add(document4)

        val adapter = BaseAdapter(items)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


    }


}