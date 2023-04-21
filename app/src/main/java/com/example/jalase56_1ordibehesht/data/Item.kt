package com.example.jalase56_1ordibehesht.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var itemName: String,
    @ColumnInfo(name = "price")
    var itemPrice: Double,
    @ColumnInfo(name = "quantity")
    var quantityInStock: Int,
)
