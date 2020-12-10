package com.sabgil.multiviewtypeadapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sabgil.multiviewtypeadapter.chat.ChatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.startChattingSample).setOnClickListener {
            startChatActivity()
        }
    }

    private fun startChatActivity() {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }
}