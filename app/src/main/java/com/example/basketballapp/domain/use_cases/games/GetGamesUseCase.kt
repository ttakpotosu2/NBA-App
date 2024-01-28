package com.example.basketballapp.domain.use_cases.games

import com.example.basketballapp.common.Resource
import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.repository.BasketballAppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
	private val repository: BasketballAppRepository
) {
	
	operator fun invoke(date: String): Flow<Resource<List<GameDetail>>> = flow {
		try {
			emit(Resource.Loading())
			val games = repository.getGames(date).toGames()
			emit(Resource.Success(games))
		} catch (e: retrofit2.HttpException){
			emit(Resource.Error(e.localizedMessage ?: "An Error Occurred"))
		} catch (e: IOException){
			emit(Resource.Error("Check your internet connection"))
		}
	}
}