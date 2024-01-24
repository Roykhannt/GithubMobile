package com.example.submission2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.adapter.UserAdapter
import com.example.submission2.databinding.ActivityFavoriteUserBinding
import com.example.submission2.model.DataUser
import com.example.submission2.model.FavoriteUser
import com.example.submission2.viewModel.Favorite.FavoriteViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var FaUbinding: ActivityFavoriteUserBinding
    private lateinit var FaUadapter: UserAdapter
    private lateinit var FaUviewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FaUbinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(FaUbinding.root)
        FaUadapter = UserAdapter()
        FaUadapter.notifyDataSetChanged()
        setActionBarTitle()

        FaUviewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        FaUadapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: DataUser) {
                Intent(this@FavoriteUserActivity, DetailUser::class.java).also {
                    it.putExtra(DetailUser.IE_UNAME, data.username)
                    it.putExtra(DetailUser.IE_ID, data.id)
                    it.putExtra(DetailUser.IE_URL, data.avatar)
                    startActivity(it)
                }
            }
        })


        FaUbinding.apply {
            rvUserfav.setHasFixedSize(true)
            rvUserfav.layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            rvUserfav.adapter = FaUadapter
        }

        FaUviewModel.getFavorite()?.observe(this, {
            if (it != null) {
                val list = mappHelperList(it)
                FaUadapter.setListView(list)
            }

        })


    }

    private fun mappHelperList(users: List<FavoriteUser>): ArrayList<DataUser> {
        val listUser = ArrayList<DataUser>()
        for (user in users) {
            val uMapped = DataUser(
                user.id,
                user.login,
                user.avatar_url
            )
            listUser.add(uMapped)
        }
        return listUser
    }

    private fun setActionBarTitle() {
        if (supportActionBar != null) {
            supportActionBar?.title = "Favorite Users"
        }
    }

}