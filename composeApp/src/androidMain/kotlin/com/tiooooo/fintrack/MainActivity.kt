package com.tiooooo.fintrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity() {

    private lateinit var activityScope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.close()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}