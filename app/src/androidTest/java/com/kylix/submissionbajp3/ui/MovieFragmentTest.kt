package com.kylix.submissionbajp3.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.ui.activities.FakeMainActivity
import com.kylix.submissionbajp3.ui.movie.MovieFragment
import com.kylix.submissionbajp3.util.DummyData
import com.kylix.submissionbajp3.utils.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<FakeMainActivity> = ActivityTestRule(
        FakeMainActivity::class.java)
    private val favoriteFragment = MovieFragment()
    private val movie = DummyData.getMovie()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource())
        activityRule.activity.setFragment(favoriteFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        }
    }

    @Test
    fun movieBehaviour() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(withText(movie[0].movieTitle)))
        onView(withId(R.id.tv_release)).check(matches(withText(movie[0].movieRelease)))
        onView(withId(R.id.tv_duration)).check(matches(withText(movie[0].movieDuration)))
        onView(withId(R.id.tv_overview)).check(matches(withText(movie[0].movieDescription)))
        onView(withId(R.id.rv_movie)).apply {
            pressBack()
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        }
        onView(withId(R.id.tv_title)).check(matches(withText(movie[9].movieTitle)))
        onView(withId(R.id.tv_release)).check(matches(withText(movie[9].movieRelease)))
        onView(withId(R.id.tv_duration)).check(matches(withText(movie[9].movieDuration)))
        onView(withId(R.id.tv_overview)).check(matches(withText(movie[9].movieDescription)))
        onView(withId(R.id.rv_movie)).apply {
            pressBack()
        }
    }
}