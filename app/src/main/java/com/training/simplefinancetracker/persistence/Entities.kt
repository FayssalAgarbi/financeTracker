package com.training.simplefinancetracker.persistence

import androidx.room.*
import java.util.*

/*
 * Should there be a clear distinction between a category and an Expenditure?
 * That way there would only ever be 2 layers. One Parent layer and one child layer
 * Instead one could have multi-layers, where everything is an expenditure which can be broken up
 * into even more sub-expenditures.
 * For example:
 * Level 1: Internet-purchases
 * Level 2: Bookstore A, Delivery Service B
 * Level 3 (B): Book 1, Book 2, Book 3
 */


/*
 * Maybe add the column "importance", where every purchase can be weighed on a scale.
 * At the end of the month a report could be made that says
 * e.g.: "You spent XXX € on unnecessary purchases, that's Y% of your monthly income,
 * better work on that"
 */
@Entity
data class Expenditure(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val label: String,
    val costType: CostType,
    val cost: Double,
    val isPaid: Boolean,
    val parentId: UUID
)


enum class CostType {
    FIX, VARIABLE
}
