package id.asiatek.asiatrans.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.ui.login.LoginActivity
import com.wang.avi.AVLoadingIndicatorView
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.ui.register.RegisterActivity
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var avi = findViewById<AVLoadingIndicatorView>(R.id.avi)
        avi.setIndicator("BallClipRotateMultipleIndicator")
        avi.show()

        Handler().postDelayed({
            if(SharedPref.getToken().isNotEmpty()) {
                finish()
                startActivity<MainTabActivity>()
            } else {
                finish()
                startActivity<RegisterActivity>()
            }
        }, SPLASH_TIME_OUT)
    }
}
