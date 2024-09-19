package br.com.fiap.locaweb.classes

import android.content.Context

class SheredUser {

    fun obterIdDoUsuario(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getLong("user_id", -1)
    }

    fun salvarTema(context: Context, tema: String) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("user_theme", tema)
            apply()
        }
    }

    fun obterTema(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("user_theme", "dark") ?: "dark" // "dark" é o valor padrão
    }
}