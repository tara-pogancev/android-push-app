package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentCountdownBinding
import com.tarapogancev.pushapp.databinding.FragmentIntervalSelectionBinding
import com.tarapogancev.pushapp.viewmodel.ExerciseViewModel


class CountdownFragment : Fragment() {

    private var binding: FragmentCountdownBinding? = null

    private val sharedViewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        sharedViewModel.countDownTimer.observe(viewLifecycleOwner) { timer ->
            if (timer == -1) {
                findNavController().navigate(R.id.action_countdownFragment_to_exerciseFragment)
            }
        }

        sharedViewModel.startCountDownTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
