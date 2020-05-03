package com.leo.android.project.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.leo.android.project.databinding.FragmentGenreBinding
import com.leo.android.project.ui.base.BaseFragment

class GenreFragment : BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args: GenreFragmentArgs by navArgs()
        return FragmentGenreBinding.inflate(inflater).apply {
            accessCode = args.accessCode
        }.root
    }
}