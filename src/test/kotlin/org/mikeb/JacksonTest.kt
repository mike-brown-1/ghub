package org.mikeb

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JacksonTest {

    @Test
    fun deserializeActionRun() {
        val mapper = jacksonObjectMapper()
        val json = """{ "name":"Build", "created_at":"2020-01-22T19:33:08Z" }"""

        val actionRun: ActionRun = mapper.readValue(json)
        println(actionRun.created)
        assertEquals("Build", actionRun.name)
    }
}