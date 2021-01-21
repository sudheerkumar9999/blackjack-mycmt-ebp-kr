package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    Player player = new Player();

    @Test
    public void playerDeposits15BalanceShouldBe15() throws Exception {
        player.playerDeposits(15);
        assertThat(player.playerBalance()).isEqualTo(15);
    }

    @Test
    public void playerDepositsMultipleTimesBalanceShouldBeUpdatedProperly() throws Exception {
        player.playerDeposits(12.87);
        player.playerDeposits(22.86);
        player.playerDeposits(0.98);
        assertThat(new Float(player.playerBalance())).isEqualTo(new Float(36.71));
    }

    @Test
    public void playerDepositsInvalidAmountExceptionShouldBeThrownAndBalanceShouldBeZero() throws Exception {
        assertThatThrownBy(() -> {
            player.playerDeposits(-10);
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("Deposit Amount should be greater than 0");
        assertThat(player.playerBalance()).isEqualTo(0);
    }

    @Test
    public void playerBetsForAnAmountWithoutDeposit() throws Exception {
        assertThatThrownBy(() -> {
            player.playerBets(12.34);
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("Wallet Balance is Zero. Please add the amount to place a bet.");
    }

    @Test
    public void playerBetsForAnAmountGreaterThanWalletAmount() throws Exception {
        player.playerDeposits(122.33);
        assertThatThrownBy(() -> {
            player.playerBets(122.34);
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("Bet Amount cannot be greater than Balance Amount");
    }

    @Test
    public void playerBetsForAnInvalidAmountExceptionShouldBeThrownAndBalanceShouldBeEqualToDepositAmount() throws Exception {
        player.playerDeposits(122.33);
        assertThatThrownBy(() -> {
            player.playerBets(0);
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("Bet Amount should be greater than 0");
        assertThat(player.playerBalance()).isEqualTo(122.33);
    }

    @Test
    public void playerBetsForAnAmountLessThanWalletAmountBalanceShouldBeUpdatedProperly() throws Exception {
        player.playerDeposits(22.34);
        player.playerBets(22.33);
        assertThat(new Float(player.playerBalance())).isEqualTo(new Float(0.01));
    }

    @Test
    public void playerPlacesMultipleBetsWithDifferentAmountsWalletBalanceShouldBeUpdatedProperly() throws Exception {
        player.playerDeposits(99);
        player.playerBets(12.98);
        player.playerBets(25.67);
        player.playerBets(30);
        assertThat(new Float(player.playerBalance())).isEqualTo(new Float(30.35));
    }

    @Test
    public void playerPlacesBetAndWinsBalanceShouldBeIncreased() throws Exception {
        player.playerDeposits(99);
        player.playerBets(12);
        player.playerWins();
        assertThat(player.playerBalance()).isEqualTo(111);
    }

    @Test
    public void playerPlacesBetAndLosesBalanceShouldBeDecreased() throws Exception {
        player.playerDeposits(99);
        player.playerBets(12);
        player.playerLoses();
        assertThat(player.playerBalance()).isEqualTo(87);
    }

    @Test
    public void playerPlacesBetAndTiesBalanceShouldBeEqualToInitialBalance() throws Exception {
        player.playerDeposits(99);
        player.playerBets(12);
        player.playerTies();
        assertThat(player.playerBalance()).isEqualTo(99);
    }

    @Test
    public void getTheTotalBetAmountForAPlayerUponForMultipleBets() throws Exception {
        player.playerDeposits(200);
        player.playerBets(55);
        player.playerBets(45);
        player.playerBets(36);
        assertThat(player.totalAmountBet()).isEqualTo(136);
    }

    @Test
    public void playerGet10AsBonusWhenHeBets100OrMore() throws Exception {
        player.playerDeposits(200);
        player.playerBets(100);
        player.playerWins();
        assertThat(player.playerBalance()).isEqualTo(310);
    }
}