package eu.robocab.habical

import eu.robocab.habical.habitica.Challenge
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.serializer
import java.time.LocalDate
import java.time.ZonedDateTime
import kotlin.reflect.KClass

object ZonedDateTimeAsStringSerializer : KSerializer<ZonedDateTime> {
    override val descriptor =
        PrimitiveSerialDescriptor("ZonedDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ZonedDateTime =
        ZonedDateTime.parse(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: ZonedDateTime) =
        encoder.encodeString(value.toString())
}

object LocalDateAsStringSerializer : KSerializer<LocalDate> {
    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate =
        LocalDate.parse(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: LocalDate) =
        encoder.encodeString(value.toString())
}

object NullableChallengeSerializer : NullableEmptyObjectSerializer<Challenge>(Challenge.serializer())

open class NullableEmptyObjectSerializer<T : Any>(serializer: KSerializer<T>) :
    JsonTransformingSerializer<T>(serializer) {

    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element is JsonNull || element is JsonObject && element.isEmpty()) {
            JsonNull
        } else {
            super.transformDeserialize(element)
        }
}
