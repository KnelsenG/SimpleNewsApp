package ca.sheridancollege.simplenewsapp.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import ca.sheridancollege.simplenewsapp.base.BaseActivity
import ca.sheridancollege.simplenewsapp.ui.mainActivity.MainActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
