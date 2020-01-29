package com.example.mvptestapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvptestapp.R
import com.example.mvptestapp.contract.ContractInterface
import com.example.mvptestapp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity(), ContractInterface.View {

    private var presenter : MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference =  getSharedPreferences("counter_settings", Context.MODE_PRIVATE)
        attachPresenter(sharedPreference)

        incrementButton.setOnClickListener { presenter?.incrementValue() }
        decrementButton.setOnClickListener { presenter?.decrementValue() }
        resetButton.setOnClickListener { presenter?.resetValue() }

        counterTextView.text = presenter?.getCounter()
        clicksPerSecondTextView.text = getString(R.string.main_clicksPerSecond, presenter?.getClicksPerSecond())
    }

    private fun attachPresenter(sharedPreferences: SharedPreferences){
        var oldPresenter = lastCustomNonConfigurationInstance
        if (oldPresenter == null){
            presenter = MainActivityPresenter(this, sharedPreferences)
        }
        else {
            presenter = oldPresenter as MainActivityPresenter
            presenter?.attachNewView(this)
        }
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter as Any
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }

    override fun displayCounter() {
        counterTextView.text = presenter?.getCounter()
    }

    override fun displayClicksPerSecond() {
        clicksPerSecondTextView.text = getString(R.string.main_clicksPerSecond, presenter?.getClicksPerSecond())
    }
}
