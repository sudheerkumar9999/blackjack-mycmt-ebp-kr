package com.jitterted.ebp.blackjack;

import java.text.DecimalFormat;

public class Player {
    private double playerBalance = 0;
    private double playerBet = 0;
    private double totalAmountBet = 0;
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public void playerDeposits(double amount) {
        if(amount <= 0){
            throw new UnsupportedOperationException("Deposit Amount should be greater than 0");
        }
        playerBalance += amount;
    }

    public void playerBets(double betAmount) {
        if(playerBalance == 0){
            throw new UnsupportedOperationException("Wallet Balance is Zero. Please add the amount to place a bet.");
        }
        if(betAmount > playerBalance){
            throw new UnsupportedOperationException("Bet Amount cannot be greater than Balance Amount");
        }
        if(betAmount <= 0){
            throw new UnsupportedOperationException("Bet Amount should be greater than 0");
        }
        playerBet = betAmount;
        playerBalance -= betAmount;
        totalAmountBet += betAmount;
        if(betAmount >= 100){
            playerBalance += 10;
        }
    }

    public double playerBalance() {
        return playerBalance;
    }

    public void playerWins() {
        playerBalance += playerBet * 2;
    }

    public void playerLoses() {
        playerBalance += playerBet * 0;
    }

    public void playerTies() {
        playerBalance += playerBet * 1;
    }

    public double totalAmountBet(){
        return totalAmountBet;
    }
}
