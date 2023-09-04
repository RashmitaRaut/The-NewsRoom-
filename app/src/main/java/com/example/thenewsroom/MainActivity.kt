package com.example.thenewsroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thenewsroom.ui.theme.TheNewsRoomTheme
import com.example.thenewsroom.ui.theme.news_screen.NewsScreen
import com.example.thenewsroom.ui.theme.news_screen.NewsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheNewsRoomTheme {
                val viewModel: NewsScreenViewModel = hiltViewModel()
                NewsScreen(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent
                )
            }

        }
    }
}

