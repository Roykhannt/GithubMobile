package com.example.submission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission2.adapter.SectionAdapter
import com.example.submission2.databinding.ActivityDetailUserBinding
import com.example.submission2.viewModel.DetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUser : AppCompatActivity() {

    companion object{
        const val IE_UNAME= "extra_username"
        const val IE_ID = "extra_id"
        const val IE_URL = "extra_url"
    }
    private lateinit var bindingdet: ActivityDetailUserBinding
    private lateinit var DetailviewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingdet = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(bindingdet.root)
        setActionBarTitle()


        val namaLengkap = intent.getStringExtra(IE_UNAME)?: ""
        val id = intent.getIntExtra(IE_ID, 0)
        val Url = intent.getStringExtra(IE_URL) ?: ""
        val bundleDetail = Bundle()
        bundleDetail.putString(IE_UNAME, namaLengkap)

        DetailviewModel=ViewModelProvider(this).get(DetailViewModel::class.java)
        DetailviewModel.setUserDetail(namaLengkap)

        DetailviewModel.getUserDetail().observe(this,{
            if(it != null){
                bindingdet?.apply {
                    tvDetailName.text=it.fullname
                    tvDetailUsername.text=it.username
                    tvDetailCompany.text=it.company
                    tvDetailLocation.text=it.location
                    tvDetailNumberOfRepos.text=it.repository
                    tvDetailNumberOfFollowing.text= it.following
                    tvDetailNumberOfFollowers.text=it.followers
                    Glide.with(this@DetailUser)
                        .load(it.avatar)
                        .centerCrop()
                        .into(ivDetailAvatar)
                    showTunggu(false)
                }
            }
        })

        val sectionAdapter = SectionAdapter(this,supportFragmentManager, bundleDetail)
        bindingdet?.apply {
            viewPager.adapter= sectionAdapter
            tabs.setupWithViewPager(viewPager)
        }

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = DetailviewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        bindingdet.favTg.isChecked = true
                        _isChecked = true
                    } else {
                        bindingdet.favTg.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        bindingdet.favTg.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
                DetailviewModel.tambahFav(namaLengkap, id, Url)
            } else {
                DetailviewModel.hapusFavorite(id)
            }
            bindingdet.favTg.isChecked = _isChecked
        }
    }
    private fun showTunggu(status: Boolean){
        if(status){
            bindingdet?.pgDetail.visibility= View.VISIBLE
        }else{
            bindingdet?.pgDetail.visibility= View.GONE
        }
    }

    private fun setActionBarTitle() {
        if (supportActionBar != null) {
            supportActionBar?.title = "Detail User"
        }
    }
}
