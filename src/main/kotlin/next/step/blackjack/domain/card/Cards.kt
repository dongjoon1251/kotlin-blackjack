package next.step.blackjack.domain.card

import next.step.blackjack.util.CombinationUtils

data class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun isBlackJack(): Boolean = size() == BLACKJACK_CARDS_CNT && isFinished()

    fun isFinished(): Boolean = possiblePoints().contains(FINISH_POINT)

    fun isBurst(): Boolean {
        return minSumCardsPoint() > FINISH_POINT
    }

    private fun minSumCardsPoint(): Int = cards.sumOf { it.minPoint() }

    fun point(): Int {
        return when {
            isFinished() -> FINISH_POINT
            isBurst() -> minSumCardsPoint()
            else -> possiblePoints().filter { it < FINISH_POINT }.max()
        }
    }

    private fun possiblePoints(): Set<Int> = CombinationUtils.possiblePoints(cards)

    fun size(): Int = cards.size

    fun descs(): Set<String> = cards.map { it.desc() }.toSet()

    companion object {
        private const val FINISH_POINT = 21
        private const val BLACKJACK_CARDS_CNT = 2
        fun of(cards: List<Card>): Cards = Cards(cards.toMutableList())
    }
}
