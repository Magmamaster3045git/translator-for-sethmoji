package com.example.sethmojitranslator

data class DictionaryEntry(
    val english: String,
    val emoji: String
)

object SethmojiDictionary {

    val entries = listOf(

        DictionaryEntry("Hello", "💛"),
        DictionaryEntry("John", "😅"),
        DictionaryEntry("James", "📛"),
        DictionaryEntry("Seth", "📐"),
        DictionaryEntry("Declan", "🪴"),
        DictionaryEntry("Goodbye", "👋"),
        DictionaryEntry("Retard", "🍴"),
        DictionaryEntry("Is", "🇮🇲"),
        DictionaryEntry("In", "🇮🇩"),
        DictionaryEntry("Nigga", "🌟"),
        DictionaryEntry("Am", "🚶"),
        DictionaryEntry("Rape", "📼"),
        DictionaryEntry("To", "🔫"),
        DictionaryEntry("You", "😘"),
        DictionaryEntry("Coming", "💥"),
        DictionaryEntry("Going", "🦰"),
        DictionaryEntry("Eat", "😋"),
        DictionaryEntry("Drink", "💦"),
        DictionaryEntry("And", "🇦🇩"),
        DictionaryEntry("Fun", "🍄"),
        DictionaryEntry("Good", "🍎"),
        DictionaryEntry("Bad", "🦹"),
        DictionaryEntry("Love", "💙"),
        DictionaryEntry("Like", "👍🏿"),
        DictionaryEntry("Dislike", "👎🏼"),
        DictionaryEntry("Pookie", "👀"),
        DictionaryEntry("Big", "😃"),
        DictionaryEntry("Black", "🖤"),
        DictionaryEntry("Yes", "🫡"),
        DictionaryEntry("No", "🙈")

    )

    fun englishToEmoji(word: String): String? {
        return entries.firstOrNull {
            it.english.equals(word.trim(), ignoreCase = true)
        }?.emoji
    }

    fun emojiToEnglish(emoji: String): String? {
        return entries.firstOrNull {
            it.emoji == emoji.trim()
        }?.english
    }

}
