package com.example.kotlinigclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kotlinigclone.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if ( currentUser != null){
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    fun signInClicked(view : View){

        var email = binding.editTexteMail.text.toString()
        var password = binding.editTextPassword.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Email and Passworld must be filled",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener { e->
                Toast.makeText(this@MainActivity,e.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }



    }

    fun signUpClicked(view : View){

        var email = binding.editTexteMail.text.toString()
        var password = binding.editTextPassword.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Email and Passworld must be filled",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener { task ->
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener { e->
                Toast.makeText(this@MainActivity,e.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

}