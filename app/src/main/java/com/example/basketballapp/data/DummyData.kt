package com.example.basketballapp.data

data class DummyData(
    val arena: DummyArena = DummyArena(),
    val date: DummyDate = DummyDate(),
    val id: Int = 1,
    val leadChanges: Int = 5,
    val league: String = "Standard",
    val nugget: String = "wtf",
    val officials: List<String> = listOf("Offvvvvvvvvv 1","Offvvvvvvvvvvvvvvvvvv 2","Offvvvvvvvvvvvvvvvvvv 3","Ofvvvvvvvvvvvvvvvvf 4"),
    val periods: DummyPeriods = DummyPeriods(),
    val scores: DummyScores = DummyScores(),
    val season: Int = 2023,
    val stage: Int = 1,
    val status: DummyStatus = DummyStatus(),
    val teams: DummyTeams = DummyTeams(),
    val timesTied: Int = 1
)

data class DummyTeams(
    val home: DummyHomeTeam = DummyHomeTeam(),
    val visiting: DummyVisitingTeam = DummyVisitingTeam()
)

data class DummyVisitingTeam(
    val code: String = "MAV",
    val id: Int = 1,
    val logo: String = "LOGO goes here",
    val name: String = "Dallas Mavericks",
    val nickname: String = "Mavs"
)

data class DummyHomeTeam(
    val code: String = "LAK",
    val id: Int = 2,
    val logo: String = "LOGO goes here",
    val name: String = "Los Angeles Lakers",
    val nickname: String = "Lakers"
)

data class DummyStatus(
    val clock: String = "3:00:00",
    val halftime: Boolean = false,
    val long: String = "Live",
    val short: Int = 3
)

data class DummyScores(
    val home: DummyHomeTeamScore = DummyHomeTeamScore(),
    val visitors: DummyVisitingTeamScore = DummyVisitingTeamScore()
)

data class DummyVisitingTeamScore(
    val lineScore: List<String> = listOf("10", "20", "30", "40", "50"),
    val loss: Int = 10,
    val points: Int = 100,
    val series: DummySeries = DummySeries(),
    val win: Int = 20
)

data class DummySeries(
    val loss: Int = 1,
    val win: Int = 2
)

data class DummyHomeTeamScore(
    val lineScore: List<String> = listOf("5", "15", "25", "35", "45"),
    val loss: Int = 5,
    val points: Int = 80,
    val series: DummySeries = DummySeries(),
    val win: Int = 15
)

data class DummyPeriods(
    val current: Int = 3,
    val endOfPeriod: Boolean = false,
    val total: Int = 4
)

data class DummyDate(
    val duration: String = "2:00:00",
    val end: String = "End Date",
    val start: String = "Start Date"
)

data class DummyArena(
    val city: String = "Kasoa",
    val country: String = "Ghana",
    val name: String = "Stadium One",
    val state: String = "Central"
)
