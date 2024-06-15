package com.nanostd.indonesiasehat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread(Runnable {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                // Sembunyikan logo aplikasi dan animasi loadin

                    // Pindah ke halaman selanjutnya
                    val intent = Intent(this, MulaiActivity::class.java)
                    startActivity(intent)
                }
            }
        ).start()

    }
}