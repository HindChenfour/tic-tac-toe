package com.estf.gl.tictactoe.models;

import java.io.Serializable;

public class Game implements Serializable {
    private Player player1;
    private Player player2;
    private String winner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = "";
    }

    public Game(Player player1, Player player2, String winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(Player player) {
        winner = player.getName();
        player.addScore();
    }

    @Override
    public String toString() {
        return "Game{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", winner='" + winner + '\'' +
                '}';
    }
}
