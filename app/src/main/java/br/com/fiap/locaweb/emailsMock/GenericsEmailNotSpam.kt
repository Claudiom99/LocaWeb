package br.com.fiap.locaweb.emailsMock
import br.com.fiap.locaweb.classes.Attachment
import br.com.fiap.locaweb.classes.Email

val attachmentNotSpam1 = Attachment(
    local = "dir/home/downloads",
    type = "image",
    name = "image-09-01-2024-123456.png"
)
val emailNotSpam1 = Email(
    receiver = "receiver@exemplo.com",
    sender = "thomasjefferson@fiap.com",
    title = "Título de exemplo",
    emailContent = "teste de email genérico",
    attachment = listOf(attachmentNotSpam1)
)