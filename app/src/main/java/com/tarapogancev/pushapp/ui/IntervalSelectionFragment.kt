package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentIntervalSelectionBinding
import com.tarapogancev.pushapp.viewmodel.ExerciseViewModel

class IntervalSelectionFragment : Fragment() {

    private var binding: FragmentIntervalSelectionBinding? = null

    private val sharedViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                findNavController().navigate(R.id.action_intervalSelectionFragment_to_countdownFragment)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}