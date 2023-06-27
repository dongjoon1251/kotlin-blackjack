package next.step.blackjack.domain.card.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult
import next.step.blackjack.domain.playercards.state.BlackjackState
import next.step.blackjack.domain.playercards.state.BurstState
import next.step.blackjack.domain.playercards.state.FinishedState
import next.step.blackjack.domain.playercards.state.PlayerCardsState
import next.step.blackjack.domain.playercards.state.UnfinishedState
import org.junit.jupiter.api.assertThrows

class BurstStateTest : DescribeSpec({

    describe("BurstState") {
        context("next") {
            it("항상 예외 발생") {
                assertThrows<IllegalArgumentException> { BurstState.next(Cards.of(emptyList())) }
            }
        }
        context("카드 상태에 따라 게임 결과가 달라짐") {
            data class ResultExpected(val state: PlayerCardsState, val result: GameResult)
            withData(
                ResultExpected(BlackjackState, GameResult.LOSE),
                ResultExpected(UnfinishedState, GameResult.LOSE),
                ResultExpected(FinishedState, GameResult.LOSE),
                ResultExpected(BurstState, GameResult.WIN),
            ) { (state, result) ->
                BurstState.fight(state) shouldBe result
            }
        }
    }
})
