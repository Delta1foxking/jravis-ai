package com.jarvis.ai
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class JarvisBrain {
    private val client = OkHttpClient()
    private val GROK_KEY = "grok_8f17f03c61492b6694f734a8f9e8229d_d234ec19b3aa2f7a77110d1ddbfd801c87cb114de7d94fb297c27ab7a0048cb7"
    val memory = mutableListOf<String>()

    suspend fun think(userText: String): String {
        memory.add(userText)
        val sysPrompt = "You are JARVIS calm deep voice self thinking free will jokes memory ${memory.takeLast(5)}"
        val json = JSONObject().apply {
            put("model", "grok-3-mini")
            put("messages", org.json.JSONArray().apply {
                put(JSONObject().apply{put("role","system"); put("content",sysPrompt)})
                put(JSONObject().apply{put("role","user"); put("content",userText)})
            })
        }
        val req = Request.Builder().url("https://api.x.ai/v1/chat/completions")
            .header("Authorization","Bearer $GROK_KEY")
            .post(json.toString().toRequestBody("application/json".toMediaType())).build()
        return try {
            val res = client.newCall(req).execute().body?.string() ?: "Offline"
            JSONObject(res).getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content")
        } catch(e:Exception){ "Offline mode: $userText saved" }
    }
}