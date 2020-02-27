package id.asiatek.asiatrans.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.ui.login.LoginActivity
import com.wang.avi.AVLoadingIndicatorView

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var avi = findViewById<AVLoadingIndicatorView>(R.id.avi)
        avi.setIndicator("BallClipRotateMultipleIndicator")
        avi.show()

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
