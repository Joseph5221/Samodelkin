package com.csci448.jspielman.samodelkin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.csci448.jspielman.samodelkin.data.SamodelkinCharacter
import com.csci448.jspielman.samodelkin.util.CharacterGenerator
import com.csci448.jspielman.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import java.util.*

class PreviewSamodelkinCharacterViewModel private constructor() : ISamodelkinCharacterViewModel() {
    companion object {
        private var preview: PreviewSamodelkinCharacterViewModel?
                = PreviewSamodelkinCharacterViewModel()
        fun getInstance(): PreviewSamodelkinCharacterViewModel {
            var instance = preview
            if (instance == null) {
                instance = PreviewSamodelkinCharacterViewModel()
            }
            preview = instance
            return instance
        }
    }
    private val _characterListLiveData = MutableLiveData< MutableList<SamodelkinCharacter> >( mutableListOf() )
    private var _characterIdLiveData = MutableLiveData<UUID>()

    override val characterListLiveData: LiveData<List<SamodelkinCharacter>> =
        Transformations.map(_characterListLiveData) { characterList ->
            characterList
        }

    override val characterLiveData: LiveData<SamodelkinCharacter?> =    // override the viewmodel interface abstract member
        Transformations.map(_characterIdLiveData) { characterId ->  // transform the character ID live data, characterId is of type UUID
            var foundCharacter: SamodelkinCharacter? = null         // initially assume we haven't found a character
            _characterListLiveData.value?.let { characterList ->    // if the current value is not null - characterList is of type List<SamodelkinCharacter>
                for (character in characterList) {                  // loop through each SamodelkinCharacter
                    if (character.id == characterId) {              // compare IDs
                        foundCharacter = character                  // we found the character
                    }                                               // endif
                }                                                   // endfor
            }                                                       // endlet
            foundCharacter                                          // return what we found (for a lambda expression, the last line is the value returned)
        }                                                           // endmap

    override fun addCharacter(character: SamodelkinCharacter) {

    }

    override fun loadCharacter(id: UUID) {
        _characterIdLiveData.value = id
    }

    override fun generateRandomCharacter(): SamodelkinCharacter {
        return CharacterGenerator.generateRandomCharacter()
    }

    init {
        _characterListLiveData.value?.let { characterList ->
            for (i in 1..15) {
                characterList.add(generateRandomCharacter())
            }
            _characterListLiveData.value = characterList
        }
    }
}