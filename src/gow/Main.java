package gow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting God of War: Betrayal (Linux Port)...");
        SwingUtilities.invokeLater(() -> {
            try {
                GOWMIDlet midlet = new GOWMIDlet();
                GameHost host = GameHost.createInstance(midlet);
                host.setVisible(true);

                // Run MIDlet startApp in a separate thread
                new Thread(() -> {
                    try {
                        midlet.startApp();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "Game-Init").start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
