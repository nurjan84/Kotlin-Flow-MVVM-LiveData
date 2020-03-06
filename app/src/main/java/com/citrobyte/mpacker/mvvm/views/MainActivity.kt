package com.citrobyte.mpacker.mvvm.views

import com.citrobyte.mpacker.mvvm.models.Result
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.citrobyte.mpacker.R
import com.citrobyte.mpacker.mvvm.viewmodels.MainActivityViewModel
import com.citrobyte.mpacker.mvvm.viewmodels.ViewModelProviderFactory
import com.citrobyte.mpacker.utils.Logger
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private val mainViewModel by lazy { ViewModelProvider(this, providerFactory).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getUser.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    Logger.i("loading $it")
                }
                is Result.Success -> {
                    Toast.makeText(this, "Success ${it.data}", Toast.LENGTH_SHORT).show()
                }
                is Result.Error-> {
                    Toast.makeText(this, "Error ${it.exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.getUserFlow.observe(this, Observer {
            Logger.i("on user flow =  ${it?.firstName}")
        })

        button.setOnClickListener {
            mainViewModel.saveNewUser()
        }

        button2.setOnClickListener {
            mainViewModel.deleteAllUsers()
        }

    }
}
