package com.example.mvptestapp.contract

interface ContractInterface {

    interface View {
        fun displayCounter()
        fun displayClicksPerSecond()
    }

    interface Presenter {
        fun attachNewView(newView: View)
        fun detachView()
        fun incrementValue()
        fun decrementValue()
        fun resetValue()
        fun getCounter(): String
        fun getClicksPerSecond(): String
    }

    interface Model {
        fun getCounter(): Int
        fun getClicksPerSecond(): Double
        fun incrementCounter()
        fun decrementCounter()
        fun resetCounter()
        fun incrementClicks()
    }

}