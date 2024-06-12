package com.example.myapplication

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.view.accessibility.AccessibilityEvent


class MyAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            // Check if the current app is Instagram
            val packageName = event.packageName.toString()
            if ("com.instagram.android" == packageName) {
                // Check if the Reels tab is selected
                val rootNode = rootInActiveWindow
                if (rootNode != null) {
                    val reelsNodes = rootNode.findAccessibilityNodeInfosByText("Reels")
                    if (!reelsNodes.isEmpty()) {
                        for (node in reelsNodes) {
                            if (node.isSelected) {
                                showPopup()
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onInterrupt() {
        // Handle interruption
    }

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        info.notificationTimeout = 100
        info.packageNames = arrayOf("com.instagram.android")
        setServiceInfo(info)
    }

    private fun showPopup() {
        // Launch a popup intent here
        val intent = Intent(this, PopupActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}

