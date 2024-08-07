package org.mikeb

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import kotlin.test.Test

class OkHttpTest {

    val client = OkHttpClient()

    @Test
    fun genericGet() {
        val request = Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            println(response.body!!.string())
        }
    }
}