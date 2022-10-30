package com.mehyo.marvelcharacters.ui.characters.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.repository.NetworkRepository
import retrofit2.HttpException
import java.io.IOException

class MarvelPagingSource(
    private val networkRepository: NetworkRepository
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 0
            val response = networkRepository.getCharacters(currentPage)
            val data = response.body()?.responseData?.results ?: emptyList()
            val responseData = mutableListOf<Character>()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 0) null else params.key?.minus(50),
                nextKey = currentPage.plus(50)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}