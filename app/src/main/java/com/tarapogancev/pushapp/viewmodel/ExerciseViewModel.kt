package com.tarapogancev.pushapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciseViewModel : ViewModel() {

    private val _interval = MutableLiveData<Int>()
    val interval: LiveData<Int> = _interval

    private val _currentTimer = MutableLiveData<Int>()
    val currentTimer: LiveData<Int> = _currentTimer

    private val _countDownTimer = MutableLiveData<Int>()
    val countDownTimer: LiveData<Int> = _countDownTimer

    private val _totalTime = MutableLiveData<Int>()
    val totalTime : LiveData<Int> = _totalTime

    init {
        resetExercise()
    }

    private fun resetExercise() {
        _interval.value = 5
        _currentTimer.value = 5
        _totalTime.value = 0
        _countDownTimer.value = 3
    }

    fun setInterval(newInterval: Int) {
        _interval.value = newInterval
        _currentTimer.value = newInterval
        _countDownTimer.value = 3
    }

    fun startTimer() {

    }

    fun pauseTimer() {

    }

    fun finishExercise() {
        resetExercise()
    }

    fun startCountDownTimer() {

    }
}