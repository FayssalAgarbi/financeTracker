package com.training.simplefinancetracker.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.training.simplefinancetracker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}