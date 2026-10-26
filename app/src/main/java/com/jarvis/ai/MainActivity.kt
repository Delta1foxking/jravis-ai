package com.jarvis.ai
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val brain = JarvisBrain()
        setContent {
            var text by remember { mutableStateOf("") }
            var reply by remember { mutableStateOf("Hello Boss. JARVIS Online. No Google. Offline Ready.") }
            val scope = rememberCoroutineScope()
            Column(Modifier.padding(16.dp)) {
                Text(reply)
                Spacer(Modifier.height(16.dp))
                TextField(value=text, onValueChange={text=it}, label={Text("Talk to JARVIS")})
                Button(onClick={ scope.launch { reply = brain.think(text) } }) { Text("ASK JARVIS") }
            }
        }
    }
}