package com.sabgil.multiviewtypeadapter.chat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sabgil.multiviewtypeadapter.R
import com.sabgil.multiviewtypeadapter.chat.ChatRoomItem.*
import com.sabgil.multiviewtypeadapter.databinding.ItemDateSectionBinding
import com.sabgil.multiviewtypeadapter.databinding.ItemMyWordsBinding
import com.sabgil.multiviewtypeadapter.databinding.ItemOpponentWordsBinding
import com.sabgil.mutiviewtype.MultiViewTypeAdapter
import com.sabgil.mutiviewtype.multiViewTypeAdapter
import com.sabgil.mutiviewtype.singleViewTypeAdapter
import com.sabgil.mutiviewtype.type

class ChatActivity : AppCompatActivity() {

    lateinit var adapter: MultiViewTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        setupRecyclerView()
        update()
    }

    private fun setupRecyclerView() {
        adapter = multiViewTypeAdapter {
            type<MyWords, ItemMyWordsBinding> {
                onCreate { binding ->
                    binding.content.setOnLongClickListener {
                        Toast.makeText(this@ChatActivity, "My words", Toast.LENGTH_SHORT).show()
                        true
                    }
                }

                onBind { item, binding ->
                    binding.item = item
                }
            }

            type<OpponentWords, ItemOpponentWordsBinding> {
                onCreate { binding ->
                    binding.content.setOnLongClickListener {
                        Toast.makeText(this@ChatActivity, "Opponent's Words", Toast.LENGTH_SHORT)
                            .show()
                        true
                    }
                }

                onBind { item, binding ->
                    binding.item = item
                }
            }

            type<DateSection, ItemDateSectionBinding> {
                onBind { item, binding ->
                    binding.item = item
                }
            }
        }

        findViewById<RecyclerView>(R.id.chatRecyclerView).adapter = adapter
    }

    private fun update() {
        adapter.update(
            listOf(
                DateSection("2020.12.31"),
                OpponentWords("key1", "5"),
                MyWords("key2", "4"),
                OpponentWords("key3", "3"),
                MyWords("key4", "2"),
                OpponentWords("key5", "1"),
                DateSection("2021.01.01"),
                OpponentWords("key6", "Happy new year"),
                OpponentWords("key7", "\uD83C\uDF8A"),
                MyWords("key8", "Happy new year \uD83C\uDF89\uD83C\uDF89")
            )
        )
    }
}