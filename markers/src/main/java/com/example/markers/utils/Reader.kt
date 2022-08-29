package com.example.markers.utils

import android.content.Context
import com.example.markers.models.WiFiPoint
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Reader(var context: Context) {

    fun initiateReadFile() : List<WiFiPoint> {
        var items = mutableListOf<WiFiPoint>()
        var reader: CSVReader? = null
        try {
            reader = CSVReader(BufferedReader(InputStreamReader(
                context.getAssets().open("hotspots.csv"),
                "UTF-8")))
            var lines = reader.readAll()
            for(i in 0..5000){
                var line = lines[i]
                var id = line[1].toString()
                var lat = line[2].toDoubleOrNull()
                var lon = line[3].toDoubleOrNull()
                if(lat != null && lon != null){ items.add(WiFiPoint(id, lat, lon)) }
            }
        } catch (e: IOException) {
        } finally { reader?.close() }
        return items
    }
}