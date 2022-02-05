package `in`.aalap.gitxplorer

import android.graphics.BitmapShader
import android.graphics.Shader
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
        val navController = navHostFragment.navController

        paintTitle()

        registerNetworkCallBackListener()
    }

    private fun paintTitle() {
        val bitmapObj = AppCompatResources.getDrawable(this, R.drawable.ic_meteor)!!.toBitmap()
        val shaderObj: Shader =
            BitmapShader(bitmapObj, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        app_nam_toolbar.paint.shader = shaderObj
    }

    private fun registerNetworkCallBackListener() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val connectivityManager: ConnectivityManager = this.getSystemService(ConnectivityManager::class.java)
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    if (this@MainActivity::snackbar.isInitialized)
                        snackbar.dismiss()
                }

                override fun onLost(network: Network) {
                    snackbar = Snackbar.make(window.decorView.rootView, "Lost Internet", LENGTH_INDEFINITE)
                    snackbar.setBackgroundTint(ContextCompat.getColor(this@MainActivity, R.color.lightred))
                    snackbar.show()
                }
            })
        }
    }
}