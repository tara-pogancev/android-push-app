package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tarapogancev.pushapp.R
import com.tarapogancev.pushapp.databinding.FragmentStartExerciseBinding

class StartExerciseFragment : Fragment() {

    private var binding: FragmentStartExerciseBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartExerciseBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            buttonStartExercise.setOnClickListener {
                findNavController().navigate(R.id.action_startExerciseFragment_to_intervalSelectionFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}