package UT2.PingPong;

public class Main {

    public static void main(String[] args) {

        PingPong ping = new PingPong("PING");
        PingPong pong = new PingPong("PONG");

        ping.start();
        pong.start();
    }
}
