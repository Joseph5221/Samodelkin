package com.csci448.jspielman.samodelkin.ui.screens

import android.app.PendingIntent.getActivity
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getText
import com.csci448.jspielman.samodelkin.MainActivity
import com.csci448.jspielman.samodelkin.R
import com.csci448.jspielman.samodelkin.data.SamodelkinCharacter
import com.csci448.jspielman.samodelkin.util.CharacterGenerator


@Composable
fun CharacterDetailScreen(character: SamodelkinCharacter) {
    Column(modifier = Modifier.padding(16.dp)) {
        NameSection(character.name)
        Spacer(modifier = Modifier.padding(16.dp))
        RaceSection(character.race)
        Spacer(modifier = Modifier.padding(16.dp))
        StatsSection(dex = character.dex, str = character.str, wis = character.wis)
    }
}

@Composable
private fun RaceSection(race: String) {
    Column() {
        SectionHeader(title = Resources.getSystem().getString(R.string.race_label))
        Text(text = race)
    }
}

@Composable
private fun NameSection(name: String) {

    var titleString = Resources.getSystem().getString(R.string.name_label)
    Log.d("tag: ", titleString)
    Column() {
        SectionHeader(title = Resources.getSystem().getString(R.string.name_label))
        Text(text = name)
    }
}

@Composable
private fun StatsDisplay(name: String, value: String) {
    Row() {
        Text(text = name,
                modifier=Modifier.weight(1f))
        Text(text = value,
                modifier=Modifier.weight(1f))
    }
}

@Composable
private fun SectionHeader(title: String) {
    Column() {
        Text(text = title,
                fontSize = 24.sp)
        Divider(thickness = 2.dp)
    }
}

@Composable
private fun StatsSection(dex: String, str: String, wis: String) {
    Column() {
        SectionHeader(title = Resources.getSystem().getString(R.string.stats_label))
        StatsDisplay(name = Resources.getSystem().getString(R.string.dex_label), value=dex)
        StatsDisplay(name = Resources.getSystem().getString(R.string.str_label), value=str)
        StatsDisplay(name = Resources.getSystem().getString(R.string.wis_label), value=wis)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterDetailScreen() {
    //StatsDisplay(name = "Testing", value = "123")
    //SectionHeader(title = Resources.getSystem().getString(R.string.stats_label))
    //StatsSection("12", "14", "16")
    //RaceSection("Still Testing")
    //NameSection("One More Test")
    CharacterDetailScreen(character = CharacterGenerator.generateRandomCharacter())
}