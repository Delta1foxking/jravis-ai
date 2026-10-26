package com.jarvis.ai
import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class JarvisAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
    fun extractKeywords(full: String): String {
        return full.substringAfter("search").trim()
    }
}