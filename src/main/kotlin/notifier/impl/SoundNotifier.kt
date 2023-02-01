package notifier.impl

import notifier.api.Notifier
import javax.sound.midi.MidiEvent
import javax.sound.midi.MidiSystem
import javax.sound.midi.Sequence
import javax.sound.midi.ShortMessage

class SoundNotifier<T> : Notifier<T> {
    override fun notify(prev: T?, new: T) {
        MidiSystem.getSequencer().apply {
            open()
            sequence = Sequence(Sequence.PPQ, 4).apply {
                createTrack().apply {
                    val step = 3L
                    repeat(15) {
                        val pos = it * 2 * step
                        add(MidiEvent(ShortMessage(144, 1, 88, 127), pos))
                        add(MidiEvent(ShortMessage(128, 1, 88, 127), pos + step))
                        add(MidiEvent(ShortMessage(144, 1, 75, 127), pos + step))
                        add(MidiEvent(ShortMessage(128, 1, 75, 127), pos + 2 * step))
                    }
                }
            }
            start()
        }
    }
}
