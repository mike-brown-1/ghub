package org.mikeb.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repo(
    val id: Long,
    val name: String,
    @JsonProperty("full_name")
    val fullName: String,
    val description: String,
    @JsonProperty("html_url")
    val htmlUrl: String,
    @JsonProperty("created_at")
    val createdAt: Date,
    @JsonProperty("updated_at")
    val updatedAt: Date,
    @JsonProperty("homepage")
    val homePage: String,
    @JsonProperty("stargazers_count")
    val stars: Int,
    val language: String,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class RepoSearchResults (
    @JsonProperty("total_count")
    var totalCount: Int = 0,
    @JsonProperty("incomplete_results")
    var incompleteResults: Boolean = false,
    var items: List<Repo> = mutableListOf<Repo>(),
    var successful: Boolean = false,
    var code: Int = 0,
    var body: String = ""
)
