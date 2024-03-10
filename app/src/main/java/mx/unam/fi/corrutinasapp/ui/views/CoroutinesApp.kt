package mx.unam.fi.corrutinasapp.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.unam.fi.corrutinasapp.R
import mx.unam.fi.corrutinasapp.ui.theme.CorrutinasAppTheme
import mx.unam.fi.corrutinasapp.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(viewModel: MainViewModel = MainViewModel(), modifier: Modifier =Modifier){

    var changeColor by remember{
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ){
        Button(onClick = { changeColor = !changeColor },
            colors = ButtonDefaults.buttonColors(
                if(changeColor) Color.Magenta else Color.Green
            )
        ) {
            Text(text = stringResource(R.string.cambio_de_color))
        }

        Spacer(modifier = modifier.height(40.dp))
        Text(text = "${viewModel.countTime} [s]")
        Text(text = viewModel.resultState)

        Spacer(modifier = modifier.height(20.dp))
        Button(onClick = { viewModel.fetchData() }) {
            Text(text = stringResource(R.string.realizar_consulta))
        }

        Spacer(modifier = modifier.height(20.dp))
        Text(text = "${viewModel.countTime2} [s]")
        Text(text = viewModel.resultState2)

        Spacer(modifier = modifier.height(60.dp))
        Button(onClick = { viewModel.fetchData2() }) {
            Text(text = stringResource(R.string.cancelar_contador))
        }
        Spacer(modifier = modifier.height(20.dp))
        Text(text = viewModel.resultState3)

    }
}

@Preview
@Composable
fun CoroutinesAppPreview(){
    CorrutinasAppTheme{
        CoroutinesApp()
    }
}