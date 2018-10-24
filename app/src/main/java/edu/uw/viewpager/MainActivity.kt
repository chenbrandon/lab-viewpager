package edu.uw.viewpager

import android.os.Bundle
import android.support.v4.app.*
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieSelectedListener {

    private lateinit var searchFragment: SearchFragment
    private lateinit var detailFragment: DetailFragment
    private lateinit var listFragment: ListFragment
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchFragment = SearchFragment.newInstance()
        viewPager = findViewById<ViewPager>(R.id.pager)



    }

    //respond to search button clicking
    fun handleSearchClick(v: View) {
        val text = findViewById<View>(R.id.txt_search) as EditText
        val searchTerm = text.text.toString()

        val fragment = MovieListFragment.newInstance(searchTerm)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, MOVIE_LIST_FRAGMENT_TAG)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onMovieSelected(movie: Movie) {
        val fragment = DetailFragment.newInstance(movie)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, MOVIE_DETAIL_FRAGMENT_TAG)
        ft.addToBackStack(null) //remember for the back button
        ft.commit()
    }
    private inner class MoviePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            //Hard-code the ordering as an example
            if (position == 0) return searchFragment
            if (position == 1) return detailFragment
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
