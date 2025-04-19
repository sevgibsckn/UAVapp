package com.mone.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mone.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var webSocketManager: WebSocketManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // WebSocket sunucusunu başlat
        webSocketManager = WebSocketManager(this)

        // Start WebSocket connection
        binding.button.setOnClickListener {
            webSocketManager?.startWebSocket()  // WebSocket bağlantısını başlat
            binding.button.isEnabled = false
            binding.button2.isEnabled = true
            showToast("WebSocket Bağlantısı Başlatıldı")
        }

        // Stop WebSocket connection
        binding.button2.setOnClickListener {
            webSocketManager?.stopWebSocket()  // WebSocket bağlantısını durdur
            binding.button.isEnabled = true
            binding.button2.isEnabled = false
            showToast("WebSocket Bağlantısı Durduruldu")
        }
    }

    // Gelen veriyi UI üzerinde güncelle
    fun updateUI(telemetriData: telemetriVeri) {
        binding.textView.text = "Batarya: ${telemetriData.batarya}%"
        binding.textView2.text = "Rakım: ${telemetriData.rakim} m"
        binding.textView3.text = "GPS: %.6f, %.6f".format(telemetriData.gpsX, telemetriData.gpsY)
        binding.textView4.text = "Uçuş Süresi: ${telemetriData.sure} sn"
    }

    // Toast mesajı göster
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketManager?.stopWebSocket()  // Aktivite yok olduğunda WebSocket'i durdur
    }
}
