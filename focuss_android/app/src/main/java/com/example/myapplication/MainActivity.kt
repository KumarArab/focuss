package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check and request overlay permission
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName())
            )
            startActivityForResult(intent, REQUEST_CODE_OVERLAY_PERMISSION)
        } else {
            startAccessibilityService()
        }
    }


    fun onEnableServiceClicked(view: View?) {
        Log.d(TAG, "onEnableServiceClicked: Starting accessibility service")
        startAccessibilityService()
    }
    private fun startAccessibilityService() {
        // Direct user to enable the accessibility service
        val intent: Intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
        Toast.makeText(this, "Please enable the accessibility service", Toast.LENGTH_LONG).show()
    }

     @Deprecated("Deprecated in Java")
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_OVERLAY_PERMISSION) {
            if (Settings.canDrawOverlays(this)) {
                startAccessibilityService()
            } else {
                Toast.makeText(
                    this,
                    "Overlay permission is required for this app to work",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_OVERLAY_PERMISSION = 1234
    }
}