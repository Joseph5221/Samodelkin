package com.csci448.jspielman.samodelkin.ui.screens

import android.content.res.Configuration
import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.jspielman.samodelkin.R
import com.csci448.jspielman.samodelkin.data.SamodelkinCharacter
import com.csci448.jspielman.samodelkin.util.CharacterGenerator

@Composable
fun NewCharacterScreen(initialCharacter: SamodelkinCharacter,
                       onGenerateRandomCharacter: () -> SamodelkinCharacter,
                       onRequestApiCharacter: () -> SamodelkinCharacter,
                       onSaveCharacter: (SamodelkinCharacter) -> Unit
) {
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    if(portrait) {
        val characterDataState = rememberSaveable { mutableStateOf(initialCharacter) }
        Column() {
            CharacterCard(characterDataState)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box(modifier = Modifier.weight(0.5f)) {
                    GenerateRandomCharacterButton(
                        characterDataState = characterDataState,
                        onGenerateRandomCharacter = onGenerateRandomCharacter
                    )
                }
                Spacer(modifier = Modifier.width((16.dp)))
                Box(modifier = Modifier.weight(0.5f)) {
                    ApiCharacterButton(characterDataState, onRequestApiCharacter)
                }
            }
            Spacer(modifier = Modifier.height((16.dp)))
            SaveCharacterButton(characterDataState, onSaveCharacter)
        }
    } else {
        val characterDataState = rememberSaveable { mutableStateOf(initialCharacter) }
        Row(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.weight(0.5f)) {
                CharacterCard(characterDataState)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column (modifier = Modifier.weight(0.5f)) {
                GenerateRandomCharacterButton(
                    characterDataState = characterDataState,
                    onGenerateRandomCharacter = onGenerateRandomCharacter
                )
                Spacer(modifier = Modifier.height((16.dp)))
                ApiCharacterButton(characterDataState, onRequestApiCharacter)
                Spacer(modifier = Modifier.height((16.dp)))
                SaveCharacterButton(characterDataState, onSaveCharacter)
            }

        }
    }

}

@Composable
private fun SaveCharacterButton(characterDataState: MutableState<SamodelkinCharacter>, onSaveCharacter: (SamodelkinCharacter) -> Unit) {
    NewCharacterButton(text = Resources.getSystem().getString(R.string.save_to_codex_label), enabled = false, { onSaveCharacter(characterDataState.value) })
}

@Composable
private fun ApiCharacterButton(
    characterDataState: MutableState<SamodelkinCharacter>,
    onRequestApiCharacter: () -> SamodelkinCharacter
) {
    NewCharacterButton(Resources.getSystem().getString(R.string.api_label), false, { characterDataState.value = onRequestApiCharacter() })
}

@Composable
private fun GenerateRandomCharacterButton(
    characterDataState: MutableState<SamodelkinCharacter>,
    onGenerateRandomCharacter: () -> SamodelkinCharacter
) {

    NewCharacterButton(Resources.getSystem().getString(R.string.generate_new_random_label), true, { characterDataState.value = onGenerateRandomCharacter() })
}

@Composable
private fun NewCharacterButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button (modifier = Modifier.fillMaxWidth(), onClick = onClick, enabled = enabled) {
        Text(text=text, textAlign = TextAlign.Center)
    }
}


@Composable
private fun CharacterCard(character: MutableState<SamodelkinCharacter>) {
    Card {
        CharacterDetailScreen(character.value)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewCharacterScreen() {
    NewCharacterScreen(CharacterGenerator.placeHolderCharacter(),
        { CharacterGenerator.generateRandomCharacter() },
        { CharacterGenerator.generateRandomCharacter() },
        {})
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1024, heightDp = 720, showBackground = true)
@Composable
fun PreviewLandscapeNewCharacterScreen() {
    NewCharacterScreen(CharacterGenerator.placeHolderCharacter(),
        { CharacterGenerator.generateRandomCharacter() },
        { CharacterGenerator.generateRandomCharacter() },
        {})
}