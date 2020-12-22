package com.kylix.submissionbajp3.favorite

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
import com.kylix.submissionbajp3.ui.FavoriteFragment
import com.kylix.submissionbajp3.utils.EspressoIdlingResource
import com.kylix.submissionbajp3.ui.activities.FakeMainActivity
import com.kylix.submissionbajp3.ui.movie.MovieFragment
import com.kylix.submissionbajp3.ui.tvshow.TvShowFragment
import com.kylix.submissionbajp3.util.DummyData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("DEPRECATION")
class FavoriteFragmentTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<FakeMainActivity> = ActivityTestRule(
        FakeMainActivity::class.java)
    private val favoriteFragment = FavoriteFragment()
    private val movieFragment = MovieFragment()
    private val tvShowFragment = TvShowFragment()
    private val movie = DummyData.getMovie()
    private val tvShow = DummyData.getTvShow()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource())
    }

    @Test
    fun inputMovieFavorite() {
        activityRule.activity.setFragment(movieFragment)
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.rv_movie)).apply {
            pressBack()
        }
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        onView(withId(R.id.favorite_menu)).perform(click())
    }

    @Test
    fun inputTvShowFavorite() {
        activityRule.activity.setFragment(tvShowFragment)
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.rv_tvShow)).apply {
            pressBack()
        }
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        onView(withId(R.id.favorite_menu)).perform(click())
    }

    @Test
    fun validationMovieFavorite() {
        activityRule.activity.setFragment(favoriteFragment)
        onView(withId(R.id.rv_favorite_movie)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }
        onView(withId(R.id.tv_title)).check(matches(withText(movie[0].movieTitle)))
        onView(withId(R.id.rv_favorite_movie)).apply {
            pressBack()
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
            pressBack()
        }
    }

    @Test
    fun validationTvShowFavorite() {
        activityRule.activity.setFragment(favoriteFragment)
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_favorite_tv_show)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }
        onView(withId(R.id.tv_title)).check(matches(withText(tvShow[0].tvShowTitle)))
        onView(withId(R.id.rv_favorite_tv_show)).apply {
            pressBack()
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
            pressBack()
        }
    }
}