package com.innowise.testcurrency.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innowise.testcurrency.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val RESULTS_REQUEST_CODE = "RESULTS_REQUEST_CODE"
        const val RESULT_KEY = "RESULT_KEY"
    }
}