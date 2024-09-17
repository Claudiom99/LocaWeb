package br.com.fiap.locaweb.emailsMock

import br.com.fiap.locaweb.model.Email

object GenericsEmails {
    val emailsList = listOf(
        Email(1, "john@example.com", "Lembrete de reunião", "Não se esqueça da reunião de amanhã!", "2024-09-01", false),
        Email(2, "doe@example.com", "Oferta especial!", "Ganhe 50% de desconto na sua próxima compra.", "2024-09-02", false),
        Email(3, "spam@example.com", "Você ganhou um prêmio!", "Clique aqui para resgatar sua recompensa!", "2024-09-03", true)
    )
}
