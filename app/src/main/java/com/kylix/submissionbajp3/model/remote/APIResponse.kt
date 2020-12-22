package com.kylix.submissionbajp3.model.remote

import com.kylix.submissionbajp3.utils.StatusResponse

class APIResponse<T>(val statusResponse: StatusResponse, val body: T?, val message: String?) {

    companion object {
        fun <T> success(body: T?): APIResponse<T> {
            return APIResponse(
                StatusResponse.SUCCESS,
                body,
                null
            )
        }

        fun <T> error(msg: String, body: T?): APIResponse<T> {
            return APIResponse(
                StatusResponse.ERROR,
                body,
                msg
            )
        }
    }
}