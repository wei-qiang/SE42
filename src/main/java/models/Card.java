package models;

public class Card {
    private int id;
    private String cardId;
    private String name;
    private String type;
    private String faction;
    private String rarity;
    private int cost;
    private int attack;
    private int health;
    private String text;
    private String flavor;
    private String artist;
    private Boolean collectible;
    private Boolean elite;
    private String race;
    private String img;
    private String imgGold;
    private String locale;

    public Card() {
    }

    public Card(String cardId, String name, String type, String faction, String rarity, int cost, int attack, int health, String text, String flavor, String artist, Boolean collectible, Boolean elite, String race, String img, String imgGold, String locale) {
        this.cardId = cardId;
        this.name = name;
        this.type = type;
        this.faction = faction;
        this.rarity = rarity;
        this.cost = cost;
        this.attack = attack;
        this.health = health;
        this.text = text;
        this.flavor = flavor;
        this.artist = artist;
        this.collectible = collectible;
        this.elite = elite;
        this.race = race;
        this.img = img;
        this.imgGold = imgGold;
        this.locale = locale;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Boolean getCollectible() {
        return collectible;
    }

    public void setCollectible(Boolean collectible) {
        this.collectible = collectible;
    }

    public Boolean getElite() {
        return elite;
    }

    public void setElite(Boolean elite) {
        this.elite = elite;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgGold() {
        return imgGold;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
