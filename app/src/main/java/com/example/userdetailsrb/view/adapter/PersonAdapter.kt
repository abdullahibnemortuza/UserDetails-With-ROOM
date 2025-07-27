package com.example.userdetailsrb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userdetailsrb.databinding.ItemPersonBinding
import com.example.userdetailsrb.model.Person

class PersonAdapter(private val personList: List<Person>,
    private val onDeleteClick:(Person) -> Unit,
    private val onEditClick:(Person) -> Unit):RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(val binding: ItemPersonBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.binding.tvName.text = person.name
        holder.binding.tvAddress.text = person.address

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(person)
        }
        holder.binding.btnEdit.setOnClickListener {
            onEditClick(person)
        }
    }
}