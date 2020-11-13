package eu.robocab.habical.habitica

import java.time.LocalDate
import java.time.ZonedDateTime

enum class Attribute(attribute: String) {
    Strength("str"),
    Intelligence("int"),
    Perception("per"),
    Constitution("con"),
}

data class Task(
    val userId: String, // "b0413351-405f-416f-8787-947ec1c85199",
    val text: String, // "API Trial",
    val alias: String, // "apiTrial",
    val type: String, // "habit",
    val notes: String, // "",
    val tags: List<String>,
    val value: Double, // 11.996661122825959,
    val priority: Double, // 1.5,
    val attribute: Attribute, // "str",
    val challenge: Challenge,
    val reminders: List<Reminder>,
    val createdAt: ZonedDateTime, // "2017-01-12T19:03:33.495Z",
    val updatedAt: ZonedDateTime, // "2017-01-13T20:52:02.927Z",
    val history: List<DatedValue>,
    val down: Boolean,
    val up: Boolean,
    val id: String, // "2b774d70-ec8b-41c1-8967-eb6b13d962ba"
)

data class Challenge(
    val taskId: String, // "5f12bfba-da30-4733-ad01-9c42f9817975",
    val id: String, // "f23c12f2-5830-4f15-9c36-e17fd729a812"
)

data class Reminder(
    val id: String, // "ed427623-9a69-4aac-9852-13deb9c190c3",
    val startDate: LocalDate, //"1/16/17",
    val time: LocalDate, //"1/16/17"
)

typealias TimeInMillis = Int

data class DatedValue(
    val value: Double, // 1.9747
    val date: TimeInMillis, // 1484252965224
)
