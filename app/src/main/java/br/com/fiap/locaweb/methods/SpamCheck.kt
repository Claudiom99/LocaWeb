package br.com.fiap.locaweb.methods

import br.com.fiap.locaweb.classes.Attachment
import br.com.fiap.locaweb.classes.Email
import br.com.fiap.locaweb.lists.ContactsList
import br.com.fiap.locaweb.lists.spamKeywords
import br.com.fiap.locaweb.lists.suspiciousDomains
import br.com.fiap.locaweb.lists.suspiciousExtensions

fun isSpam(email: Email): Boolean{
    return containsSpamKeywords(email.emailContent) ||
            hasSuspiciousAttachments(email.attachment) ||
            containsSuspiciousLinks(email.emailContent) ||
            isAContact(email.sender)
}

fun containsSpamKeywords(content: String): Boolean {
    return spamKeywords.any { content.contains(it, ignoreCase = true) }
}

fun hasSuspiciousAttachments(attachments: List<Attachment>): Boolean {
    return attachments.any { attachment ->
        suspiciousExtensions.any { attachment.name.endsWith(it, ignoreCase = true) }
    }
}

fun containsSuspiciousLinks(content: String): Boolean {
    return suspiciousDomains.any { content.contains(it, ignoreCase = true) }
}

fun isAContact(sender: String): Boolean {
    return !ContactsList.any {sender.contains(it, ignoreCase = true)}
}

