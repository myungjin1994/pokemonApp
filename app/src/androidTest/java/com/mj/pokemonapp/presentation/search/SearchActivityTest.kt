package com.mj.pokemonapp.presentation.search

import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.mj.pokemonapp.R
import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.presentation.search.adapter.PokemonListAdapter
import com.mj.pokemonapp.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)


    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun insertSearchQueryAndClickItemTest() {
        onView(withId(R.id.edittext_search)).perform(typeText("pi"))

        onView(withId(R.id.recyclerview_pokemon_info))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PokemonListAdapter.PokemonViewHolder>(3, click()))
    }

    @Test
    fun checkPokemonDetailTest() {
        onView(withId(R.id.edittext_search)).perform(typeText("bulbasaur"))

        onView(withId(R.id.recyclerview_pokemon_info))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PokemonListAdapter.PokemonViewHolder>(0, click()))


        val expectedValue1 = PokemonName(1, "이상해씨", "이상해씨", "Bulbasaur")
        val expectedValue2 = PokemonDetail(1, 7, "Bulbasaur", "image_url_1", 69)

        onView(withId(R.id.textview_pokemon_name_korean)).check(matches(withText(expectedValue1.nameKorean)))
        onView(withId(R.id.textview_pokemon_name_english)).check(matches(withText(expectedValue1.nameEnglish)))

        val pokemonHeightText = "${expectedValue2.height} dm"
        val pokemonWeightText = "${expectedValue2.weight} hg"
        onView(withId(R.id.textview_pokemon_height)).check(matches(withText(pokemonHeightText)))
        onView(withId(R.id.textview_pokemon_weight)).check(matches(withText(pokemonWeightText)))
    }

    @Test
    fun checkPokemonLocationMapTest() {
        onView(withId(R.id.edittext_search)).perform(typeText("meowth"))

        onView(withId(R.id.recyclerview_pokemon_info))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PokemonListAdapter.PokemonViewHolder>(0, click()))

        onView(withId(R.id.button_location))
            .perform(click())

        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = mDevice.findObject(UiSelector().descriptionContains("lat/lng: (37.40125,127.11066)"))
        marker.click()
    }

}