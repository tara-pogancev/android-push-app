package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.MainActivity
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentCountdownBinding
import com.tarapogancev.pushapp.databinding.FragmentIntervalSelectionBinding
import com.tarapogancev.pushapp.navigation.Navigation
import com.tarapogancev.pushapp.viewmodel.ExerciseViewModel


class CountdownFragment : Fragment() {

    private var binding: FragmentCountdownBinding? = null

    private lateinit var navController : Navigation

    private val sharedViewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as MainActivity).navController

        val fragmentBinding = FragmentCountdownBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner

        }

        sharedViewModel.startCountDownTimer()

        sharedViewModel.countDownTimer.observe(viewLifecycleOwner) { timer ->
            if (timer == 0) {
                navController.countdownToExercise()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
