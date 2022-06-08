package com.mj.pokemonapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
    }

    private fun observeData() {

        // 화면 회전시 현재 fragment를 파악해 다시 보여준다
        viewModel.currentFragmentTag.observe(this) {
            when (it) {
                DetailFragment.TAG -> showFragment(DetailFragment.newInstance(), DetailFragment.TAG)
                LocationFragment.TAG -> showFragment(LocationFragment.newInstance(), LocationFragment.TAG)
                else -> showFragment(DetailFragment.newInstance(), DetailFragment.TAG)
            }
        }
    }

    // fragment를 불러올때 생성되어 있는 fragment면 기존의 fragment를 보여준다.
    // 첫 생성이면 fragment를 생성하여 보여준다.
    private fun showFragment(fragment: Fragment, tag: String) {

        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction()
                .hide(it)
                .commitAllowingStateLoss()
        }

        findFragment?.let {
            supportFragmentManager.beginTransaction()
                .show(it)
                .commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

}