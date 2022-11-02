package com.tarapogancev.pushapp.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tarapogancev.pushapp.R

class ExerciseViewModel : ViewModel() {

    private val _interval = MutableLiveData<Int>()
    val interval: LiveData<Int> = _interval

    private val _currentTimer = MutableLiveData<Int>()
    val currentTimer: LiveData<Int> = _currentTimer

    private val _countDownTimer = MutableLiveData<Int>()
    val countDownTimer: LiveData<Int> = _countDownTimer

    private val _totalTime = MutableLiveData<Int>()
    val totalTime : LiveData<Int> = _totalTime

    private val _position = MutableLiveData<Int>()
    val position : LiveData<Int> = _position

    private lateinit var startTimer: CountDownTimer

    private lateinit var exerciseTimer: CountDownTimer
    var isTimerRunning: Boolean = false

    private val positionList = listOf<Int>(
        R.drawable.position1,
        R.drawable.position2,
        R.drawable.position3
    )

    init {
        resetExercise()
    }

    private fun resetExercise() {
        _interval.value = 5
        _currentTimer.value = 5
        _totalTime.value = 0
        _countDownTimer.value = 4
        _position.value = positionList.random()
    }

    fun setInterval(newInterval: Int) {
        _interval.value = newInterval
        _currentTimer.value = newInterval
    }

    fun startTimer() {
        exerciseTimer = object : CountDownTimer((_interval.value!!.times(1000)).toLong(), 1000) {
            override fun onTick(p0: Long) {
                _totalTime.value = _totalTime.value?.plus(1)
                _currentTimer.value = _currentTimer.value?.minus(1)

            }

            override fun onFinish() {
                Log.e("EXERCISE TIMER", "Finished!")
                _currentTimer.value = _interval.value
                changeImage()
                exerciseTimer.start()
            }
        }.start()

        isTimerRunning = true
    }

    fun pauseTimer() {
        exerciseTimer.cancel()
        isTimerRunning = false
    }

    fun resumeTimer() {
        exerciseTimer = object : CountDownTimer((_currentTimer.value!!.times(1000)).toLong(), 1000) {
            override fun onTick(p0: Long) {
                _totalTime.value = _totalTime.value?.plus(1)
                _currentTimer.value = _currentTimer.value?.minus(1)

            }

            override fun onFinish() {
                Log.e("EXERCISE TIMER", "Finished!")
                _currentTimer.value = _interval.value
                changeImage()
                exerciseTimer.start()
            }
        }.start()
        isTimerRunning = true
    }

    fun stopTimer() {
        exerciseTimer.cancel()
        isTimerRunning = false
    }

    fun finishExercise() {
        exerciseTimer.cancel()
        isTimerRunning = false
        resetExercise()
    }

    fun changeImage() {
        var newPosition = positionList.random()
        while (newPosition == _position.value) {
            newPosition = positionList.random()
        }
        _position.value = newPosition
    }

    fun startCountDownTimer() {
        startTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _countDownTimer.value = _countDownTimer.value?.minus(1)
            }

            override fun onFinish() {
                _countDownTimer.value = 4
                Log.e("START TIMER", "Finished!")
            }
        }.start()
    }
}