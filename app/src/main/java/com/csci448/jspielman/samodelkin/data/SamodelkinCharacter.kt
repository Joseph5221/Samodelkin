package com.csci448.jspielman.samodelkin.data

import java.util.*
import java.io.Serializable

class SamodelkinCharacter(val name: String, val race: String, val dex: String, val str: String, val wis: String) : Serializable  {
    val id = UUID.randomUUID()


}