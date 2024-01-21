package com.example.basketballapp.domain.use_cases.games

import com.example.basketballapp.common.Resource
import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.repository.BasketballAppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(
	private val repository: BasketballAppRepository
) {
	
	operator fun invoke(gameId: Int): Flow<Resource<GameDetail>> = flow {
		try {
			emit(Resource.Loading())
			val gameById = repository.getGameById(gameId).toGameDetail()
			emit(Resource.Success(gameById))
		} catch (e: HttpException){
			emit(Resource.Error(e.localizedMessage ?: "An Error Occurred"))
		} catch (e: IOException){
			emit(Resource.Error("Check your internet connection"))
		}
	}



}