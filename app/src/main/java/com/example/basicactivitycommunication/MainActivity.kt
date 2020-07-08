package com.example.basicactivitycommunication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun validate(view: View?){
        val name = inputName.text.toString()
        if(name.isNotEmpty()){
            val intent = Intent(this, Conditions::class.java)
            intent.putExtra("name", name)
            startActivityForResult(intent, CONDITIONS_INTENT_ID)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CONDITIONS_INTENT_ID && resultCode == Activity.RESULT_OK){
            val conditionsResult = data?.extras?.getString("conditionsResult")
            conditionsResultTextView.text = conditionsResult
        }
    }
}