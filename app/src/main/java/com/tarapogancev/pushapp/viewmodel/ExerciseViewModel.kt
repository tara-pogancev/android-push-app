package com.tarapogancev.pushapp.viewmodel

import android.os.CountDownTimer
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

    private lateinit var startTimer: CountDownTimer

    

    init {
        resetExercise()
    }

    private fun resetExercise() {
        _interval.value = 5
        _currentTimer.value = 5
        _totalTime.value = 0
        _countDownTimer.value = 4
    }

    fun setInterval(newInterval: Int) {
        _interval.value = newInterval
        _currentTimer.value = newInterval
    }

    fun startTimer() {

    }

    fun pauseTimer() {

    }

    fun finishExercise() {
        resetExercise()
    }

    fun startCountDownTimer() {
        startTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _countDownTimer.value = _countDownTimer.value?.minus(1)
            }

            override fun onFinish() {
                _countDownTimer.value = 4
            }
        }.start()
    }
}