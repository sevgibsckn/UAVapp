package com.mone.myapplication

class sahteSoket {

    private var ilkVoltaj=16.5f
    private var ilkRakim =120
    private var ilkUcus=0

    fun randomTelemetriDegistir():telemetriVeri{

        ilkVoltaj-=0.01f
        ilkRakim+=(0..9).random()
        ilkUcus+=1

        val gpsX=41.0145+ilkUcus*0.001
        val gpsY=25.5268+ilkUcus*0.001
        val ucusSure=formatTime(ilkUcus)



        return telemetriVeri(ilkVoltaj,ilkRakim,String.format("%.6f", gpsX).toDouble(),String.format("%.6f", gpsY).toDouble(),ucusSure)

    }

    private fun formatTime(totalSeconds:Int):String{
        val hours=totalSeconds/3600
        val minutes=(totalSeconds%3600)/60
        val seconds=totalSeconds%60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }



}