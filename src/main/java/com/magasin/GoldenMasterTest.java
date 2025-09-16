package com.magasin;

import java.io.FileWriter;
import java.io.IOException;

public class GoldenMasterTest {

    public static void main(String[] args) throws IOException {
        // création des obj
        Item[] items = new Item[]{
                new Item("Normal", 10, 20),
                new Item("Comté", 2, 0),
                new Item("Kryptonite", 0, 80),
                new Item("Pass VIP Concert", 15, 20),
                new Item("Pouvoirs magiques", 3, 6),
        };

        Magasin magasin = new Magasin(cloneItems(items)); // IMPORTANT: ne pas muter original
        FileWriter goldenWriter = new FileWriter("golden_master.txt");
        FileWriter newWriter = new FileWriter("new_output.txt");

        for (int day = 0; day <= 30; day++) {
            // Impression pour comparaison
            goldenWriter.write("-------- day " + day + " --------\n");
            newWriter.write("-------- day " + day + " --------\n");

            for (Item item : items) {
                goldenWriter.write(item + "\n");
            }

            for (Item item : magasin.items) {
                newWriter.write(item + "\n");
            }

            // màj next days
            Magasin goldenMagasin = new Magasin(cloneItems(items));
            Magasin newMagasin = new Magasin(magasin.items);

            goldenMagasin.updateQuality();
            newMagasin.updateQuality();

            // ecrase les tableaux
            items = goldenMagasin.items;
            magasin.items = newMagasin.items;
        }

        goldenWriter.close();
        newWriter.close();
    }

    private static Item[] cloneItems(Item[] items) {
        Item[] clone = new Item[items.length];
        for (int i = 0; i < items.length; i++) {
            clone[i] = new Item(items[i].name, items[i].sellIn, items[i].quality);
        }
        return clone;
    }
}
