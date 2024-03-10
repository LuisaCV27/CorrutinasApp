package mx.unam.fi.corrutinasapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel:ViewModel(){
    var resultState by mutableStateOf("")
        private set
    var countTime by mutableStateOf(0)
        private set
    var resultState2 by mutableStateOf("")
        private set
    var countTime2 by mutableStateOf(0)
        private set
    var oneCount by mutableStateOf(false)
    var oneCount2 by mutableStateOf(false)
    var contadorReady by mutableStateOf(false)
    var contadorReady2 by mutableStateOf(false)
    var aux by mutableStateOf(false)
    var resultState3 by mutableStateOf("")
        private set
    private var job : Job? = null

    fun fetchData() {
        job = viewModelScope.launch {
            for (i in 1..5) {
                delay(1000)
                countTime = i
            }
            oneCount = true
            contadorReady = true


            if (contadorReady) {
                job = viewModelScope.launch {
                    for (j in 1..5) {
                        delay(1000)
                        countTime2 = j
                    }
                    oneCount2 = true
                    contadorReady2 = true

                    if (contadorReady2){
                        viewModelScope.launch {
                            resultState2 = "Finalizó segundo contador"
                        }

                        if (oneCount2) {
                            job?.cancel()
                        }
                    }
                }
            }
        }
        if (aux){
            viewModelScope.launch {
                delay(5000)
                resultState = "Respuesta de la API de la web"

            }

            if (oneCount) {
                job?.cancel()
            }
        }
        aux = false
    }

    fun fetchData2(){
        countTime = 0
        countTime2 = 0
        contadorReady = false
        oneCount = false
        oneCount2 = false
        resultState = ""
        resultState2 = ""
        job?.cancel() // Cancela el job actual si hay alguno activo
        job = null // Limpia el job actual
        viewModelScope.launch {
            resultState3 = "Contador detenido"
            delay(3000)
            resultState3 = ""
        }
        aux = true
    }

}