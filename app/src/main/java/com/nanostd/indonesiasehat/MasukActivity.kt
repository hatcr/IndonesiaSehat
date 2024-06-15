package com.nanostd.indonesiasehat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nanostd.indonesiasehat.databinding.ActivityMasukBinding

class MasukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMasukBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.btnMasuk1.setOnClickListener {
            val masukEmail = binding.etEmail.text.toString()
            val masukSandi = binding.etSandi.text.toString()


            if (masukEmail.isNotEmpty() && masukSandi.isNotEmpty()) {
                masukUser(masukEmail, masukSandi)
            } else {
                Toast.makeText(this@MasukActivity, "Wajib di isi semua!!", Toast.LENGTH_SHORT).show()

            }
        }

        binding.ivBlmPunya.setOnClickListener {
            startActivity(Intent(this@MasukActivity, Daftar1Activity::class.java))
            finish()
        }


        val btnLupaPw = findViewById<TextView>(R.id.tv_lupaPw)
        btnLupaPw.setOnClickListener {
            val ganti = Intent(this@MasukActivity, HomeActivity::class.java )
            startActivity(ganti)
        }

        val btnMasuk = findViewById<ImageView>(R.id.btn_masuk1)
        btnMasuk.setOnClickListener {
            val masuk = Intent(this@MasukActivity, HomeActivity::class.java )
            startActivity(masuk)
        }

        val btnBlmPunya = findViewById<ImageView>(R.id.iv_blmPunya)
        btnBlmPunya.setOnClickListener {
            val daftar = Intent(this@MasukActivity, Daftar1Activity::class.java )
            startActivity(daftar)
        }

        val btnKembali = findViewById<TextView>(R.id.tv_kembali)
        btnKembali.setOnClickListener {
            val kembali = Intent(this@MasukActivity, MulaiActivity::class.java )
            startActivity(kembali)
        }

    }

    private fun masukUser(email: String, password: String){
        databaseReference.orderByChild(email).equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    for (userSnapshot in dataSnapshot.children){
                        val userData = userSnapshot.getValue(UserData::class.java)

                        if (userData != null && userData.password == password){
                            Toast.makeText(this@MasukActivity, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@MasukActivity, HomeActivity::class.java))
                            finish()
                            return
                        }
                    }
                }
                Toast.makeText(this@MasukActivity, "Login Gagal!", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MasukActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()

            }
        })
    }

}