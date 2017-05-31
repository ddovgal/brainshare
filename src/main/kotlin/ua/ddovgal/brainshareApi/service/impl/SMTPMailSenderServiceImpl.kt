package ua.ddovgal.brainshareApi.service.impl

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import ua.ddovgal.brainshareApi.configuration.MailProperties
import ua.ddovgal.brainshareApi.service.MailSenderService
import java.util.*
import javax.mail.Message
import javax.mail.internet.InternetAddress

@Service
class SMTPMailSenderServiceImpl(
        private val sender: JavaMailSender,
        private val mailProperties: MailProperties
) : MailSenderService {

    private val FROM_ADDRESS_NAME = "Brainshare Support"

    @Async
    override fun sendSimpleMessage(receiverAddress: String, receiverName: String, subject: String, text: String) {
        val message = sender.createMimeMessage()
        message.setFrom(InternetAddress(mailProperties.fromAddress, FROM_ADDRESS_NAME))
        message.setRecipient(Message.RecipientType.TO, InternetAddress(receiverAddress, receiverName))
        message.subject = subject
        message.setText(text)
        message.sentDate = Date()
        sender.send(message)
    }
}