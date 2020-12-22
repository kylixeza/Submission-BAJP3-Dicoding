package com.kylix.submissionbajp3.model.remote.response

data class TvShowModelResponse (
    val tvShowId: Int? = 0,
    val tvShowTitle: String? = "",
    val tvShowDescription: String? = "",
    val tvShowRelease: String? = "",
    val tvShowGenre : String? = "",
    val tvShowDuration: String? = "",
    val tvShowRating : String? = "",
    val tvShowPoster : String? = ""
)