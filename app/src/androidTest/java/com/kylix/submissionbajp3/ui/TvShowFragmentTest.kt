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
import com.kylix.submissionbajp3.ui.tvshow.TvShowFragment
import com.kylix.submissionbajp3.util.DummyData
import com.kylix.submissionbajp3.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<FakeMainActivity> = ActivityTestRule(
        FakeMainActivity::class.java)
    private val favoriteFragment = TvShowFragment()
    private val tvShow = DummyData.getTvShow()

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
    fun loadTvShow() {
        onView(withId(R.id.rv_tvShow)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        }
    }

    @Test
    fun tvShowBehaviour() {
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(withText(tvShow[0].tvShowTitle)))
        onView(withId(R.id.tv_release)).check(matches(withText(tvShow[0].tvShowRelease)))
        onView(withId(R.id.tv_duration)).check(matches(withText(tvShow[0].tvShowDuration)))
        onView(withId(R.id.tv_overview)).check(matches(withText(tvShow[0].tvShowDescription)))
        onView(withId(R.id.rv_tvShow)).apply {
            pressBack()
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        }
        onView(withId(R.id.tv_title)).check(matches(withText(tvShow[9].tvShowTitle)))
        onView(withId(R.id.tv_release)).check(matches(withText(tvShow[9].tvShowRelease)))
        onView(withId(R.id.tv_duration)).check(matches(withText(tvShow[9].tvShowDuration)))
        onView(withId(R.id.tv_overview)).check(matches(withText(tvShow[9].tvShowDescription)))
        onView(withId(R.id.rv_tvShow)).apply {
            pressBack()
        }
    }
}