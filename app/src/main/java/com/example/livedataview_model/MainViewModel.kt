package com.example.livedataview_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브데이터를 포함
class MainViewModel: ViewModel() {

    // 뮤터블 라이브 데이터 - 수정가능
    // 라이브 데이터(일반) - 수정불가

    // 내부(private)의 데이터는 변경 가능한 뮤터블로 생성
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기값 설정 (생성자)
    init {
        Log.d("로그", "MainViewModel 생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(type: ActionType, input: Int) {
        Log.d("로그", "update value 함수 호출")
        when (type) {
            ActionType.PLUS -> _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS -> _currentValue.value = _currentValue.value?.minus(input)
        }
        Log.d("로그", "_currentValue : ${_currentValue.value}")
    }

}