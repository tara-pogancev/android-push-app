package com.tarapogancev.pushapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarapogancev.pushapp.MainActivity
import com.tarapogancev.pushapp.databinding.FragmentStartExerciseBinding
import com.tarapogancev.pushapp.navigation.Navigation

class StartExerciseFragment : Fragment() {

    private var binding: FragmentStartExerciseBinding? = null

    private lateinit var navController : Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as MainActivity).navController

        val fragmentBinding = FragmentStartExerciseBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            buttonStartExercise.setOnClickListener {
                 navController.startToInterval()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}