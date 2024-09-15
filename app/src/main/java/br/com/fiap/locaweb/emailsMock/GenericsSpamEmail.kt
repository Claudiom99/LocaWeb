package br.com.fiap.locaweb.emailsMock

import br.com.fiap.locaweb.classes.Attachment
import br.com.fiap.locaweb.classes.Email

val attachmentSpam1 = Attachment(
    local = "dir/home/downloads",
    type = ".png",
    name = "image-09-01-2024-123456"
)
val emailSpam1 = Email(
    receiver = "receiver@exemplo.com",
    sender = "sender@exemplo.com",
    title = "Ganhe dinheiro rápido agora mesmo!",
    emailContent = """Olá,

    Você foi selecionado para uma oferta imperdível! Ganhe dinheiro rápido e fácil com o nosso novo método exclusivo. Não perca tempo, o limite de tempo está acabando!

    Clique aqui: http://login-wsapp-hk.top/ para aproveitar a promoção exclusiva.

            Atenciosamente,
    Equipe de Oportunidades""".trimIndent(),
    attachment = listOf(attachmentSpam1)
)

val attachmentSpam2 = Attachment(
    local = "dir/home/downloads",
    type = "archiver",
    name = "premio_sorte.zip"
)
val emailSpam2 = Email(
    receiver = "receiver@exemplo.com",
    sender = "sender@exemplo.com",
    title = "Parabéns, você ganhou um prêmio!",
    emailContent = """Olá,

Você foi sorteado na nossa loteria e ganhou um prêmio especial! Para reivindicar seu prêmio, atualize suas informações e baixe o anexo a seguir.

Clique aqui para mais detalhes: https://m6247015k6e0q.wixsite.com/metamasksegnin 

Atenciosamente,
Equipe de Promoções""".trimIndent(),
    attachment = listOf(attachmentSpam2)
)

val attachmentSpam3 = Attachment(
    local = "dir/home/downloads",
    type = ".png",
    name = "image-09-01-2024-123456"
)
val emailSpam3 = Email(
    receiver = "receiver@exemplo.com",
    sender = "sender@exemplo.com",
    title = "Atualize suas informações de conta agora!",
    emailContent = """Caro usuário,

Detectamos atividades suspeitas em sua conta. Para sua segurança, atualize suas informações de cartão de crédito imediatamente.

Clique no link abaixo para atualizar sua senha e evitar o bloqueio da conta:
https://verifyiosapp.com/

Por favor, não ignore este aviso.

Atenciosamente,
Suporte ao Cliente""".trimIndent(),
    attachment = listOf(attachmentSpam3)
)

val attachmentSpam4 = Attachment(
    local = "dir/home/downloads",
    type = ".png",
    name = "image-09-01-2024-123456"
)
val emailSpam4 = Email(
    receiver = "receiver@exemplo.com",
    sender = "sender@exemplo.com",
    title = "Ganhe Dinheiro Trabalhando em Casa!",
    emailContent = """Olá,

Quer aumentar seus lucros trabalhando de casa? Nós temos a solução perfeita para você! Trabalhe em casa e ganhe dinheiro rápido com esta oportunidade única!

Baixe o anexo agora e comece já: trabalho_facil.exe

Clique aqui para mais informações: https://currentlyupdate.wixsite.com/signin

Não perca essa chance!""".trimIndent() ,
    attachment = listOf(attachmentSpam4)
)

val attachmentSpam5 = Attachment(
    local = "dir/home/downloads",
    type = "archive",
    name = "desconto_incrivel.rar"
)
val emailSpam5 = Email(
    receiver = "receiver@exemplo.com",
    sender = "sender@exemplo.com",
    title = "Última Chance! Promoção exclusiva com 50% de desconto",
    emailContent = """Olá,

Aproveite nossa promoção exclusiva! Descontos incríveis de até 50%! Compre agora e não perca essa oportunidade.

Clique no link e compre agora: https://superdupergraphics.com/.include.chk/licence.html?referCode=

Atenciosamente,
Equipe de Ofertas""".trimIndent(),
    attachment = listOf(attachmentSpam5)
)
