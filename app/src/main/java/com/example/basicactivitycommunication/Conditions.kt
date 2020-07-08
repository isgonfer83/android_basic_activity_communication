package com.example.basicactivitycommunication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.conditions.*

class Conditions : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conditions)
        val name = intent.extras?.getString("name")
        val prompt = getString(R.string.welcome) + " " + name + getString(R.string.accept_conditions)
        promptConditionsAcceptante.text = prompt
    }

    fun accept(view: View?){
        val intent = Intent()
        intent.putExtra("conditionsResult", "Conditions accepted! :)")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun refuse(view: View?){
        val intent = Intent()
        intent.putExtra("conditionsResult", "Conditions refused :(")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}