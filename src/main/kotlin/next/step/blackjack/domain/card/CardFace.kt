package next.step.blackjack.domain.card

enum class CardFace(val desc: String, val point: Int) {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    fun maxPoint(): Int = point

    fun minPoint(): Int = if (isAce()) 1 else point

    private fun isAce(): Boolean = this == ACE
}
