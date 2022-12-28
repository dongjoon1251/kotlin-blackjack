package blackjack.domain

class Card private constructor(val number: CardNumber, val shape: CardShape) {
    override fun toString(): String = number.value + shape.label

    fun getScore(): Int = number.score

    companion object {
        val ALL_CARDS: List<Card> = CardShape.values().flatMap { shape ->
            CardNumber.values().map { number -> Card(number, shape) }
        }

        fun of(number: CardNumber, shape: CardShape): Card {
            return ALL_CARDS.asSequence()
                .filter { it.shape == shape }
                .find { it.number == number }
                ?: throw IllegalArgumentException("찾을 수 없는 카드입니다. - ${number.value} ${shape.label}")
        }
    }
}
