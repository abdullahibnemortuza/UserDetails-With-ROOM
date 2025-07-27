package com.example.userdetailsrb.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Update
    suspend fun update(person: Person)

    @Query("SELECT * FROM person_table ORDER BY id ASC")
    fun getAllPersons(): LiveData<List<Person>>
}