package com.csci448.jspielman.samodelkin.ui.screens

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.csci448.jspielman.samodelkin.R
import com.csci448.jspielman.samodelkin.data.SamodelkinCharacter
import java.lang.reflect.Modifier

@Composable
private fun CharacterRow(character: SamodelkinCharacter, onSelectCharacter: (SamodelkinCharacter) -> Unit) {
    Card(modifier = Modifier.padding(16.dp )) {
        Column {
            Text(text = character.name)
        }
    }
    NewCharacterButton(text = Resources.getSystem().getString(R.string.save_to_codex_label), enabled = false, { onSaveCharacter(characterDataState.value) })
}