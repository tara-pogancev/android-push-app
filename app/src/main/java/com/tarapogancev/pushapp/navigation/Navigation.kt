package com.tarapogancev.pushapp.navigation

import androidx.navigation.NavController
import com.tarapogancev.pushapp.R

class Navigation(navController : NavController) {

    var navController = navController

    fun startToInterval(){
        navController.navigate(R.id.action_startExerciseFragment_to_intervalSelectionFragment)
    }

    fun intervalToCountdown(){
        navController.navigate(R.id.action_intervalSelectionFragment_to_countdownFragment)
    }

    fun countdownToExercise(){
        navController.navigate(R.id.action_countdownFragment_to_exerciseFragment)
    }

    fun exerciseToFinish(){
        navController.navigate(R.id.action_exerciseFragment_to_finishExerciseFragment)
    }

    fun finishToCountdown(){
        navController.navigate(R.id.action_finishExerciseFragment_to_countdownFragment)
    }

    fun finishToStart(){
        navController.navigate(R.id.action_finishExerciseFragment_to_startExerciseFragment)
    }

}