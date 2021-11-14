package com.example.week7_sec3_noteappfragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_sec3_noteappfragments.fragments.ListNote_Fragment
import com.example.week7_sec3_noteappfragments.modles.Notes
import kotlinx.android.synthetic.main.item_row.view.*


class RV_Adapter(val fragment1: ListNote_Fragment, private var listOf_Note: List<Notes> ): RecyclerView.Adapter<RV_Adapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = listOf_Note[position].note
        val idn = listOf_Note[position].id

        holder.itemView.apply {
            text_View.text = note

            editIcon.setOnClickListener {

                with(fragment1.sharedPreferences.edit()) {
                    putInt("NoteId", idn)
                    putString("Note", note)
                    apply()
                }
                fragment1.findNavController()
                    .navigate(R.id.action_listNote_Fragment_to_updateNote_Fragment)


            }
            deleteIcon.setOnClickListener {
                fragment1.myViewModel.delete_Note(idn)//--------------------------------------------------------------
                Toast.makeText(context, "Delete Success!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun getItemCount() = listOf_Note.size

    fun updateList(notes: List<Notes>) {
        this.listOf_Note = notes
        notifyDataSetChanged()

    }

}


