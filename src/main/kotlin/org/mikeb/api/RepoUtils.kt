package org.mikeb.api

fun searchPublicRepos(query: String) {
    val request = getRequest("search/repositories?q=$query")
    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            println("response body: ${response.body!!.string()}")
        } else {
            println("ERROR: $response")
        }
    }
}
