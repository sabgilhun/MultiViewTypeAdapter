package com.sabgil.multiviewtypeadapter.my

import com.sabgil.mutiviewtype.BaseItem

sealed class MyPageItem(key: Any) : BaseItem(key) {

    data class Profile(
        val userId: String,
        val name: String
    ) : MyPageItem(userId)

    data class Menu(
        val menuName: String
    ) : MyPageItem(menuName)

    data class MenuGroup(
        val menuGroupName: String
    ) : MyPageItem(menuGroupName)

    data class BottomDesc(
        val contents: String
    ) : MyPageItem(BottomDesc::class.simpleName!!)
}