package com.example.userdetailsrb.repository

import androidx.lifecycle.LiveData
import com.example.userdetailsrb.model.Person
import com.example.userdetailsrb.model.PersonDao

class PersonRepository(private val dao: PersonDao) {
    val allPersons: LiveData<List<Person>> = dao.getAllPersons()

    suspend fun insert(person: Person) = dao.insert(person)
    suspend fun delete(person: Person) = dao.delete(person)
    suspend fun update(person: Person) = dao.update(person)
}