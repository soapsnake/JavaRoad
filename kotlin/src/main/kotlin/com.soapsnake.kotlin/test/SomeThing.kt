package main.kotlin.com.soapsnake.kotlin.test
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

fun main() {
    val jsonStr = "{\"columns\": [\"string1\", \"string2\", \"string3\"]}"
    val obj = parseJsonAwait<Some>(jsonStr)
    println(obj)
    println(obj.columns.javaClass)
}


inline fun <reified T> parseJsonAwait(deserialized: String): T {
    val objectMapper: ObjectMapper = ObjectMapper()
//    .registerModule(JavaTimeModule()
//        .addSerializer(ZonedDateTimeSerializer(OPP_TIMESTAMP_FORMATTER))
//        .addDeserializer(ZonedDateTime::class.java, ZonedDateTimeDeserializer(OPP_TIMESTAMP_FORMATTER)
//        ))
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
   return objectMapper.readValue(deserialized, T::class.java)
}
