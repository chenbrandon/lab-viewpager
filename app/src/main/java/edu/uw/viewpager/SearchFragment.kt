package edu.uw.viewpager

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import edu.uw.viewpager.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SearchFragment : Fragment() {

    private var callback: OnSearchListener? = null



    internal interface OnSearchListener {
        fun onSearchSubmitted(searchTerm: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        // Inflate the view
        val rootView = inflater!!.inflate(R.layout.fragment_search, container, false)

        // Add on click listener to button
        // ClickListener should be the callback on searchsubmitted
        val searchButton = rootView.findViewById<Button>(R.id.btn_search)
        searchButton.setOnClickListener {
            val text = rootView.findViewById<View>(R.id.txt_search) as EditText
            val searchTerm = text.text.toString()
            callback!!.onSearchSubmitted(searchTerm)
        }

        return rootView

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as OnSearchListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString() + " must implement OnSearchListener")
        }
    }


    companion object {
        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
