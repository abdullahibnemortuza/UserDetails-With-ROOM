package com.example.userdetailsrb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.*
import com.example.userdetailsrb.database.PersonDatabase
import com.example.userdetailsrb.model.Person
import com.example.userdetailsrb.repository.PersonRepository
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonRepository
    val allPersons: LiveData<List<Person>>

    init {
        val dao = PersonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(dao)
        allPersons = repository.allPersons
    }
    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }
    fun delete(person: Person) = viewModelScope.launch {
        repository.delete(person)
    }
    fun update(person: Person) = viewModelScope.launch {
        repository.update(person)
    }
}