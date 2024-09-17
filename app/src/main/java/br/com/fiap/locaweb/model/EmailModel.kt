package br.com.fiap.locaweb.model

data class Email(
    val id: Int,
    val sender: String,
    val subject: String,
    val message: String,
    val date: String,
    val isSpam: Boolean = false
)
