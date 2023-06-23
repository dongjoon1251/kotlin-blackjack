package next.step.blackjack.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerCardsTest : BehaviorSpec({

    Given("PlayerCards") {
        When("add하면") {
            val playerCards = PlayerCards.of(emptyList())
            val card = Card.of(CardFace.ACE, CardSymbol.CLUB)

            playerCards.add(card)

            Then("가지고 있는 카드를 추가함") {
                assertSoftly {
                    playerCards shouldBe PlayerCards.of(mutableListOf(card))
                    playerCards.size() shouldBe 1
                }
            }
        }

        When("카드 총 점수가 21점이 넘지 않으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.KING, CardSymbol.CLUB),
                    Card.of(CardFace.KING, CardSymbol.HEART)
                )
            )

            Then("burst하지 않음") {
                playerCards.isBurst() shouldBe false
            }

            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("카드 총 점수가 21점이면") {
            val playerCards =
                PlayerCards.of(listOf(Card.of(CardFace.ACE, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)))

            Then("blackjack!") {
                playerCards.isBlackJack() shouldBe true
            }
        }
        When("카드를 총 점수가 21점이 넘으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.KING, CardSymbol.CLUB),
                    Card.of(CardFace.KING, CardSymbol.HEART),
                    Card.of(CardFace.TWO, CardSymbol.DIAMOND)
                )
            )

            Then("burst함") {
                playerCards.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최대점수가 블랙잭점수보다 더 작으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                )
            )
            Then("최대점수를 점수로 제공함") {
                playerCards.point() shouldBe 20
            }
            Then("burst 하지 않음") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최소점수가 블랙잭점수보다 더 작고 최대점수가 블랙잭점수보다 더 크면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                )
            )
            Then("최소점수를 점수로 제공함") {
                playerCards.point() shouldBe 19
            }
            Then("burst 하지 않음") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최소점수가 블랙잭점수보다 더 크면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.THREE, CardSymbol.HEART),
                )
            )
            Then("최소점수를 점수로 제공함") {
                playerCards.point() shouldBe 22
            }
            Then("burst 함") {
                playerCards.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최대점수가 블랙잭점수와 같으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.ONE, CardSymbol.DIAMOND)
                )
            )
            Then("블랙잭 점수를 점수로 제공함") {
                playerCards.point() shouldBe 21
            }
            Then("burst 안함") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭임") {
                playerCards.isBlackJack() shouldBe true
            }
        }
        When("Ace가 포함되어 최소점수가 블랙잭점수와 같으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.TWO, CardSymbol.HEART),
                )
            )
            Then("블랙잭 점수를 점수로 제공함") {
                playerCards.point() shouldBe 21
            }
            Then("burst 안함") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭임") {
                playerCards.isBlackJack() shouldBe true
            }
        }
    }


})
