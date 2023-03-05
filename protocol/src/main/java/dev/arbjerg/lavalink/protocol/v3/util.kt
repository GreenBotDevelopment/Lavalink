package dev.arbjerg.lavalink.protocol.v3

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.tools.io.MessageInput
import com.sedmelluq.discord.lavaplayer.tools.io.MessageOutput
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import org.apache.commons.codec.binary.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import org.slf4j.LoggerFactory

fun decodeTrack(audioPlayerManager: AudioPlayerManager, message: String): AudioTrack? {
    val bais = ByteArrayInputStream(Base64.decodeBase64(message))
    val track = audioPlayerManager.decodeTrack(MessageInput(bais))
    val track2 = track.decodedTrack
    if (track2 == null) return null
    return track2
}
fun encodeTrack(audioPlayerManager: AudioPlayerManager, track: AudioTrack): String {
    val baos = ByteArrayOutputStream()
    audioPlayerManager.encodeTrack(MessageOutput(baos), track)
    return Base64.encodeBase64String(baos.toByteArray())
}
