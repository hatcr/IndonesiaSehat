package com.nanostd.indonesiasehat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MulaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mulai)

        val btnMasuk = findViewById<ImageView>(R.id.iv_masuk)
        val btnDaftar = findViewById<ImageView>(R.id.iV_daftar)

        btnDaftar.setOnClickListener {
            val inten = Intent(this@MulaiActivity,Daftar1Activity::class.java)
            startActivity(inten)
            finish()
        }

        btnMasuk.setOnClickListener {
            val intent = Intent(this@MulaiActivity,MasukActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}