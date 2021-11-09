package com.itstep.weatherapp

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.itstep.weatherapp.databinding.ActivityMainBinding
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.itstep.weatherapp.view_model.WeatherViewModel
import java.lang.Math.round
import java.math.BigDecimal



class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private val cities = arrayOf("Nur-Sultan", "Almaty")
    private val cities_id = arrayOf("1526384", "1526273")

    private lateinit var citySelect: Spinner
    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //AppDatabase.buildDatabase(this)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        setCitySpinner()
    }

    fun setCitySpinner(){
        citySelect = binding.spinnerCity

        adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, cities)
        citySelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.getWeatherInfo(cities_id[position], BuildConfig.API_KEY)
                binding.textTemp.setText(viewModel.weatherItem.value?.main?.temp?.let { round(it.minus(273)).toString() })
                binding.textWeatherMain.setText(viewModel.weatherItem.value?.weather?.get(0)?.description)
                binding.textSpeed.setText(viewModel.weatherItem.value?.wind?.speed.toString())
                binding.textHumidity.setText(viewModel.weatherItem.value?.main?.humidity.toString())
                binding.textPressure.setText(viewModel.weatherItem.value?.main?.pressure.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        citySelect.setAdapter(adapter)

    }
}
