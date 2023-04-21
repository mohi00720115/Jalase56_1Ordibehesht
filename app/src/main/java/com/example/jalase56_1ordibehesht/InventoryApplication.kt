package com.example.inventory

import android.app.Application
import com.example.jalase56_1ordibehesht.data.ItemRoomDataBase

class InventoryApplication : Application() {
    val dataBase: ItemRoomDataBase by lazy { ItemRoomDataBase.getDataBase(this) }
}
