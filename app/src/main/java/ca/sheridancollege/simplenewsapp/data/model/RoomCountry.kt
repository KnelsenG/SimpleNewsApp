package ca.sheridancollege.simplenewsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCountry(
        @PrimaryKey(autoGenerate = false)
        var value: String,
        var display: String
) {
    companion object {
        val all = listOf(
                RoomCountry("AF", "Afghanistan"),
                RoomCountry("AS", "Australia"),
                RoomCountry("AT", "Austria"),
                RoomCountry("BD", "Bangladesh"),
                RoomCountry("BR", "Brazil"),
                RoomCountry("BW", "Botswana"),
                RoomCountry("CA", "Canada"),
                RoomCountry("CN", "China"),
                RoomCountry("FI", "Finland"),
                RoomCountry("FR", "France"),
                RoomCountry("DE", "German"),
                RoomCountry("HK", "Hong Kong"),
                RoomCountry("IN", "India"),
                RoomCountry("ID", "Indonesia"),
                RoomCountry("IE", "Ireland"),
                RoomCountry("IL", "Israel"),
                RoomCountry("IT", "Italy"),
                RoomCountry("JP", "Japan"),
                RoomCountry("LB", "Lebanon"),
                RoomCountry("MX", "Mexico"),
                RoomCountry("MY", "Malaysia"),
                RoomCountry("MM", "Myanmar"),
                RoomCountry("NL", ""),
                RoomCountry("NO", "Norway"),
                RoomCountry("NZ", "New Zealand"),
                RoomCountry("NG", "Nigeria"),
                RoomCountry("PH", "Philipines"),
                RoomCountry("PSE", "Palestine"),
                RoomCountry("PK", "Pakistan"),
                RoomCountry("PA", "Panama"),
                RoomCountry("PT", "Portugal"),
                RoomCountry("RU", "Russia"),
                RoomCountry("SA", "Saudi Arabia"),
                RoomCountry("SG", "Singapore"),
                RoomCountry("CH", "Switzerland"),
                RoomCountry("SK", "South Korea"),
                RoomCountry("SA", "South Africa"),
                RoomCountry("TW", "Taiwan"),
                RoomCountry("TH", "Thailand"),
                RoomCountry("TR", "Turkey"),
                RoomCountry("AE", "United Arabian Emirates"),
                RoomCountry("US", "United States"),
                RoomCountry("GB", "United Kingdom"),
                RoomCountry("VIET", "Vietnam"),
                RoomCountry("VEN", "Venezuela"),
                RoomCountry("ZW", "Zimbabwe")
        )
    }
}