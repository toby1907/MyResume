package com.example.myresume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.myresume.ui.profile.*
import com.example.myresume.ui.theme.MyResumeTheme
import com.example.voicejournal.ui.MyAppNavHost
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkmode by remember {
                mutableStateOf(false)
            }
            val profileModel: ProfileViewModel by viewModels()
            val scope = rememberCoroutineScope()
            LaunchedEffect(key1 = true){
                profileModel.stateFlow.collectLatest { event ->
                    when(event){
                        is ProfileViewModel.UiState.DarkMode ->{

                         darkmode=true
                        }
                        is ProfileViewModel.UiState.LightMode ->{
                           darkmode=false
                        }
                    }
                }
            }
            MyResumeTheme(darkmode) {

                // A surface container using the 'background' color from the theme
                MyAppNavHost(viewModel = profileModel)
            }
        }
    }
}

