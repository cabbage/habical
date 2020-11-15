@file:UseSerializers(ZonedDateTimeAsStringSerializer::class, LocalDateAsStringSerializer::class)

package eu.robocab.habical.habitica

import eu.robocab.habical.LocalDateAsStringSerializer
import eu.robocab.habical.NullableChallengeSerializer
import eu.robocab.habical.ZonedDateTimeAsStringSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.time.ZonedDateTime

// TODO: add enum serializer for string value
@Serializable enum class Attribute { str, int, per, con, }

// API model description: https://habitica.com/apidoc/#api-Task-CreateUserTasks
@Serializable
data class Task(
    val id: String,
    val userId: String,
    val text: String,
    val alias: String? = null,
    val type: Type, // "habit",
    val notes: String? = null, // "",
    val tags: List<String> = emptyList(),
    val value: Double? = null, // Only valid for type "reward"
    val priority: Float, // 1.5, // TODO model value restriction: 0.1, 1, 1.5, 2
    val attribute: Attribute, // "str",
    val reminders: List<Reminder>,
    val createdAt: ZonedDateTime, // "2017-01-12T19:03:33.495Z",
    val updatedAt: ZonedDateTime, // "2017-01-13T20:52:02.927Z",
    val history: List<DatedValue> = emptyList(),
    val down: Boolean = true, // Only valid for type "habit"
    val up: Boolean = true, // Only valid for type "habit"
    val isDue: Boolean = false,
    val everyX: Int = 1, // Only valid for frequency "daily"
    val streak: Int = 0, // Only valid for type "daily"
    val frequency: Frequency? = null, // default "weekly"; only valid for type "daily"
    // TODO model for different repeat values
//    val repeat: String? = null, //  Only valid for type "daily". see API model desc.
) {
    @Serializable enum class Type { habit, daily, todo, reward, }
}

@Serializable enum class Frequency { daily, weekly, monthly, yearly, }

// TODO: Add nullable serializer for empty JSON objects to support [Challenge] property in [Task]
@Serializable
data class Challenge(
    val taskId: String, // "5f12bfba-da30-4733-ad01-9c42f9817975",
    val id: String, // "f23c12f2-5830-4f15-9c36-e17fd729a812"
)

@Serializable
data class Reminder(
    val id: String, // "ed427623-9a69-4aac-9852-13deb9c190c3",
    val startDate: LocalDate, //"1/16/17",
    val time: LocalDate, //"1/16/17"
)

typealias TimeInMillis = Long

@Serializable
data class DatedValue(
    val value: Double, // 1.9747
    val date: TimeInMillis, // 1484252965224
)
