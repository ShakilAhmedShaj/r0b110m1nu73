package com.shajt3ch.robi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shajt3ch.robi.R
import kotlinx.android.synthetic.main.activity_document.*

class DocumentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
        this.title = "Document"

        val bundle: Bundle? = intent.extras

        val documentTitle = bundle?.getString("documentTitle")

        tv_document.text = documentTitle
    }
}