package pl.wspa.diploma.data.dao.enums;

public enum AdvertType {

    BUY("kupie"),
    SELL("sprzedam"),
    EXCHANGE("wymienie"),
    SEARCH("szukam "),
    SERVICE("usługa"),
    NA("inne");

    String advertTypeName;

    AdvertType(String advertTypeName) {
        this.advertTypeName = advertTypeName;
    }

    public String getAdvertTypeName() {
        return advertTypeName;
    }
}
