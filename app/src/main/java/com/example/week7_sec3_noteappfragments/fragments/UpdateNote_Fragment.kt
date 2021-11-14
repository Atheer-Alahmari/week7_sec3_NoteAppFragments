package com.example.week7_sec3_noteappfragments.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
import androidx.navigation.fragment.findNavController
import com.example.week7_sec3_noteappfragments.MyViewModel
import com.example.week7_sec3_noteappfragments.R
import com.example.week7_sec3_noteappfragments.RV_Adapter
import com.example.week7_sec3_noteappfragments.modles.NoteDataBase
import com.example.week7_sec3_noteappfragments.modles.Notes
import kotlinx.android.synthetic.main.fragment_update_note_.*


class UpdateNote_Fragment : Fragment() {
    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java)}//--------------------------------

    lateinit var listNote:List<Notes>
    lateinit var sharedPreferences: SharedPreferences
    lateinit var updateBtn:Button
    lateinit var updateNoteED:EditText

    override fun onCreateView
                (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_note_, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)

        listNote= listOf()
        updateNoteED=view.findViewById(R.id.updateNote_ED)
        updateBtn=view.findViewById(R.id.update_btn)

        val idn = sharedPreferences.getInt("NoteId", 0)
        val note = sharedPreferences.getString("Note", "")
        updateNoteED.setText(note)

        updateBtn.setOnClickListener {

            var input = updateNoteED.text.toString()

            if (input.isNotEmpty()) {
                myViewModel.update_Note(idn, input)

                Toast.makeText(activity, "Update Success!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    activity,
                    "Update Doesn't Work , Something Empty ",
                    Toast.LENGTH_SHORT
                ).show()
            }

            with(sharedPreferences.edit()){
                putString("NoteId", input)
                apply()
            }
            findNavController().navigate(R.id.action_updateNote_Fragment_to_listNote_Fragment)


            updateNoteED.text.clear()
            updateNoteED.clearFocus()
        }



        return view
    }


}