package com.kylix.submissionbajp3.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieModel (
    @PrimaryKey
    val movieId: Int? = 0,
    val movieTitle: String? = "",
    val movieDescription: String? = "",
    val movieRelease: String?= "",
    val movieGenre: String? = "",
    val movieDuration: String? = "",
    val movieRating : String?= "",
    val moviePoster : String?= "",
    var favorite: Boolean = false
)