package com.training.simplefinancetracker.persistence

import androidx.room.TypeConverter
import java.util.*

class UuidTypeConverter {

    @TypeConverter
    fun toUuid(value: String?): UUID? = if (value == null) null else UUID.fromString(value)


    @TypeConverter
    fun toString(value: UUID?): String? = value?.toString()
}