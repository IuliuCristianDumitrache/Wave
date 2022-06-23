package com.example.wave.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wave.R
import com.example.wave.databinding.FragmentUserListBinding
import com.example.wave.databinding.UserListItemBinding
import com.example.wave.extensions.disposeIfNotAlready
import com.example.wave.extensions.observe
import com.example.wave.models.Results
import com.example.wave.network.rxmessages.ReloadPeopleList
import com.example.wave.utils.RxBus
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import navigateSafely

@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.OnUserItemListener {

    private val viewModel: UsersViewModel by viewModels()
    private var views: FragmentUserListBinding? = null

    private var usersAdapter = UsersAdapter(this)
    private var reloadSubscriber: Disposable? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = FragmentUserListBinding.bind(view)

        initRecyclerView()

        observe(viewModel.usersList) { users ->
            usersAdapter.submitData(lifecycle, users)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            if (viewModel.usersList.value == null) {
                viewModel.getUsersList()
            }
        }

        initSubscriber()
    }

    private fun initSubscriber() {
        reloadSubscriber = RxBus.listen(ReloadPeopleList::class.java).subscribe {
            if (it.reload) {
                viewModel.getUsersList()
            }
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        views?.rvUsers?.layoutManager = linearLayoutManager
        views?.rvUsers?.adapter = usersAdapter
    }

    override fun onUserItemClicked(
        binding: UserListItemBinding,
        item: Results
    ) {
        val extras = FragmentNavigatorExtras(
            binding.image to "user_image_transition",
            binding.tvName to "user_name_transition",
            binding.tvLocation to "user_location_transition",
            binding.root to "user_root_transition"
        )
        val cameraLocationDirection = UsersFragmentDirections.actionUserFragmentToUserDetailsFragment(
            user = item
        )
        findNavController().navigateSafely(
            cameraLocationDirection, extras
        )
    }

    override fun onDestroy() {
        reloadSubscriber?.disposeIfNotAlready()
        super.onDestroy()
    }
}