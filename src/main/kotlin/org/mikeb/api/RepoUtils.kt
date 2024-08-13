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
    var results = getRepoSearchResults(query)
    while (results.nextPageUrl != null) {
        val nextPage = getRepoSearchResults(results.nextPageUrl!!)
        results.items.addAll(nextPage.items)
        results.nextPageUrl = nextPage.nextPageUrl
    }
    return results
}

fun getRepoSearchResults(query: String): RepoSearchResults {
    val request = getRequest("search/repositories?q=$query")
    var results: RepoSearchResults
    client.newCall(request).execute().use { response ->
        val body = response.body!!.string()
        if (response.isSuccessful) {
            logger.trace { "headers: ${response.headers}" }
            logger.trace { "searchPublicRepos response: ${body}" }
            results = mapper.readValue(body)
            results.successful = true
            results.code = response.code
            results.nextPageUrl = getNextPageUrl(response.header("link"))
        } else {
            logger.error {"ERROR: $response"}
            results = RepoSearchResults(code = response.code, successful = false, body = body)
        }
    }
    return results
}

val nextPagePattern = """<(.*?)>;\s*rel="next"""".toRegex()

private fun getNextPageUrl(data: String?): String? {
    var result: String? = null

    if (data != null) {
        val matchResult = nextPagePattern.find(data)

        if (matchResult != null) {
            result = matchResult.groupValues[1]
        }
    }
    return result
}