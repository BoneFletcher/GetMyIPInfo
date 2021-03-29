package com.sdv.domain.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IpAddressDetail(
    @Json(name = "as")
    val asX: String,
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "countryCode")
    val countryCode: String,
    @Json(name = "isp")
    val isp: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "org")
    val org: String,
    @Json(name = "query")
    val query: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "regionName")
    val regionName: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "timezone")
    val timezone: String,
    @Json(name = "zip")
    val zip: String
)