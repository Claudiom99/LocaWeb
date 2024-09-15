package br.com.fiap.locaweb.methods

import br.com.fiap.locaweb.classes.Attachment
import br.com.fiap.locaweb.classes.Email
import br.com.fiap.locaweb.lists.ContactsList
import br.com.fiap.locaweb.lists.spamKeywords
import br.com.fiap.locaweb.lists.suspiciousDomains
import br.com.fiap.locaweb.lists.suspiciousExtensions

fun isSpam(email: Email): Boolean {

    val spamKeywordCheck = containsSpamKeywords(email.emailContent, email.title)
    val suspiciousAttachmentsCheck = hasSuspiciousAttachments(email.attachment)
    val suspiciousLinksCheck = containsSuspiciousLinks(email.emailContent)
    val unknownSenderCheck = isUnknownSender(email.sender)

    println("test: Spam keyword detected: $spamKeywordCheck")
    println("test: Suspicious attachment detected: $suspiciousAttachmentsCheck")
    println("test: Suspicious link detected: $suspiciousLinksCheck")
    println("test: Unknown sender detected: $unknownSenderCheck")

    return containsSpamKeywords(email.emailContent, email.title) ||
            hasSuspiciousAttachments(email.attachment) ||
            containsSuspiciousLinks(email.emailContent) ||
            isUnknownSender(email.sender)
}

fun containsSpamKeywords(content: String, title: String): Boolean {
    return spamKeywords.any { content.contains(it, ignoreCase = true) || title.contains(it, ignoreCase = true) }
}

fun hasSuspiciousAttachments(attachments: List<Attachment>?): Boolean {
    return attachments?.any { attachment ->
        suspiciousExtensions.any { attachment.name.endsWith(it, ignoreCase = true) }
    } ?: false
}

fun containsSuspiciousLinks(content: String): Boolean {
    return suspiciousDomains.any { content.contains(it, ignoreCase = true) }
}

fun isUnknownSender(sender: String): Boolean {
    return !ContactsList.any { contact -> sender.contains(contact, ignoreCase = true) }
}
