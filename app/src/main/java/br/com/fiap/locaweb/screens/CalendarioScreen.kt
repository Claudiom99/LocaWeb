package br.com.fiap.locaweb.screens

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.methods.Style
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson
import java.util.Calendar

@Composable
fun AgendarEventoScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val context = LocalContext.current
    val styles = Style(context)
    val wallpaper = styles.wallpaper()
    var openCalendar by remember { mutableStateOf(false) }
    val usuarioRepository = UsuarioRepository(context)
    val userJson = backStackEntry.arguments?.getString("usuario")
    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    if (openCalendar) {
        LaunchedEffect(Unit) {
            abrirCalendario(context)
            navController.navigate("menu") {
                popUpTo("menu") { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = wallpaper),
            contentDescription = "Fundo escuro com pedras",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de título
            Image(
                painter = painterResource(id = R.drawable.locaweb_logo),
                contentDescription = "Logo de Título",
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Botão "Agendar Evento"
            Button(
                onClick = { openCalendar = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .padding(16.dp)
                    .height(75.dp)  // Altura
                    .width(200.dp)   // Largura
            ) {
                Text(text = "Agendar Evento", color = styles.textButtonColor(), fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Linha com botão de ícone e texto "Voltar Menu" clicável
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .height(60.dp)
                    .width(200.dp)
                    .border(
                        width = 2.dp,
                        color = styles.solidColors(),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .background(Color.Transparent, shape = RoundedCornerShape(32.dp))
                    .clickable {
                        val userJson = gson.toJson(usuario)
                        navController.navigate("menu/$userJson") {
                            popUpTo("menu") { inclusive = true }
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_return_button),
                    contentDescription = "Ícone voltar",
                    tint = styles.solidColors(),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Voltar Menu", color = styles.solidColors(), fontSize = 16.sp)
            }
        }
    }
}

// Função para abrir o calendário nativo do Android
fun abrirCalendario(context: Context) {
    val cal = Calendar.getInstance()
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
        putExtra(
            CalendarContract.EXTRA_EVENT_END_TIME,
            cal.timeInMillis + 60 * 60 * 1000
        ) // Evento de 1 hora
        putExtra(CalendarContract.Events.TITLE, "Novo Evento")
        putExtra(CalendarContract.Events.DESCRIPTION, "Descrição do evento")
        putExtra(CalendarContract.Events.EVENT_LOCATION, "Localização do evento")
        putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
    }
    context.startActivity(intent)
}

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AgendarEventoScreenPreview() {
    AgendarEventoScreen(rememberNavController())
}*/
