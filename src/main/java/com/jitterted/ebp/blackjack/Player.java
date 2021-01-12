package com.jitterted.ebp.blackjack;

public class Player {
    private int playerBalance = 0;
    private int playerBet = 0;
    private double totalAmountBet = 0;

    public void playerDeposits(int amount) {
        if(amount <= 0){
            throw new UnsupportedOperationException("Deposit Amount should be greater than 0");
        }
        playerBalance += amount;
    }

    public void playerBets(int betAmount) {
        if(playerBalance < betAmount){
            throw new UnsupportedOperationException("Bet Amount cannot be greater than Balance Amount");
        }
        playerBet = betAmount;
        playerBalance -= betAmount;
        totalAmountBet += betAmount;
        if(betAmount >= 100){
            playerBalance += 10;
        }
    }

    public int playerBalance() {
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
