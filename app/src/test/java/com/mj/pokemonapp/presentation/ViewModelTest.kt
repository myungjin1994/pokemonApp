package com.mj.pokemonapp.presentation

import com.mj.pokemonapp.presentation.detail.DetailViewModelTest
import com.mj.pokemonapp.presentation.search.SearchViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DetailViewModelTest::class,
    SearchViewModelTest::class
)
class ViewModelTest