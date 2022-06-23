package com.example.wave.ui.userdetails

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.wave.R
import com.example.wave.databinding.FragmentUserDetailsBinding
import com.example.wave.extensions.loadWithUri
import com.example.wave.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var views: FragmentUserDetailsBinding? = null

    private val args by navArgs<UserDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = FragmentUserDetailsBinding.bind(view)

        initUi()
        initListeners()
    }

    private fun initListeners() {
        views?.btnClose?.setOnClickListener {
            popBackStack()
        }
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }

    private fun initUi() {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        views?.image?.loadWithUri(args.user.picture?.large)

        val name = args.user.name?.getFullName()
        views?.tvName?.text = name
        val location = args.user.location?.getFullAddress()
        views?.tvLocation?.text = location
        views?.tvBirthdate?.text = DateUtils.formatDate(DateTime(args.user.dob?.date))
        views?.tvGender?.text = args.user.gender ?: ""
        views?.tvEmail?.text = args.user.email ?: ""
        views?.tvTitle?.text = args.user.name?.title ?: ""
    }
}