package com.baseProject.android.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baseProject.android.data.Status
import com.baseProject.android.data.remote.model.responseModel.chat.ChannelsItem
import com.baseProject.android.data.remote.model.responseModel.usersForeign.UsersItem
import com.baseProject.android.databinding.FragmentChatBinding
import com.baseProject.android.ui.baseFragments.IdentifiedFragment
import com.baseProject.android.ui.error.ErrorDialogFragment
import com.baseProject.android.util.MessageMap
import com.baseProject.android.util.PrefManager

class ChatFragment : IdentifiedFragment(), ErrorDialogFragment.OnErrorActionListener {
    private lateinit var viewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ChatViewModel::class.java)
        binding.loadingObservable = viewModel.loading

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        subscribeGetUser()
    }

    private fun subscribe() {
        viewModel.response.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data?.channels != null) {
                        binding.isChatListEmpty = it.data.channels.isEmpty()
                        if (it.data.channels.isNotEmpty()) {
                            var ids = ""
                            val userID = PrefManager(requireContext()).userID
                            for (item in it.data.channels) {
                                if (userID != item?.idPartner)
                                    ids = ids + item?.idPartner.toString() + ","
                            }
                            viewModel.usersForeign(ids.removeRange(ids.length - 1, ids.length))
                        }
                    }
                }
                Status.ERROR, Status.SERVER_ERROR -> {
                    if (checkResponse(it))
                        return@observe
                    MessageMap.showMessage(requireActivity(), it.serverErrorCodes, requireContext())
                }
                Status.CONNECTION_ERROR -> {
                    ErrorDialogFragment.showError(childFragmentManager, it, this)
                }
                else -> true
            }

        }
    }

    private fun subscribeGetUser() {
        viewModel.usersResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val reversedList = viewModel.response.value?.data?.channels?.reversed()
                    setupList(reversedList!!, it.data?.users!!)
                }
                Status.ERROR, Status.SERVER_ERROR -> {
                    if (checkResponse(it))
                        return@observe
                    MessageMap.showMessage(requireActivity(), it.serverErrorCodes, requireContext())
                }
                Status.CONNECTION_ERROR -> {
                    ErrorDialogFragment.showError(childFragmentManager, it, this)
                }
                else -> true
            }

        }
    }

    private fun setupList(channels: List<ChannelsItem?>, users: List<UsersItem?>) {
        val reversedList = channels.reversed()
        val adapter = ChannelListAdapter(requireContext(), reversedList, users)
        binding.chatRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.chatRecyclerView.adapter = adapter
    }


    override fun onAuthenticated() {
        viewModel.getChatList()
    }

    override fun onUnAuthenticated() {
        redirectToLogin(requireContext())
    }

    override fun onErrorRetryButtonClick() {
        viewModel.getChatList()
    }

    override fun onErrorCancelButtonClick() {
    }
}