package com.mone.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mone.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sahteSoket=sahteSoket()
    private val handler=Handler(Looper.getMainLooper())
    private val updateInterval=1000L
    private var isUpdating=true

    private val updateTask=object:Runnable{
        override fun run() {

            val veri = sahteSoket.randomTelemetriDegistir()



            binding.textView.text="Batarya: ${veri.batarya}"
            binding.textView2.text="Rakım:${veri.rakim}"
            binding.textView3.text="GPS: %.6f, %.6f".format(veri.gpsX, veri.gpsY)
            binding.textView4.text="Uçuş Süresi: ${veri.sure}"

            handler.postDelayed(this,updateInterval)


        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            if (!isUpdating) {
                handler.post(updateTask)
                isUpdating = true
                binding.button.isEnabled = false
                binding.button2.isEnabled = true
            }
        }

        binding.button2.setOnClickListener {
            if (isUpdating) {
                handler.removeCallbacks(updateTask)
                isUpdating = false
                binding.button.isEnabled = true
                binding.button2.isEnabled = false
            }

            binding.button.isEnabled = true
            binding.button2.isEnabled = false


        }


    }
}