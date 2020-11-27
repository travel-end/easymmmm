package ez.wind.wheatmusic.ui.act

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gyf.immersionbar.ImmersionBar
import ez.wind.wheatmusic.R

/**
 * @By Journey 2020/11/25
 * @Description
 */
class SplashActivity : AppCompatActivity() {
    private val mPermission = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    private val mPermissionCode = 0x122
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ImmersionBar.with(this).transparentBar().init()
        checkPermissions()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, mPermission,
                mPermissionCode
            )
        } else {
            window.decorView.postDelayed({
                toMain()
            }, 1000)
        }
    }

    private fun toMain() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == mPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toMain()
            } else {
                Toast.makeText(this, "拒绝该权限无法使用该程序", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}