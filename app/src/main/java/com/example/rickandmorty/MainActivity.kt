package com.example.rickandmorty


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rickandmorty.compose.theme.ComposeTrainingTheme
import com.example.rickandmorty.compose.theme.Gray1200
import com.example.rickandmorty.compose.RickAndMortyApp
import dagger.hilt.android.AndroidEntryPoint

@Keep
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrainingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Gray1200
                ) {
                    RickAndMortyApp()
                }
            }
        }
    }
}
