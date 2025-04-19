package com.mone.myapplication

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
class WebSocketManager(private val activity: MainActivity) {

    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    // WebSocket bağlantısını başlat
    fun startWebSocket() {
        val request = Request.Builder().url("ws://10.0.2.2:8080")  // Emülatör için localhost
            .build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
                super.onOpen(webSocket, response)
                Log.d("WebSocket", "Bağlantı Açıldı")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.d("WebSocket", "Mesaj Alındı: $text")

                // WebSocket'ten alınan veriyi UI thread üzerinde işlemek için
                activity.runOnUiThread {
                    // Burada gelen JSON verisini telemetriVeri'ye dönüştürüp UI'yi güncelleyin
                    val telemetriData = parseTelemetriData(text)
                    activity.updateUI(telemetriData)
                }
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                Log.d("WebSocket", "Byte Message Alındı: ${bytes.hex()}")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
                super.onFailure(webSocket, t, response)
                Log.e("WebSocket", "Hata: ${t.message}")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Log.d("WebSocket", "Bağlantı Kapanıyor: $reason")
            }
        })
    }

    // WebSocket bağlantısını durdur
    fun stopWebSocket() {
        webSocket?.close(1000, "Normal closure")
        webSocket = null
    }

    // JSON string verisini telemetriVeri nesnesine dönüştürme
    private fun parseTelemetriData(jsonString: String): telemetriVeri {
        val gson = Gson()
        return gson.fromJson(jsonString, telemetriVeri::class.java)
    }
}