package DTOs;

import java.util.Date;

//Jiri Uhlir
public class Games {
    private int id;
    private String gameTitle;
    private String developer;
    private int price;
    private float gbOfSpace;
    private Date realeaseDate;

    public Games(int gameId, String gameTitle, String developer, int price, float gbOfSpace, Date releaseDate){
        this.id = gameId;
        this.gameTitle = gameTitle;
        this.developer = developer;
        this.price = price;
        this.gbOfSpace = gbOfSpace;
        this.realeaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getGbOfSpace() {
        return gbOfSpace;
    }

    public void setGbOfSpace(float gbOfSpace) {
        this.gbOfSpace = gbOfSpace;
    }

    public Date getRealeaseDate() {
        return realeaseDate;
    }

    public void setRealeaseDate(Date realeaseDate) {
        this.realeaseDate = realeaseDate;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", gameTitle='" + gameTitle + '\'' +
                ", developer='" + developer + '\'' +
                ", price=" + price +
                ", gbOfSpace=" + gbOfSpace +
                ", realeaseDate=" + realeaseDate +
                '}';
    }
}
