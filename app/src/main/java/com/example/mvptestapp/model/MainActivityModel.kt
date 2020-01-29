package com.example.mvptestapp.model

import android.content.SharedPreferences
import android.os.SystemClock
import com.example.mvptestapp.contract.ContractInterface


class MainActivityModel(_sharedPreferences: SharedPreferences): ContractInterface.Model {

    private val sharedPreferences = _sharedPreferences
    private val editor = sharedPreferences.edit()

    private var clicks = 0
    private var startTime = -1L
    private var clicksPerSecond = 0.0

    override fun getCounter(): Int {
        return sharedPreferences.getInt("counter",0)
    }

    override fun incrementCounter() {
        editor.putInt("counter",getCounter()+1)
        editor.commit()
    }

    override fun decrementCounter() {
        editor.putInt("counter",getCounter()-1)
        editor.commit()
    }

    override fun resetCounter() {
        editor.putInt("counter",0)
        editor.commit()
    }

    override fun incrementClicks() {
        clicks++
        if (clicks == 1){
            startTime = SystemClock.elapsedRealtime()
            clicksPerSecond = 1.0
        }
        else calculateClicksPerSecond()
    }

    private fun calculateClicksPerSecond(){
        var currentTime = SystemClock.elapsedRealtime()
        clicksPerSecond = clicks/((currentTime - startTime).toFloat().div(1000.0))
    }

    override fun getClicksPerSecond(): Double {
        return clicksPerSecond
    }
}