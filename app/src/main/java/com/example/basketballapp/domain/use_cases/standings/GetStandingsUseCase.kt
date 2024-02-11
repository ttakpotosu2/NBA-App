package com.example.basketballapp.domain.use_cases.standings

//class GetStandingsUseCase @Inject constructor(
//	private val repository: BasketballAppRepository
//) {
//
//	operator fun invoke(league: String, season: String): Flow<Resource<Standings>> = flow {
//		try {
//			emit(Resource.Loading())
//			val standings = repository.getStandings(league, season).toStandings()
//			emit(Resource.Success(standings))
//		} catch (e: HttpException){
//			emit(Resource.Error(e.localizedMessage ?: "An Error Occurred"))
//		} catch (e: IOException){
//			emit(Resource.Error("Check your internet connection"))
//		}
//	}
//}