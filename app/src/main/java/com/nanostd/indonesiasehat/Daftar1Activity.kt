package com.nanostd.indonesiasehat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nanostd.indonesiasehat.databinding.ActivityDaftar1Binding

class Daftar1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDaftar1Binding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftar1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseDatabase = FirebaseDatabase.getInstance()

        // nama database kalau mau nge akses
        databaseReference = firebaseDatabase.reference.child("users")


        binding.btnDaftar.setOnClickListener {
            val daftarEmail = binding.etEmail.text.toString()
            val daftarSandi = binding.etSandi.text.toString()
            val daftarNamaUser = binding.etNamaUser.text.toString()


            if (daftarEmail.isNotEmpty() && daftarSandi.isNotEmpty() && daftarNamaUser.isNotEmpty()) {
                daftarUser(daftarEmail, daftarSandi, daftarNamaUser)
            } else {
                Toast.makeText(this@Daftar1Activity, "Wajib di isi semua!!", Toast.LENGTH_SHORT).show()

            }
        }

        binding.ivSdhMasuk.setOnClickListener {
            startActivity(Intent(this@Daftar1Activity, MasukActivity::class.java))
            finish()
        }


        val btnSdhMasuk = findViewById<ImageView>(R.id.iv_sdhMasuk)
        btnSdhMasuk.setOnClickListener {
            val sdhMasuk = Intent(this@Daftar1Activity, MasukActivity::class.java )
            startActivity(sdhMasuk)
        }

        val btnKembali = findViewById<TextView>(R.id.tv_kembali)
        btnKembali.setOnClickListener {
            val kembali = Intent(this@Daftar1Activity, MulaiActivity::class.java )
            startActivity(kembali)
        }

        // testing to commit file changes on github
        val valueVar1 = 2
        val valueVar2 = 1
        val valueResult = valueVar1 + valueVar2
        println("Value Final Result: $valueResult")

    }

    private fun daftarUser(
        email: String,
        password: String,
        daftarNamaUser: String
    ){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()){
                    val id = databaseReference.push().key
                    val userData = id?.let { UserData(it,email, password,daftarNamaUser) }
                    databaseReference.child(id!!).setValue(userData)
                    Toast.makeText(this@Daftar1Activity, "Daftar Berhasil!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Daftar1Activity, MasukActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@Daftar1Activity, "User Telah Terdaftar", Toast.LENGTH_SHORT).show()

                }

                }
            override fun onCancelled(databaseError: DatabaseError){
                Toast.makeText(this@Daftar1Activity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()

            }
        })
    }

}
