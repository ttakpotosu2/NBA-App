package com.example.basketballapp.domain.use_cases.standings

import com.example.basketballapp.common.Resource
import com.example.basketballapp.domain.model.Standings
import com.example.basketballapp.domain.repository.BasketballAppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStandingsUseCase @Inject constructor(
	private val repository: BasketballAppRepository
) {
	
	operator fun invoke(league: String, season: String): Flow<Resource<Standings>> = flow {
		try {
			emit(Resource.Loading())
			val standings = repository.getStandings(league, season).toStandings()
			emit(Resource.Success(standings))
		} catch (e: HttpException){
			emit(Resource.Error(e.localizedMessage ?: "An Error Occurred"))
		} catch (e: IOException){
			emit(Resource.Error("Check your internet connection"))
		}
	}
}