package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            //change the title of the action bar
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            if(validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue*heightValue)
                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity, "Please enter valid values."
                , Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun displayBMIResult(bmi : Float){

        var bmiLabel: String = "fff"
        var bmiDescription: String = "fff"

        if(bmi.compareTo(15f) <=0){
            //Compares this object with the specified object for order.
            // Returns zero if this object is equal to the specified other object,
            // a negative number if it's less than other, or a positive number if it's greater than other.
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        }else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f) <=0){
            bmiLabel = "Severely Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        }else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f) <=0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        }else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f) <=0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulation! You are in good shape!"
        }else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f) <=0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Workout more"
        }else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f) <=0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Workout more"
        }else if(bmi.compareTo(35f)>0 && bmi.compareTo(40f) <=0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "You are in a very dangerous condition! Act now!"
        }else{
            bmiLabel = "Obese Class ||| (Very severely obese)"
            bmiDescription = "You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
            .toString()
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits():Boolean {
        var isValid = true
        if(binding?.etMetricUnitWeight?.text.toString().isEmpty())
            isValid = false
        else if(binding?.etMetricUnitHeight?.text.toString().isEmpty())
            isValid = false

        return isValid
    }
}