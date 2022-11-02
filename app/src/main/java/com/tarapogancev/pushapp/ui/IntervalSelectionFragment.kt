package com.tarapogancev.pushapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.MainActivity
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentIntervalSelectionBinding
import com.tarapogancev.pushapp.navigation.Navigation
import com.tarapogancev.pushapp.viewmodel.ExerciseViewModel

class IntervalSelectionFragment : Fragment() {

    private var binding: FragmentIntervalSelectionBinding? = null

    private lateinit var navController : Navigation

    private val sharedViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as MainActivity).navController

        val fragmentBinding = FragmentIntervalSelectionBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner

            buttonStartExercise.setOnClickListener {
                val editText = editTextInterval.text.toString()
                viewModel?.setInterval(editText.toInt())
                navController.intervalToCountdown()
            }

            editTextInterval.addTextChangedListener(object :TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    val editText = editTextInterval.text.toString()
                    if (editText.isEmpty()) {
                        buttonStartExercise.isEnabled = false
                    } else {
                    buttonStartExercise.isEnabled = (editTextInterval.text.toString()).toInt() > 0
                    }
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}