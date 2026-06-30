package com.example.sethmojitranslator

object DictionarySeeder {

    fun defaultData(): List<DictionaryEntity> {
        return listOf(

            DictionaryEntity(english = "Hello", emoji = "💛"),
            DictionaryEntity(english = "John", emoji = "😅"),
            DictionaryEntity(english = "James", emoji = "📛"),
            DictionaryEntity(english = "Seth", emoji = "📐"),
            DictionaryEntity(english = "Declan", emoji = "🪴"),
            DictionaryEntity(english = "Goodbye", emoji = "👋"),
            DictionaryEntity(english = "Eat", emoji = "😋"),
            DictionaryEntity(english = "Drink", emoji = "💦"),
            DictionaryEntity(english = "Love", emoji = "🖤"),
            DictionaryEntity(english = "Yes", emoji = "👍🏿"),
            DictionaryEntity(english = "No", emoji = "👎🏼")

        )
    }
}
