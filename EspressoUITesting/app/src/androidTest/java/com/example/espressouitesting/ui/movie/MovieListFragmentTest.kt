package com.example.espressouitesting.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.espressouitesting.R
import com.example.espressouitesting.data.FakeMovieData
import com.example.espressouitesting.ui.movie.MoviesListAdapter.*
import com.example.espressouitesting.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get:Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.countingIdlingResource)
    }

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    /**
     * RecyclerView comes into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }


    /**
     * Select list item, nav to DetailFragment
     * Correct movie is in view?
     */
    @Test
    fun test_select_ListItem_isDetailFragmentVisible() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }


    /**
     * select list item, nav to DetailFragment
     * pressBack
     */
    @Test
    fun test_backNavigation_toMovieListFragment() {

        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))


    }

    /**
     * select list item, nav to DetailFragment
     * select directors textView, nav to DirectorsFragment
     */
    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_directiors)).perform(click())

        val directors = MOVIE_IN_TEST.directors
        val verifyDirectorsValue = DirectorsFragment.stringBuilderForDirectors(directors!!)

        onView(withId(R.id.directors_text))
            .check(matches(withText(verifyDirectorsValue)))
    }


    /**
     * select list item, nav to DetailFragment
     * select star actors textView, nav to StarActorsFragment
     */
    @Test
    fun test_navStarActorsFragment_validateDirectorsList() {
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        val actors = MOVIE_IN_TEST.star_actors
        val verifyActorsValue = DirectorsFragment.stringBuilderForDirectors(actors!!)

        onView(withId(R.id.star_actors_text))
            .check(matches(withText(verifyActorsValue)))
    }

}