package com.example.livedataview_model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedataview_model.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val resultTextView by lazy {
        findViewById<TextView>(R.id.resultTextView)
    }
    private val numberEditText by lazy {
        findViewById<EditText>(R.id.numberEditText)
    }
    private val plusButton by lazy {
        findViewById<Button>(R.id.plusButton)
    }
    private val minusButton by lazy {
        findViewById<Button>(R.id.minusButton)
    }

    lateinit var binding: ActivityMainBinding

    var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        // ViewModel 초기화
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // 라이브 데이터 관찰
        mainViewModel.currentValue.observe(this, Observer {
            Log.d("로그", "MainActivity에서 MainViewModel을 호출하여 currentValue 관찰")
            result = it.toString()
            binding.invalidateAll()
        })
        plusButton.setOnClickListener {
            val input = numberEditText.text.toString().toInt()
            Log.d("로그", "input : $input")
            mainViewModel.updateValue(ActionType.PLUS, input)
        }
        minusButton.setOnClickListener {
            val input = numberEditText.text.toString().toInt()
            Log.d("로그", "input : $input")
            mainViewModel.updateValue(ActionType.MINUS, input)
        }

    }

}