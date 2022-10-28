package com.mehyo.marvelcharacters.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.data.DefaultObject
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

    private val comicsResult = MutableLiveData<Resource<List<DefaultObject>>>()
    val comicsResultLiveData: LiveData<Resource<List<DefaultObject>>> get() = comicsResult

    private val eventsResult = MutableLiveData<Resource<List<DefaultObject>>>()
    val eventsResultLiveData: LiveData<Resource<List<DefaultObject>>> get() = eventsResult

    private val storiesResult = MutableLiveData<Resource<List<DefaultObject>>>()
    val storiesResultLiveData: LiveData<Resource<List<DefaultObject>>> get() = storiesResult

    private val seriesResult = MutableLiveData<Resource<List<DefaultObject>>>()
    val seriesResultLiveData: LiveData<Resource<List<DefaultObject>>> get() = seriesResult

    /**
     * suspending function to get a character
     * from the server, then saves the details
     * of the character in a live data.
     */
    private suspend fun getCharacterById(id: Int) {
        characterDetailsResult.setLoading()
        try {
            repository.getCharacterById(id).body()?.let { baseResponse ->
                val character: Character = baseResponse.responseData.results[0]
                characterDetailsResult.setSuccess(character)
            }
        } catch (throwable: Throwable) {
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
     * suspending function to get Comics
     * from the server, then saves the list
     * of Comics in a live data.
     */
    private suspend fun getCharacterComicsById(id: Int) {
        comicsResult.setLoading()
        try {
            repository.getCharacterComicsById(id).body()?.let { baseResponse ->
                val comics: List<DefaultObject> = baseResponse.responseData.results
                comicsResult.setSuccess(comics)
            }
        } catch (throwable: Throwable) {
            comicsResult.setError(throwable.message, Exception(throwable))
        }
    }

    /**
     * function to run the getCharacterComicsById function
     * asynchronously using kotlin coroutines.
     */
    fun getCharacterComicsByIdAsync(id: Int) {
        launchAsync { getCharacterComicsById(id) }
    }


    /**
     * suspending function to get events
     * from the server, then saves the list
     * of events in a live data.
     */
    private suspend fun getCharacterEventsById(id: Int) {
        eventsResult.setLoading()
        try {
            repository.getCharacterEventsById(id).body()?.let { baseResponse ->
                val events: List<DefaultObject> = baseResponse.responseData.results
                eventsResult.setSuccess(events)
            }
        } catch (throwable: Throwable) {
            eventsResult.setError(throwable.message, Exception(throwable))
        }
    }

    /**
     * function to run the getCharacterEventsById function
     * asynchronously using kotlin coroutines.
     */
    fun getCharacterEventsByIdAsync(id: Int) {
        launchAsync { getCharacterEventsById(id) }
    }

    /**
     * suspending function to get stories
     * from the server, then saves the list
     * of stories in a live data.
     */
    private suspend fun getCharacterStoriesById(id: Int) {
        storiesResult.setLoading()
        try {
            repository.getCharacterStoriesById(id).body()?.let { baseResponse ->
                val stories: List<DefaultObject> = baseResponse.responseData.results
                storiesResult.setSuccess(stories)
            }
        } catch (throwable: Throwable) {
            storiesResult.setError(throwable.message, Exception(throwable))
        }
    }

    /**
     * function to run the getCharacterStoriesById function
     * asynchronously using kotlin coroutines.
     */
    fun getCharacterStoriesByIdAsync(id: Int) {
        launchAsync { getCharacterStoriesById(id) }
    }

    /**
     * suspending function to get series
     * from the server, then saves the list
     * of series in a live data.
     */
    private suspend fun getCharacterSeriesById(id: Int) {
        seriesResult.setLoading()
        try {
            repository.getCharacterSeriesById(id).body()?.let { baseResponse ->
                val series: List<DefaultObject> = baseResponse.responseData.results
                seriesResult.setSuccess(series)
            }
        } catch (throwable: Throwable) {
            seriesResult.setError(throwable.message, Exception(throwable))
        }
    }

    /**
     * function to run the getCharacterSeriesById function
     * asynchronously using kotlin coroutines.
     */
    fun getCharacterSeriesByIdAsync(id: Int) {
        launchAsync { getCharacterSeriesById(id) }
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