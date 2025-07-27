package com.example.userdetailsrb.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdetailsrb.databinding.ActivityMainBinding
import com.example.userdetailsrb.databinding.DialogEditPersonBinding
import com.example.userdetailsrb.model.Person
import com.example.userdetailsrb.view.adapter.PersonAdapter
import com.example.userdetailsrb.viewmodel.PersonViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val personViewModel: PersonViewModel by viewModels()
    private lateinit var adapter: PersonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()

            if (name.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please Enter Both name and address", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val person = Person(name = name, address = address)
                personViewModel.insert(person)
                binding.etName.text?.clear()
                binding.etAddress.text?.clear()
            }
        }
        personViewModel.allPersons.observe(this) { persons ->
            adapter = PersonAdapter(
                persons,
                onDeleteClick = { person ->
                    personViewModel.delete(person)
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                },
                onEditClick = { person ->
                    showEditDialog(person)
                }
            )
            binding.rvPersons.adapter = adapter
        }

    }

    private fun showEditDialog(person: Person) {
        val dialogView = DialogEditPersonBinding.inflate(layoutInflater)

        dialogView.etEditName.setText(person.name)
        dialogView.etEditAddress.setText(person.address)

        AlertDialog.Builder(this)
            .setTitle("Edit Details")
            .setView(dialogView.root)
            .setPositiveButton("Update") { _, _ ->
                val updateName = dialogView.etEditName.text.toString().trim()
                val updateAddress = dialogView.etEditAddress.text.toString().trim()

                if (updateName.isNotEmpty() && updateAddress.isNotEmpty()) {
                    val updatePerson = person.copy(
                        name = updateName,
                        address = updateAddress
                    )
                    personViewModel.update(updatePerson)
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setupRecyclerView() {
        binding.rvPersons.layoutManager = LinearLayoutManager(this)
    }
}