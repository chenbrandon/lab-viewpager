package edu.uw.viewpager

import android.os.Bundle
import android.support.v4.app.*
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieSelectedListener {

    private lateinit var searchFragment: SearchFragment
    private lateinit var detailFragment: DetailFragment
    private lateinit var listFragment: ListFragment
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchFragment = SearchFragment.newInstance()
        viewPager = findViewById<ViewPager>(R.id.pager)
        pagerAdapter = MoviePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter


    }

    override fun onMovieSelected(movie: Movie) {
        Log.v(TAG, "Detail for $movie")
        detailFragment = DetailFragment.newInstance(movie)
        pagerAdapter!!.notifyDataSetChanged()
        viewPager!!.currentItem = 2 //hard-code the shift
    }

    private inner class MoviePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            //Hard-code the ordering as an example
            if (position == 0) return searchFragment
            if (position == 1) return detailFragment
            return if (position == 2) detailFragment else null
        }

        override fun getCount(): Int  {
            return if (listFragment == null) {
                1
            } else if (detailFragment == null) {
                2
            } else {
                3
            }
        }

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }
    }


    companion object {

        private val TAG = "MainActivity"
        val MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment"
        val MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment"
    }
}
