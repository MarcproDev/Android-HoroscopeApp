package com.example.horoscopeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopeapp.domain.model.HoroscopeModel
import com.example.horoscopeapp.domain.model.PredictionModel
import com.example.horoscopeapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) : ViewModel() {
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state
    lateinit var horoscope: HoroscopeModel

    fun getHoroscope(sing: HoroscopeModel){
        horoscope = sing
        viewModelScope.launch {
            //Hilo principal
            _state.value = HoroscopeDetailState.Loading
          val result: PredictionModel? = withContext(Dispatchers.IO){getPredictionUseCase(sing.name)}//Hilo secundario
            //Hilo principal
            if (result != null) {
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)
            }else{
                _state.value = HoroscopeDetailState.Error("An error has occurred, please try again later.")
            }
        }
    }
}