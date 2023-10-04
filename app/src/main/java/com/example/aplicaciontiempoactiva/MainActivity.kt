package com.example.aplicaciontiempoactiva

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicaciontiempoactiva.ui.theme.AplicacionTiempoActivaTheme
import com.example.aplicaciontiempoactiva.ui.theme.AplicacionTiempoActivaTheme

val TAG = "Estado"
var startTime: Long = 0L
var totalTimeActive: Long = 0L
class MainActivity : ComponentActivity() {
    val TAG = "Estado"
    var startTime: Long = 0L
    var totalTimeActive: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                AplicacionTiempoActivaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting( totalTimeActive)

                }
            }
        }
    }

    fun updateUI(){
        var minutos = ((totalTimeActive / 60000).toInt())
        Toast.makeText(this, "La aplicació ha estado activa hasta ahora: ${totalTimeActive / 60000}min ${(totalTimeActive / 1000) - (60 * minutos)}s",Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Función onStart")
        startTime = System.currentTimeMillis()


    }

    override fun onPause() {
        var endTime: Long = System.currentTimeMillis()
        totalTimeActive =  endTime - startTime

        super.onPause()
        Log.d(TAG,"Función onPause, ha estado activa "+ totalTimeActive + " ms")
    }

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hola $name!" + "La aplicacion anterior ha estado activa: " + totalTimeActive ,
        modifier = modifier


    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplicacionTiempoActivaTheme {
        Greeting("Android")
    }
}