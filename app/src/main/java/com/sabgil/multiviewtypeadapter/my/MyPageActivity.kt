package com.sabgil.multiviewtypeadapter.my

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sabgil.multiviewtypeadapter.R
import com.sabgil.multiviewtypeadapter.databinding.ItemBottomDescBinding
import com.sabgil.multiviewtypeadapter.databinding.ItemMenuBinding
import com.sabgil.multiviewtypeadapter.databinding.ItemMenuGroupBinding
import com.sabgil.multiviewtypeadapter.databinding.ItemProfileBinding
import com.sabgil.multiviewtypeadapter.my.MyPageItem.*
import com.sabgil.mutiviewtype.MultiViewTypeAdapter
import com.sabgil.mutiviewtype.multiViewTypeAdapter
import com.sabgil.mutiviewtype.type

class MyPageActivity : AppCompatActivity() {

    lateinit var adapter: MultiViewTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        setupRecyclerView()
        update()
    }

    private fun setupRecyclerView() {
        adapter = multiViewTypeAdapter {
            type<Profile, ItemProfileBinding> {
                onCreate { binding ->
                    binding.profileImage.setOnClickListener {
                        Toast.makeText(this@MyPageActivity, "Click Profile", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                onBind { item, binding ->
                    binding.item = item
                }
            }

            type<Menu, ItemMenuBinding> {
                onCreate { binding, _, itemSupplier ->
                    binding.menuContainer.setOnClickListener {
                        val menu = itemSupplier()

                        menu?.let {
                            Toast.makeText(
                                this@MyPageActivity,
                                "Click Meunu" + it.menuName,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                onBind { item, binding ->
                    binding.item = item
                }
            }

            type<MenuGroup, ItemMenuGroupBinding>() {
                onBind { item, binding ->
                    binding.item = item
                }
            }

            type<BottomDesc, ItemBottomDescBinding> {
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
                Profile("sabgilhun", "YongHun Kim"),
                MenuGroup("Group 1"),
                Menu("Menu A"),
                Menu("Menu B"),
                Menu("Menu C"),
                MenuGroup("Group 2"),
                Menu("Menu 1"),
                Menu("Menu 2"),
                Menu("Menu 3"),
                BottomDesc("bottom description...")
            )
        )
    }
}