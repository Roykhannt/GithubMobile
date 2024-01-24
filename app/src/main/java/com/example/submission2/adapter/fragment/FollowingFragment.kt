package com.example.submission2.adapter.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.activity.DetailUser
import com.example.submission2.adapter.UserAdapter
import com.example.submission2.databinding.FragmentFollowingBinding
import com.example.submission2.viewModel.FollowViewModel


class FollowingFragment : Fragment(R.layout.fragment_following) {

    private var  fllwingbinding : FragmentFollowingBinding? =null
    private val bindingfllwing get() = fllwingbinding!!
    private lateinit var FllwingViewModel : FollowViewModel
    private lateinit var fllwingadapter: UserAdapter
    private lateinit var uname:String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        uname = args?.getString(DetailUser.IE_UNAME).toString()

        fllwingbinding = FragmentFollowingBinding.bind(view)

        fllwingadapter= UserAdapter()
        fllwingadapter.notifyDataSetChanged()
        showTunggu(true)


        bindingfllwing.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager= LinearLayoutManager(activity)
            rvUser.adapter= fllwingadapter


        }
        FllwingViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java)
        FllwingViewModel.setListFollowing(uname)

        FllwingViewModel.getFollowing().observe(viewLifecycleOwner,{
            if(it!=null){
                fllwingadapter.setListView(it)
                showTunggu(false)

            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fllwingbinding = null
    }
    private fun showTunggu(status: Boolean){
        if(status){
            bindingfllwing?.pgFoller?.visibility= View.VISIBLE
        }else{
            bindingfllwing?.pgFoller?.visibility=View.GONE
        }
    }


}