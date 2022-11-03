package com.tarapogancev.pushapp.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tarapogancev.pushapp.R


const val TICK_MILLIS: Long = 123

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

    private val _stopWatchCountDown = MutableLiveData<String>()
    val stopWatchCountDown: LiveData<String> = _stopWatchCountDown

    private val _stopWatch = MutableLiveData<String>()
    val stopWatch: LiveData<String> = _stopWatch

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
        _currentTimer.value = 5000
        _totalTime.value = 0
        _countDownTimer.value = 3000
        _position.value = positionList.random()
        _stopWatch.value = "00:00.000"
        _stopWatchCountDown.value = "3.00"
    }

    fun setInterval(newInterval: Int) {
        _interval.value = newInterval
        _currentTimer.value = _interval.value?.times(1000)
    }

    fun startTimer() {
        _currentTimer.value = _interval.value?.times(1000)
        exerciseTimer = object : CountDownTimer((_interval.value!!.times(1000)).toLong(), TICK_MILLIS) {
            override fun onTick(p0: Long) {
                _totalTime.value = _totalTime.value?.plus(TICK_MILLIS.toInt())
                _currentTimer.value = _currentTimer.value?.minus(TICK_MILLIS.toInt())
                formatStopWatch()

            }

            override fun onFinish() {
                Log.e("EXERCISE TIMER", "Finished!")
                _currentTimer.value = _interval.value?.times(1000)
                changeImage()
                exerciseTimer.start()
            }
        }.start()

        isTimerRunning = true
    }

    fun pauseTimer() {
        Log.e("TIMER", "Paused!")
        exerciseTimer.cancel()
        isTimerRunning = false
    }

    fun resumeTimer() {
        Log.e("TIMER", "Resumed!")
        exerciseTimer = object : CountDownTimer(_currentTimer.value!!.toLong(), TICK_MILLIS) {
            override fun onTick(p0: Long) {
                _totalTime.value = _totalTime.value?.plus(TICK_MILLIS.toInt())
                _currentTimer.value = _currentTimer.value?.minus(TICK_MILLIS.toInt())
                formatStopWatch()

            }

            override fun onFinish() {
                Log.e("EXERCISE TIMER2", "Finished!")
                _currentTimer.value = _interval.value?.times(1000)
                changeImage()
                startTimer()
            }
        }.start()
        isTimerRunning = true
    }

    fun stopTimer() {
        Log.e("EXERCISE TIMER", "Stopped!")
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
        startTimer = object : CountDownTimer(3000, TICK_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {
                _countDownTimer.value = _countDownTimer.value?.minus(TICK_MILLIS.toInt())
                formatStopWatchCountDown()
            }

            override fun onFinish() {
                _countDownTimer.value = 3000
                Log.e("START TIMER", "Finished!")
            }
        }.start()
    }

    fun formatStopWatchCountDown() {
        val time = _countDownTimer.value ?: 0
        var seconds = time / 1000
        var millis = time % 1000

        var secondsStr = seconds.toString()

        var millisStr = millis.toString()
        if (millisStr.length == 1) {
            millisStr = "00$millisStr"
        } else if (millisStr.length == 2) {
            millisStr = "0$millisStr"
        }

        _stopWatchCountDown.value = "$secondsStr.$millisStr"
    }

    fun formatStopWatch() {
        val time = _totalTime.value ?: 0
        var minutes = time / 60000
        var seconds = time % 60000 / 1000
        var millis = time % 1000

        var minutesStr = minutes.toString()
        if (minutesStr.length == 1) {
            minutesStr = "0$minutesStr"
        }

        var secondsStr = seconds.toString()
        if (secondsStr.length == 1) {
            secondsStr = "0$secondsStr"
        }

        var millisStr = millis.toString()
        if (millisStr.length == 1) {
            millisStr = "00$millisStr"
        } else if (millisStr.length == 2) {
            millisStr = "0$millisStr"
        }

        _stopWatch.value = "$minutesStr:$secondsStr.$millisStr"
    }
}