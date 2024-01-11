package com.example.basketballapp.domain.use_cases.games

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.basketballapp.common.Resource
import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.repository.BasketballAppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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
		} catch (e: HttpException){
			emit(Resource.Error("Check your internet connection"))
		}
	}
}