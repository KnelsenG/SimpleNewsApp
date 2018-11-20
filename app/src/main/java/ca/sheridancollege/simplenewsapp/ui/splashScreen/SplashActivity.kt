package ca.sheridancollege.simplenewsapp.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import ca.sheridancollege.simplenewsapp.base.BaseActivity
import ca.sheridancollege.simplenewsapp.ui.mainActivity.MainActivity

class SplashActivity : BaseActivity() {

//    private lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashActivityViewModel::class.java)
//        initObservables()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

//    private fun initObservables() {
//        viewModel.token.observe(this, Observer {
//            if (it.isBlank() || it.isEmpty()){
//                viewModel.getNewToken()
//            } else {
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }
//        })
//
//        viewModel.newTokenStatus.observe(this, Observer {
//            when (it){
//                is DataStatus.Loading -> {}
//                is DataStatus.Success -> {}
//                is DataStatus.Error<*> -> viewModel.getNewTokenError(it.data)
//            }
//        })
//
//        viewModel.alertError.observe(this, Observer {
//            AlertDialog.Builder(this)
//                    .setTitle("Oops")
//                    .setMessage("Unable to get API key")
//                    .setPositiveButton("Retry") { _, _ -> viewModel.getNewToken() }
//                    .setNegativeButton("Close") { _, _ -> finish() }
//                    .setCancelable(false)
//                    .show()
//        })
//
//        viewModel.alertErrorFinal.observe(this, Observer {
//            AlertDialog.Builder(this)
//                    .setTitle("Error")
//                    .setMessage("Unable contact server. Please try again later")
//                    .setNegativeButton("Close") { _, _ -> finish() }
//                    .setCancelable(false)
//                    .show()
//        })
//    }
}
