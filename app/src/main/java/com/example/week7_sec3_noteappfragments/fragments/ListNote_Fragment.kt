package com.example.week7_sec3_noteappfragments.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_sec3_noteappfragments.MyViewModel
import com.example.week7_sec3_noteappfragments.R
import com.example.week7_sec3_noteappfragments.RV_Adapter
import com.example.week7_sec3_noteappfragments.modles.Notes
import kotlinx.android.synthetic.main.fragment_list_note_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ListNote_Fragment : Fragment() {
    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }//------------------------

    lateinit var list_RV: RecyclerView
    private lateinit var rvAdapter: RV_Adapter
    lateinit var listNote: List<Notes>
    lateinit var sharedPreferences: SharedPreferences

    lateinit var noteED: EditText
    lateinit var submitBtn: Button

    override fun onCreateView
                (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_note_, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)

        list_RV = view.findViewById(R.id.recycler_View)// Recycler View

        noteED = view.findViewById(R.id.note_ED)
        submitBtn = view.findViewById(R.id.btn_submit)
        listNote = listOf()


        rvAdapter = RV_Adapter(this, listNote)
        list_RV.adapter = rvAdapter
        list_RV.layoutManager = LinearLayoutManager(requireContext())
        myViewModel.get_Note().observe(viewLifecycleOwner, { notes ->
            rvAdapter.updateList(notes)
        })



        submitBtn.setOnClickListener {
            var input = noteED.text.toString()

            //----------------Save DB--------------
            if (input.isNotEmpty()) {
                myViewModel.add_Note(input)
                Toast.makeText(activity, "data saved successfully! ", Toast.LENGTH_SHORT)
                    .show()

            } else {
                Toast.makeText(activity, "Please Enter a Note ", Toast.LENGTH_SHORT).show()

            }
            //---------------------------------------------------
            noteED.text.clear()
            noteED.clearFocus()
        }


        return view
    }

}