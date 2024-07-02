package com.example.bmicalculater

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculater.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
         binding.cardView3.visibility = View.INVISIBLE


        binding.buttonId.setOnClickListener {



            val weight = binding.WeightEditId.text.toString()
            val height = binding.HeightEditText.text.toString()


            if (validityCheck(weight,height)){

                val bmi = weight.toFloat() / (height.toFloat() * height.toFloat())
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                bmiResult(bmi2digits)



            }




        }


    }



    private fun bmiResult(bmiInput: Float) {
        binding.resultTextId1.text = bmiInput.toString()
        // var resultText = binding.resultTextId2.text
        var color = 0
        var resultText = ""


        when {
            bmiInput < 16.0 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }

            bmiInput in 18.5..24.9 -> {
                resultText = "Normal"
                color = R.color.normal
            }

            bmiInput in 25.0..29.9 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }

            bmiInput in 30.0..40.0 -> {
                resultText = "Obese"
                color = R.color.obese
            }


        }

        binding.resultTextId2.setTextColor(ContextCompat.getColor(this,color))
        binding.resultTextId2.text = resultText

        if (binding.cardView3.visibility == View.INVISIBLE){
            binding.cardView3.visibility = View.VISIBLE
        }


    }

    private fun validityCheck (w:String?,h:String?) : Boolean {

        if (w.isNullOrEmpty()){
            Toast.makeText(this,"Please input Weight!",Toast.LENGTH_SHORT).show()
            return false
        }
        else if(h.isNullOrEmpty()){
            Toast.makeText(this,"Please input Height!",Toast.LENGTH_SHORT).show()
            return false
        }
        else{
             return true
        }
    }
}