package com.kylix.submissionbajp3.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowModel (
    @PrimaryKey
    val tvShowId: Int? = 0,
    val tvShowTitle: String? = "",
    val tvShowDescription: String? = "",
    val tvShowRelease: String? = "",
    val tvShowGenre : String? = "",
    val tvShowDuration: String? = "",
    val tvShowRating : String? = "",
    val tvShowPoster : String? = "",
    var favorite: Boolean = false
)