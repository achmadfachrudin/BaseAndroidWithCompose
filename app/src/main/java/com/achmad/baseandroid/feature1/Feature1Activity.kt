package com.achmad.baseandroid.feature1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.achmad.baseandroid.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Feature1Activity : AppCompatActivity() {

    companion object {
        fun createIntent(
            context: Context,
        ): Intent {
            return Intent(context, Feature1Activity::class.java)
        }
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: Feature1ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_1)

        sharedPreferences.edit().putString("firstStoredString", "this is the content").apply()

        Toast.makeText(this, viewModel.coba, Toast.LENGTH_LONG).show()
    }
}
