package com.example.a7minuteworkout
//for viewbinding ->
/* in build.gradle(module:app) in android{} add buildFeatures

buildFeatures{
    viewBinding true
}

also add to onDestroy() in end of main activity binding = null
*/


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null //binding object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //setup binding object using activity_main xml file
        setContentView(binding?.root) //use root of binding object i.e. constraint layout of activity_main file

        //val flStartButton : FrameLayout = findViewById(R.id.flStart) //no need to do this
        //now use id of containers in binding object to set func
        binding?.flStart?.setOnClickListener{
            val intent: Intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener {
            val intent = Intent(this@MainActivity , BMIActivity::class.java)
            startActivity(intent)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}