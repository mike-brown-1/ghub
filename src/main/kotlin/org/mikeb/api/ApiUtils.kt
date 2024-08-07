package org.mikeb.api

import okhttp3.OkHttpClient
import okhttp3.Request

val urlPrefix = "https://api.github.com/"
val ghToken = System.getenv("GH_TOKEN")
val client = OkHttpClient()

fun getRequest(urlSuffix: String): Request {
    println("ghToken: $ghToken")
    return Request.Builder()
        .url("$urlPrefix$urlSuffix")
        .header("User-Agent", "ghub")
        .addHeader("Accept", "application/vnd.github.v3+json")
        .build()
}