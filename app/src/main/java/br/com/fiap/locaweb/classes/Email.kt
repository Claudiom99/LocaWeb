package br.com.fiap.locaweb.classes

class Email(
    val sender: String,
    val emailContent: String,
    val attachment: List<Attachment>,
    val link: List<String>
){


}