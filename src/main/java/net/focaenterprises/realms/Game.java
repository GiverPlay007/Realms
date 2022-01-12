package net.focaenterprises.realms;

import net.focaenterprises.realms.game.Realms;

public class Game {
  public static void main(String[] args) {
    Realms realms = new Realms();
    realms.load();
    realms.start();
  }
}
