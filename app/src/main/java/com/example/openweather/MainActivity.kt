package com.example.openweather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var resultTxt: TextView
    private val viewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTxt = findViewById(R.id.resultTxt)

        viewModel.weatherData.observe(this, Observer { data ->
            if (data != null) {
                Log.e("WEATHER", data.toString())
                resultTxt.text =
                    data.main.temp.toString() + " " + data.weather[0].main.toString() + " " + data.name
            }
        })

        viewModel.fetchWeatherData(
            lat = 49.24,
            lon = 28.45,
            apiKey = ""
        )
    }
}