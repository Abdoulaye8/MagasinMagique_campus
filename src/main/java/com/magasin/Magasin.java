package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

        public void updateQuality() {
            for (Item item : items) {
                switch (item.name) {
                    case "Comté":
                        updateComte(item);
                        break;

                    case "Pass VIP Concert":
                        updateConcert(item);
                        break;

                    case "Kryptonite":
                        // Ne rien faire : objet légendaire
                        break;

                    case "Pouvoirs magiques":
                        updateMagicalPower(item);
                        break;

                    default:
                        updateNormal(item);
                        break;
                }
            }
        }

        private void updateNormal(Item item) {
            decreaseQuality(item, 1);
            item.sellIn--;
            if (item.sellIn < 0) {
                decreaseQuality(item, 1);
            }
        }

        private void updateComte(Item item) {
            increaseQuality(item, 1);
            item.sellIn--;
            if (item.sellIn < 0) {
                increaseQuality(item, 1);
            }
        }

        private void updateConcert(Item item) {
            if (item.sellIn > 10) {
                increaseQuality(item, 1);
            } else if (item.sellIn > 5) {
                increaseQuality(item, 2);
            } else if (item.sellIn > 0) {
                increaseQuality(item, 3);
            } else {
                item.quality = 0;
            }
            item.sellIn--;
        }

        private void updateMagicalPower(Item item) {
            decreaseQuality(item, 2);
            item.sellIn--;
            if (item.sellIn < 0) {
                decreaseQuality(item, 2);
            }
        }

        private void increaseQuality(Item item, int amount) {
            item.quality = Math.min(50, item.quality + amount);
        }

        private void decreaseQuality(Item item, int amount) {
            item.quality = Math.max(0, item.quality - amount);
        }
    }

