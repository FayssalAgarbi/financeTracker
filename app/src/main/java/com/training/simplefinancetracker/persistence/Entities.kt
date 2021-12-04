package com.training.simplefinancetracker.persistence

import androidx.room.*
import java.util.*

@Entity
data class Category(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val description: String,
    val costType: CostType
)

@Entity
data class Expenditure(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val description: String,
    val categoryId: UUID,
    val cost: Double,
    val isPaid: Boolean
)

data class CategoryWithExpenditures(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val expenditure: Expenditure
)

enum class CostType {
    FIX, VARIABLE
}
