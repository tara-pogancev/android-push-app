package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentExerciseBinding
import com.tarapogancev.pushapp.databinding.FragmentFinishExerciseBinding
import com.tarapogancev.pushapp.viewmodel.ExerciseViewModel

class FinishExerciseFragment : Fragment() {

    private var binding: FragmentFinishExerciseBinding? = null

    private val sharedViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFinishExerciseBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel

            buttonRestart.setOnClickListener {
                sharedViewModel.finishExercise()
                findNavController().navigate(R.id.action_finishExerciseFragment_to_startExerciseFragment)
            }

            buttonResume.setOnClickListener {
                findNavController().navigate(R.id.action_finishExerciseFragment_to_countdownFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}