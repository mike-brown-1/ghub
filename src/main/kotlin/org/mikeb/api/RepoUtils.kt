package org.mikeb.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.oshai.kotlinlogging.KotlinLogging
import org.mikeb.data.RepoSearchResults
import java.net.URLEncoder

private val logger = KotlinLogging.logger {}
private val mapper = jacksonObjectMapper()

fun searchPublicRepos(query: String): RepoSearchResults {
    logger.info { "searchPublicRepos, query:$query" }
    val encoded = URLEncoder.encode(query, "utf-8")
    val request = getRequest("search/repositories?q=$encoded&sort:stars")
    var results: RepoSearchResults
    client.newCall(request).execute().use { response ->
        val body = response.body!!.string()
        if (response.isSuccessful) {
            logger.info { "headers: ${response.headers}" }
            logger.trace { "searchPublicRepos response: ${body}" }
            results = mapper.readValue(body)
            results.successful = true
            results.code = response.code
        } else {
            logger.error {"ERROR: $response"}
            results = RepoSearchResults(code = response.code, successful = false, body = body)
        }
    }
    return results
}
