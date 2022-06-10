package com.mj.presentation

import com.mj.presentation.detail.DetailViewModelTest
import com.mj.presentation.search.SearchViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DetailViewModelTest::class,
    SearchViewModelTest::class
)
class ViewModelTestSuite