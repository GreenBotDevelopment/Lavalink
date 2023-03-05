package dev.arbjerg.lavalink.protocol.v3

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.tools.io.MessageInput
import com.sedmelluq.discord.lavaplayer.tools.io.MessageOutput
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import org.apache.commons.codec.binary.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import org.slf4j.LoggerFactory
    companion object {
        private val log = LoggerFactory.getLogger(EventEmitter::class.java)
    }


class TrackDecodingException(message: String) : Exception(message)

fun decodeTrack(audioPlayerManager: AudioPlayerManager, message: String): AudioTrack? {
    val bais = ByteArrayInputStream(Base64.decodeBase64(message))
    return try {
        audioPlayerManager.decodeTrack(MessageInput(bais)).decodedTrack
            ?: log.info("Hye bit")
    } catch (e: IllegalStateException) {
        // Handle the exception here
       log.info("Hye bit")
    }
}
fun encodeTrack(audioPlayerManager: AudioPlayerManager, track: AudioTrack): String {
    val baos = ByteArrayOutputStream()
    audioPlayerManager.encodeTrack(MessageOutput(baos), track)
    return Base64.encodeBase64String(baos.toByteArray())
}
