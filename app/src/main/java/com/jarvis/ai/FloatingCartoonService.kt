package com.jarvis.ai
import android.app.Service
import android.content.Intent
import android.os.IBinder

class FloatingCartoonService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
    fun openApp(name: String) {
        val pkg = when(name.lowercase()){
            "whatsapp"->"com.whatsapp"
            "youtube"->"com.google.android.youtube"
            "tiktok"->"com.zhiliaoapp.musically"
            else->"com.android.chrome"
        }
        val launch = packageManager.getLaunchIntentForPackage(pkg)
        launch?.let{ startActivity(it) }
    }
    fun searchYouTube(keywords: String){
        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://www.youtube.com/results?search_query=$keywords"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}