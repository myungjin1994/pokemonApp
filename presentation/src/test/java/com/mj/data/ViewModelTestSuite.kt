package com.mj.data

import com.mj.data.detail.DetailViewModelTest
import com.mj.data.search.SearchViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DetailViewModelTest::class,
    SearchViewModelTest::class
)
class ViewModelTestSuite