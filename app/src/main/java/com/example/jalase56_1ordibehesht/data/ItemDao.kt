package com.example.jalase56_1ordibehesht.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    /**
     * حالت OnConflictStrategy.IGNORE یعنی وقتی پرایمری کی یکی باشه میاد این 2تا رو ایگنور می کنه.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<Item>>

    @Delete
    suspend fun delete(item: Item)

}