package com.mehyo.marvelcharacters.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.network.Resource
import com.mehyo.marvelcharacters.repository.NetworkRepository
import com.mehyo.marvelcharacters.utils.setError
import com.mehyo.marvelcharacters.utils.setLoading
import com.mehyo.marvelcharacters.utils.setSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val repository: NetworkRepository
) : ViewModel() {

    private val characterDetailsResult = MutableLiveData<Resource<Character>>()
    val characterDetailsResultLiveData: LiveData<Resource<Character>> get() = characterDetailsResult

    /**
     * suspending function to get a character
     * from the server, then saves the details
     * of the character in a live data.
     */
    private suspend fun getCharacterById(id: Int) {
        characterDetailsResult.setLoading()
        Log.d("mehyos", "setLoading")
        try {
            repository.getCharacterById(id).body()?.let { baseResponse ->
                val character = baseResponse.responseData.results[0]
                Log.d("mehyos", "setSuccess")
                Log.d("mehyos", "$character")
                characterDetailsResult.setSuccess(character)
            }
        } catch (throwable: Throwable) {
            Log.d("mehyos", "setError")
            Log.d("mehyos", throwable.message.toString())
            characterDetailsResult.setError(throwable.message, Exception(throwable))
        }
    }

    /**
     * function to run the getCharacterById function
     * asynchronously using kotlin coroutines.
     */
    fun getCharacterByIdAsync(id: Int) {
        launchAsync { getCharacterById(id) }
    }

    /**
     * function to run the any suspending function
     * asynchronously using kotlin coroutines.
     */
    private fun launchAsync(method: suspend () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                method()
            }
        }
    }

}