package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GameBettingOutcomeTest {

  // Player Ties = get Bet back
  // Player BJ = Bet + Bet + 50% Bet

  @Test
  public void playerWith20Bets10WhenWinsBalanceIs30() throws Exception {
    Game game = createGameWithPlayerBalanceOf(20);
    game.getPlayer().playerBets(10);

    game.getPlayer().playerWins();

    assertThat(game.getPlayer().playerBalance())
        .isEqualTo(30);
  }

  @Test
  public void playerWith80Bets70WhenTiesBalanceIs80() throws Exception {
    Game game = createGameWithPlayerBalanceOf(80);
    game.getPlayer().playerBets(70);

    game.getPlayer().playerTies();

    assertThat(game.getPlayer().playerBalance())
        .isEqualTo(80);
  }

  @Test
  public void playerWith35Bets30WhenLosesBalanceIs5() throws Exception {
    Game game = createGameWithPlayerBalanceOf(35);

    game.getPlayer().playerBets(30);
    game.getPlayer().playerLoses();

    assertThat(game.getPlayer().playerBalance())
        .isEqualTo(5);
  }

  @Test
  public void playerWith40Bets15BalanceIs25() throws Exception {
    Game game = createGameWithPlayerBalanceOf(40);

    game.getPlayer().playerBets(15);

    assertThat(game.getPlayer().playerBalance())
        .isEqualTo(25);
  }

  @Test
  public void playerDeposits18DollarsBalanceIs18Dollars() throws Exception {
    Game game = createGameWithPlayerBalanceOf(18);

    assertThat(game.getPlayer().playerBalance())
        .isEqualTo(18);
  }

  @Test
  public void getTheTotalBetAmountForAPlayerUponBettingForMultipleGames() throws Exception {
    double calculatedTotalBet;
    double actualTotalBet = 576;

    //Create Multiple Games and place the bet for each
    Game game1 = createGameWithPlayerBalanceOf(200);
    game1.getPlayer().playerBets(100);
    game1.getPlayer().playerWins();

    Game game2 = createGameWithPlayerBalanceOf(200);
    game2.getPlayer().playerBets(150);
    game2.getPlayer().playerLoses();

    Game game3 = createGameWithPlayerBalanceOf(400);
    game3.getPlayer().playerBets(326);
    game3.getPlayer().playerTies();

    //Calculate the Total Bet for Multiple Games
    calculatedTotalBet = game1.getPlayer().totalAmountBet() +game2.getPlayer().totalAmountBet()+game3.getPlayer().totalAmountBet();

    //Verify the total bet amount for all the games
    assertThat(calculatedTotalBet).isEqualTo(actualTotalBet);
  }

  @Test
  public void playerGet10AsBonusWhenHeBets100OrMore() throws Exception {
    Game game = createGameWithPlayerBalanceOf(200);
    game.getPlayer().playerBets(100);
    game.getPlayer().playerWins();
    assertThat(game.getPlayer().playerBalance())
            .isEqualTo(310);
  }

  private Game createGameWithPlayerBalanceOf(int amount) {
    Game game = new Game();
    game.getPlayer().playerDeposits(amount);
    return game;
  }
}