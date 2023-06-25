package next.step.blackjack.controller

import next.step.blackjack.domain.card.GameCards
import next.step.blackjack.domain.player.Players
import next.step.racing.view.InputView
import next.step.racing.view.OutputView

fun main() {
    runCatching {
        val gameCards = GameCards.shuffled()
        val players = Players.of(InputView.readPlayerNames()) { gameCards.pop(it) }
        OutputView.showStart(players, Players.INIT_CARD_CNT)
        players.turn(InputView::readTurn, { gameCards.pop() }) { player -> OutputView.showPlayerCards(player) }
        OutputView.showResult(players)
    }.onFailure { e ->
        OutputView.showError(e.message)
        main()
    }
}
