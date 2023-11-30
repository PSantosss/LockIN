package com.example.lockin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import com.example.lockin.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
//    private lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var editTextFName: EditText = findViewById(R.id.et_Firstname)
        var editTextLName: EditText = findViewById(R.id.et_Lastname)
        var editTextEmail: EditText = findViewById(R.id.et_Email)
        var editTextUName: EditText = findViewById(R.id.et_username)
        var editTextPass: EditText = findViewById(R.id.et_password)
        var buttonReg: Button = findViewById(R.id.btn_submit)
        var buttonback: Button = findViewById(R.id.btn_back)

        buttonReg.setOnClickListener {

            val firstname = editTextFName.text.toString()
            val lastname = editTextLName.text.toString()
            val email = editTextEmail.text.toString()
            val username = editTextUName.text.toString()
            val password = editTextPass.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Accounts")
                var dataKey = databaseReference.push().getKey()
                var userDetails = UserDetails(firstname, lastname, email, username, password)
                databaseReference.child("UserDetails").child(dataKey.toString())
                    .setValue(userDetails).addOnSuccessListener {
                    Toast.makeText(this, "Success - ADD", Toast.LENGTH_SHORT).show()
                }
            }


        buttonback.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}