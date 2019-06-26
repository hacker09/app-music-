package com.example.musicfinal.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicfinal.R
import com.example.musicfinal.ui.fragment.listener.LibraryFragmentActionListener
import com.example.musicfinal.ui.recyclerview.adapter.LibraryAdapter
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {
    private lateinit var listener: LibraryFragmentActionListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is LibraryFragmentActionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement LibraryFragmentActionListener")
        }
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = LibraryAdapter(context, listener)
    }
}
