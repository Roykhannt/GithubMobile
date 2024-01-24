package com.example.submission2.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.adapter.UserAdapter
import com.example.submission2.databinding.ActivityMainBinding
import com.example.submission2.model.DataUser
import com.example.submission2.viewModel.SettingPreferences
import com.example.submission2.viewModel.SettingViewModel
import com.example.submission2.viewModel.UserViewModel
import com.example.submission2.viewModel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private  lateinit var bindingMain : ActivityMainBinding
    private lateinit var MainviewModel: UserViewModel
    private lateinit var Mainadapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        vector(true)
        Mainadapter = UserAdapter()
        Mainadapter.notifyDataSetChanged()

        setActionBarTitle()

        Mainadapter.setOnItemClickCallBack(object :UserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: DataUser) {
                Intent(this@MainActivity, DetailUser::class.java).also {
                    it.putExtra(DetailUser.IE_UNAME, data.username)
                    it.putExtra(DetailUser.IE_ID, data.id)
                    it.putExtra(DetailUser.IE_URL, data.avatar)
                    startActivity(it)
                }
            }
        })
        MainviewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        MainviewModel.getCariUsers().observe(this,{
            if(it!=null){
                Mainadapter.setListView(it)
                showTunggu(false)
                vector(false)
            }
        })

        bindingMain?.apply {
            rvUser.layoutManager= LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter=Mainadapter
        }

        val btn = findViewById<Switch>(R.id.switch1)
        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    btn.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    btn.isChecked = false
                }
            })

        btn.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        val cariManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val tampilanCari = menu.findItem(R.id.act_search).actionView as SearchView

        tampilanCari.setSearchableInfo(cariManager.getSearchableInfo(componentName))
        tampilanCari.queryHint = resources.getString(R.string.search_hint)
        tampilanCari.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    showTunggu(true)
                    MainviewModel.setCariUsers(query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_to_fav -> {
                Intent(this, FavoriteUserActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun vector(state: Boolean){
        if(state){
            bindingMain?.vector2?.visibility= View.VISIBLE
            bindingMain?.tvvector2?.visibility= View.VISIBLE
        }else{
            bindingMain?.vector2?.visibility=View.GONE
            bindingMain?.tvvector2?.visibility= View.GONE
        }
    }

    private fun showTunggu(state: Boolean){
        if(state){
            bindingMain?.pgMainActivity?.visibility= View.VISIBLE
        }else{
            bindingMain?.pgMainActivity?.visibility=View.GONE
        }
    }

    private fun setActionBarTitle() {
        if (supportActionBar != null) {
            supportActionBar?.title = "Search Users"
        }
    }


}