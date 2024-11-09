package com.example.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyBackgroundService : Service() {

    companion object {
        internal val TAG = MyBackgroundService::class.java.simpleName
    }
    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service dijalankan...");
        serviceScope.launch {
            for (i in 1..50) {
                delay(1000)
                Log.d(TAG, "Do Something $i")
            }
            stopSelf() //stopSelf() digunakan untuk menghentikan atau mematikan service dari dalam kelas service itu sendiri.
            Log.d(TAG, "Service dihentikan")
        }

        //Pada metode tersebut, kita menjalankan sebuah background process untuk melakukan simulasi proses yang sulit dan ia berjalan secara asynchronous.
        //
        //Kekurangan dari service tipe ini adalah ia tak menyediakan background thread d
        // i luar ui thread secara default. Jadi, tiada cara lainnya selain membuat thread secara sendiri.


        return START_STICKY // menandakan bila service tersebut dimatikan oleh sistem Android karena kekurangan memori, ia akan
    // diciptakan kembali jika sudah ada memori yang bisa digunakan. Sehingga, metode onStartCommand()
    // juga akan kembali dijalankan.
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "onDestroy: Service dihentikan")
    }
}