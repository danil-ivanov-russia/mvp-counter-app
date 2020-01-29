package com.example.mvptestapp.presenter

import android.content.SharedPreferences
import com.example.mvptestapp.contract.ContractInterface
import com.example.mvptestapp.model.MainActivityModel

class MainActivityPresenter(_view: ContractInterface.View, _sharedPreferences: SharedPreferences): ContractInterface.Presenter{

    private var view: ContractInterface.View? = _view
    private var model: ContractInterface.Model = MainActivityModel(_sharedPreferences)

    override fun attachNewView(newView: ContractInterface.View) {
        this.view = newView
    }

    override fun detachView() {
        this.view = null
    }

    override fun incrementValue() {
        model.incrementClicks()
        model.incrementCounter()
        view?.displayCounter()
        view?.displayClicksPerSecond()
    }

    override fun decrementValue() {
        model.incrementClicks()
        model.decrementCounter()
        view?.displayCounter()
        view?.displayClicksPerSecond()
    }

    override fun resetValue() {
        model.incrementClicks()
        model.resetCounter()
        view?.displayCounter()
        view?.displayClicksPerSecond()
    }

    override fun getCounter() = model.getCounter().toString()

    override fun getClicksPerSecond() = String.format("%.5f",model.getClicksPerSecond())

}