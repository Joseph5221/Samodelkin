package com.csci448.jspielman.samodelkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.jspielman.samodelkin.ui.screens.PreviewCharacterDetailScreen
import com.csci448.jspielman.samodelkin.ui.theme.SamodelkinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent()
        }
    }
    @Composable
    private fun MainActivityContent() {
        SamodelkinTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                PreviewCharacterDetailScreen()
            }
        }
    }
    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    private fun PreviewMainActivity() {
        SamodelkinTheme {

        }
        MainActivityContent()
    }

    val name_label = resources.getString(R.string.name_label)
    val race_label = resources.getString(R.string.name_label)
    val stats_label = resources.getString(R.string.name_label)
    val dex_label = resources.getString(R.string.name_label)
    val str_label = resources.getString(R.string.name_label)
    val wis_label = resources.getString(R.string.name_label)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}



