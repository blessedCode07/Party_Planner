package com.arkapp.partyplanner.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.arkapp.partyplanner.R
import com.arkapp.partyplanner.data.authentication.RC_SIGN_IN
import com.arkapp.partyplanner.data.repository.PrefRepository
import com.arkapp.partyplanner.utils.hide
import com.arkapp.partyplanner.utils.toast
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_splash.*

class MainActivity : AppCompatActivity() {

    private val prefRepository by lazy { PrefRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.optionsFragment, R.id.finalChecklistFragment)
            .build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        findNavController(R.id.fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment || destination.id == R.id.signupFragment)
                supportActionBar!!.hide()
            else
                supportActionBar!!.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                prefRepository.setLoggedIn(true)
                signUpBtn.hide()
            } else {
                toast(getString(R.string.login_failed))
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}