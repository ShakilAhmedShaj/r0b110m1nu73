package com.shajt3ch.robi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.shajt3ch.robi.R
import kotlinx.android.synthetic.main.activity_document.*

class DocumentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
        if (supportActionBar != null) {

            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            supportActionBar!!.setDisplayShowHomeEnabled(true);
        }

        this.title = "Document"

        val bundle: Bundle? = intent.extras

        val documentTitle = bundle?.getString("documentTitle")
        val authorName = bundle?.getString("authorName")

        tv_document.text = documentTitle
        tv_author.text = authorName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}