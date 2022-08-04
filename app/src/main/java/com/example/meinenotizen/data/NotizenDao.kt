package com.example.meinenotizen.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotizenDao {

    @Query("SELECT * FROM Notizen WHERE id = :id")
    suspend fun getSpecificNote(id: Int): Notizen

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notizen: Notizen)

    @Update
    suspend fun update(notizen: Notizen)

    @Delete
    suspend fun delete(notizen: Notizen)

    @Query("DELETE FROM Notizen WHERE id = :id")
    suspend fun deleteSpecificNote(id: Int)

    @Query("SELECT * FROM Notizen ORDER BY title")
    fun getAll(): LiveData<List<Notizen>>

    @Query("SELECT * from Notizen WHERE id = :key")
    fun getById(key: Long): LiveData<Notizen>

    @Query("DELETE from Notizen WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE from Notizen")
    suspend fun deleteAll()
}