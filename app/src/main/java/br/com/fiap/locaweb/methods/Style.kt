package br.com.fiap.locaweb.methods

import android.content.Context
import androidx.compose.ui.graphics.Color
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.classes.SheredUser

class Style(context: Context){
    private val theme = SheredUser().obterTema(context)

    fun wallpaper(): Int {
        return if (theme == "light") R.drawable.fundo_ligth else R.drawable.fundo_dark
    }

    fun textfieldAndIconsFocus(isFocusedEmail: Boolean): Color {
        return if (theme == "dark"){
            if (isFocusedEmail) Color.White else Color.LightGray.copy(alpha = 0.5F)
        }else{
            if (isFocusedEmail) Color.Black else Color.DarkGray.copy(alpha = 0.8F)
        }
    }

    fun inputText(): Color {
        return if (theme == "light") Color.Black else Color.White
    }

    fun solidColors(): Color {
        return if (theme == "light") Color.Black else Color.LightGray
    }

    fun colorReceiveEmail(): Color {
        return if (theme == "light") Color.Black else Color.White
    }

    fun textButtonColor(): Color {
        return if (theme == "light") Color.White else Color.Black
    }

    fun textSpamColor(): Color {
        return if (theme == "light") Color.Red else Color.Yellow
    }

}

