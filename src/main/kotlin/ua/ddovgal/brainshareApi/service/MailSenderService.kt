package ua.ddovgal.brainshareApi.service

interface MailSenderService {
    /**
     * Sends simple text message. Default from address is got from {$mail.from-mail} property
     * and it's default alias name is "Brainshare Support"
     * @param receiverAddress email address of receiver
     * @param receiverName alias for receiver address
     * @param subject subject of mail
     * @param text simple text of message
     */
    fun sendSimpleMessage(receiverAddress: String, receiverName: String, subject: String, text: String)
}