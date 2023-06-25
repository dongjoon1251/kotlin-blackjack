package next.step.blackjack.domain.card.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult
import org.junit.jupiter.api.assertThrows

class FinishedStateTest : DescribeSpec({

    describe("FinishedState") {
        context("next") {
            it("항상 예외 발생") {
                assertThrows<IllegalArgumentException> { FinishedState.next(Cards.of(emptyList())) }
            }
        }
        context("카드 상태에 따라 게임 결과가 달라짐") {
            data class ResultExpected(val state: CardsState, val result: GameResult)
            withData(
                ResultExpected(BlackjackState, GameResult.LOSE),
                ResultExpected(UnfinishedState, GameResult.WIN),
                ResultExpected(FinishedState, GameResult.TIE),
                ResultExpected(BurstState, GameResult.WIN),
            ) { (state, result) ->
                FinishedState.fight(state) shouldBe result
            }
        }
    }
})
