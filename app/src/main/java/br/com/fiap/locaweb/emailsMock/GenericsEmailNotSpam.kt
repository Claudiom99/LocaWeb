package br.com.fiap.locaweb.emailsMock
import br.com.fiap.locaweb.classes.Attachment
import br.com.fiap.locaweb.classes.Email

val attachmentNotSpam1 = Attachment(
    local = "dir/home/downloads",
    type = "pdf",
    name = "challenge-final-09-2024.pdf"
)

val emailNotSpam1 = Email(
    receiver = "avaliador@fiap.com",
    sender = "thomasjefferson@fiap.com",
    title = "Atualização sobre o Challenge",
    emailContent = """
        Olá, Avaliador!

        A equipe formada por Thomas, David, Rodrigo e Cláudio completou a missão! 🚀 
        O nosso Challenge está pronto e em suas mãos. Foi uma jornada épica, e estamos prontos para a nota (Avalie com carinho). 😄

        Até a próxima missão!

        Atenciosamente,
        equipe de fiapentos
    """.trimIndent(),
    attachment = listOf(attachmentNotSpam1)
)