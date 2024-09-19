package br.com.fiap.locaweb.methods

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.locaweb.classes.SheredUser
import br.com.fiap.locaweb.database.repository.UsuarioRepository

@Composable
fun ButtonTheme(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val switchPosition by animateFloatAsState(targetValue = if (isChecked) 1f else 0f, label = "")
    val backgroundColor by animateColorAsState(
        targetValue = if (isChecked) Color(0xFF00008B) else Color(0xFF87CEEB	),
        label = ""
    )
    val thumbColor = if (isChecked) Color.White else Color.Yellow
    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)
    val usuarioId = SheredUser().obterIdDoUsuario(context)
    var theme: String

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .clickable {
                onCheckedChange(!isChecked)
                val theme = if (isChecked) "light" else "dark"
                usuarioRepository.atualizarTema(usuarioId, theme)
                SheredUser().salvarTema(context, theme)
                Log.d("ButtonTheme", "isChecked 'changed to: $theme, boolean: $isChecked")
            }
            .padding(4.dp)
    ) {

        Box(
            modifier = Modifier
                .size(22.dp)
                .offset(x = (30.dp * switchPosition))
                .background(thumbColor, RoundedCornerShape(50))
        )
    }
}