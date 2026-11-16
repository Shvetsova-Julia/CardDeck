import java.util.*;

class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Card)) return false;

        Card card = (Card) obj;
        return suit.equals(card.suit) && rank.equals(card.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}

class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final Set<Card> dealtCards = new HashSet<>();

    public Deck() {
        String[] suits = {"Черви", "Бубны", "Крести", "Пики"};
        String[] ranks = {
            "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Валет", "Дама", "Король", "Туз"
        };

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            System.out.println("В колоде нет карт.");
            return null;
        }

        Card card = cards.remove(0);
        dealtCards.add(card);
        return card;
    }

    public boolean returnCard(Card card) {
        if (card == null || !dealtCards.contains(card)) {
            return false;
        }

        dealtCards.remove(card);
        cards.add(card);
        return true;
    }

    public int getCardsCount() {
        return cards.size();
    }
}

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("Создана колода: " + deck.getCardsCount() + " карт.");

        deck.shuffle();
        System.out.println("Колода перетасована.");

        List<Card> hand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Card card = deck.dealCard();
            if (card != null) {
                hand.add(card);
            }
        }

        System.out.println("\nСданные карты:");
        for (Card card : hand) {
            System.out.println(card);
        }

        System.out.println("\nКарт в колоде: " + deck.getCardsCount());

        Card returnedCard = hand.remove(0);

        if (deck.returnCard(returnedCard)) {
            System.out.println("\nКарта возвращена: " + returnedCard);
        }

        System.out.println("Карт в колоде после возврата: "
                + deck.getCardsCount());

        System.out.println("Повторный возврат той же карты: "
                + deck.returnCard(returnedCard));
    }
}

