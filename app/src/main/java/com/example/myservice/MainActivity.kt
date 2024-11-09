package com.example.myservice

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //start service. service dijalankan dengan caera berikut
        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        binding.btnStartBackgroundService.setOnClickListener {
            //start service. service dijalankan dengan caera berikut
            startService(serviceIntent) //menjalnakan onStartCommand() pada Background service
        }
        binding.btnStopBackgroundService.setOnClickListener {
            stopService(serviceIntent) //stopService dipakai untuk mematikan service secara langsung dari luar kelas service,
        }

    }
}