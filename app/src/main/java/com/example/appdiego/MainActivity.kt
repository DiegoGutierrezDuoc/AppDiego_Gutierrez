package com.example.appdiego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appdiego.ui.screen.PostScreen
import com.example.appdiego.viewmodel.PostViewModel
import com.example.appdiego.ui.theme.AppDiegoTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppDiegoTheme() {
                val postViewModel: PostViewModel = viewModel()
                PostScreen(viewModel = postViewModel)
            }
        }
    }
}