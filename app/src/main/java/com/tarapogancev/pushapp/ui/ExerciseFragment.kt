package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.MainActivity
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentExerciseBinding
import com.tarapogancev.pushapp.navigation.Navigation
import com.tarapogancev.pushapp.viewmodel.ExerciseViewModel


class ExerciseFragment : Fragment() {

    private var binding: FragmentExerciseBinding? = null

    private lateinit var navController : Navigation

    private val sharedViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as MainActivity).navController

        val fragmentBinding = FragmentExerciseBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedViewModel.isTimerRunning) {
            sharedViewModel.stopTimer()
        }

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel

            buttonPauseExercise.setOnClickListener {
                if (sharedViewModel.isTimerRunning) {
                    sharedViewModel.pauseTimer()
                    buttonPauseExercise.setText(R.string.resume)
                } else {
                    sharedViewModel.resumeTimer()
                    buttonPauseExercise.setText(R.string.pause)
                }
            }

            buttonStopExercise.setOnClickListener {
                sharedViewModel.stopTimer()
                navController.exerciseToFinish()
            }
        }

        sharedViewModel.startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}