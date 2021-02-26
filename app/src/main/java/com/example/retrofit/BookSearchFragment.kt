package com.example.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapter.BookSearchResultsAdapter
import com.example.retrofit.model.VolumeResponse
import com.example.retrofit.viewmodels.BookSearchViewModel
import com.google.android.material.textfield.TextInputEditText


class BookSearchFragment : Fragment() {

    private lateinit var viewModel: BookSearchViewModel
    private lateinit var adapter: BookSearchResultsAdapter

    private lateinit var keywordEditText: TextInputEditText
    private lateinit var authorEditText:TextInputEditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = BookSearchResultsAdapter();

        viewModel = ViewModelProviders.of(this).get(BookSearchViewModel::class.java);
        viewModel.getVolumesResponseLiveData()!!.observe(this, object : Observer<VolumeResponse> {
            override fun onChanged(t: VolumeResponse?) {
                if (t != null) {
                    adapter.setResults(t.getItems())
                }
            }

        })
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_search, container, false)

        val recyclerview = view.findViewById<RecyclerView>(R.id.fragment_booksearch_searchResultsRecyclerView)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter

        keywordEditText = view.findViewById(R.id.fragment_booksearch_keyword);
        authorEditText = view.findViewById(R.id.fragment_booksearch_author);
        searchButton = view.findViewById<Button>(R.id.fragment_booksearch_search);

        searchButton.setOnClickListener {
            performSearch()
        }

        return view
    }

    private fun performSearch() {
        val keyword = keywordEditText.editableText.toString()
        val author = authorEditText.editableText.toString()

        viewModel.searchVolumes(keyword, author)
    }
}