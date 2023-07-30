package main.kotlin.com.soapsnake.kotlin.test
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule


data class Person(
                @JsonInclude(JsonInclude.Include.NON_NULL)
                val name: String,
                  @JsonInclude(JsonInclude.Include.NON_NULL)
                  val age: Int?,
                  @JsonInclude(JsonInclude.Include.NON_NULL)
                    val address: String)
fun Person.filterFields(vararg fieldsToFilter: Set<String>): Person {
    val filteredFields = this::class.java.declaredFields.filter { field ->
        fieldsToFilter.any { it.contains( field.name) }
    }
    filteredFields.forEach { field ->
        field.isAccessible = true
        field.set(this, null)
    }
    return this
}

fun main() {
    val jsonStr = "{\"optionKey\":\"toto\",\"columns\":[\"rcash_point\",\"sec_able_rcash_point\",\"month_rcash_point_total\",\"max_rcash_point_month\",\"max_rcash_point_order\",\"min_point\"]}"
    val obj = parseJsonAwait<Some>(jsonStr)
    println(obj)
    println(obj.columns.javaClass)

    val person = Person("Alice", 25, "123 Main St")
    val filteredPerson = person.filterFields(setOf("name"))

    val xmlMapper = XmlMapper().registerKotlinModule()
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT)

    println(filteredPerson)
//    xmlMapper.setDefaultPropertyInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
    val xmlString = xmlMapper.writeValueAsString(filteredPerson)
    println(xmlString)
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

