package org.mikeb.org.mikeb

import io.github.oshai.kotlinlogging.KotlinLogging
import org.mikeb.api.searchPublicRepos

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info {"ghub starting"}
    val results = searchPublicRepos("configuration language:java language:kotlin stars:>=500 sort:stars")
    logger.info { "successful: ${results.successful}, total count: ${results.totalCount}, " +
            "list size: ${results.items.size}"}

    results.items.forEach { repo ->
        println("name: ${repo.fullName}, language: ${repo.language}, stars: ${repo.stars}, updated: ${repo.updatedAt}")
        println("    ${repo.description}, ")
    }
}
