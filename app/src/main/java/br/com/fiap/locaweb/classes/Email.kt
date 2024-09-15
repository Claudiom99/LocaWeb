package br.com.fiap.locaweb.classes

class Email(
    val receiver: String,
    val sender: String,
    val title: String,
    val emailContent: String,
    val attachment: List<Attachment>
){


}