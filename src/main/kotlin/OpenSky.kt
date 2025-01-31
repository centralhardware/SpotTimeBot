package me.centralhardware.telegram

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

@Serializable
data class Flight(
    val icao24: String,
    val firstSeen: Long,
    val lastSeen: Long,
    val estDepartureAirport: String?,
    val estArrivalAirport: String?,
    val callsign: String?
)

object OpenSky {

    private val URL = "https://opensky-network.org/api/flights/departure"
    private val client = HttpClient(CIO)

    suspend fun  getFlight(iata: String): List<Flight> {
        val begin = LocalDateTime.now().minusDays(1).with(LocalTime.MIN).toEpochSecond(ZoneOffset.UTC)
        val end = LocalDateTime.now().minusDays(1).with(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC)
        val url = "$URL?airport=$iata&begin=$begin&end=$end"
        val response: String = client.get(url).body()
        return Json.decodeFromString(response)
    }

}