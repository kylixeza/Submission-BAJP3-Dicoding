package com.kylix.submissionbajp3.utils

import java.util.concurrent.Executor

class InstantAppExecutors: AppExecutors(instant, instant) {
    companion object {
        private val instant = Executor{ it.run() }
    }
}