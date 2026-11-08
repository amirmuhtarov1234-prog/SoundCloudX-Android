package com.soundcloudx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF101010)
private val Panel = Color(0xFF1D1D1D)
private val Accent = Color(0xFFFF5500)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SoundCloudXApp() }
    }
}

@Composable
fun SoundCloudXApp() {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("Поиск") }
    MaterialTheme(colorScheme = darkColorScheme(primary = Accent, background = Ink, surface = Panel)) {
        Scaffold(containerColor = Ink, bottomBar = {
            NavigationBar(containerColor = Panel) {
                listOf("Поиск", "Библиотека", "Профиль").forEach { tab ->
                    NavigationBarItem(selected = selectedTab == tab, onClick = { selectedTab = tab }, icon = {}, label = { Text(tab) })
                }
            }
        }) { padding ->
            Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
                Text("SOUNDCLOUD X", color = Accent, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(20.dp))
                Text(when (selectedTab) { "Поиск" -> "Найди свой звук."; "Библиотека" -> "Твоя библиотека"; else -> "Профиль" }, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(18.dp))
                if (selectedTab == "Поиск") {
                    OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Поиск треков и исполнителей") }, singleLine = true, shape = RoundedCornerShape(16.dp))
                    Spacer(Modifier.height(18.dp))
                    Surface(color = Panel, shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(18.dp)) {
                            Text("Поиск пока не подключён", color = Color.White, fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.height(6.dp))
                            Text("Следующий этап: OAuth и официальный SoundCloud API.", color = Color.LightGray)
                        }
                    }
                } else {
                    Text("Раздел подготовлен для следующего этапа разработки.", color = Color.LightGray)
                }
            }
        }
    }
}
