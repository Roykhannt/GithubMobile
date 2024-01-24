package com.example.submission2.adapter.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.activity.DetailUser
import com.example.submission2.adapter.UserAdapter
import com.example.submission2.databinding.FragmentFollowersBinding

import com.example.submission2.viewModel.FollowViewModel


class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var  follwrsbinding : FragmentFollowersBinding? =null
    private val bindingfollwrs get() = follwrsbinding!!
    private lateinit var follwrsViewModel : FollowViewModel
    private lateinit var follwrsAdapter: UserAdapter
    private lateinit var uname:String



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        uname = args?.getString(DetailUser.IE_UNAME).toString()
        follwrsbinding = FragmentFollowersBinding.bind(view)

        follwrsAdapter= UserAdapter()
        follwrsAdapter.notifyDataSetChanged()
        showTunggu(true)



        bindingfollwrs?.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager= LinearLayoutManager(activity)
            rvUser.adapter= follwrsAdapter

        }

        follwrsViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java)
        follwrsViewModel.setListFollowers(uname)

        follwrsViewModel.getFollowers().observe(viewLifecycleOwner,{
            if(it!=null){
                follwrsAdapter.setListView(it)
                showTunggu(false)

            }

        })

    }

    private fun showTunggu(status: Boolean){
        if(status){
            bindingfollwrs?.pgFolling?.visibility= View.VISIBLE
        }else{
            bindingfollwrs?.pgFolling?.visibility=View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        follwrsbinding = null
    }


}