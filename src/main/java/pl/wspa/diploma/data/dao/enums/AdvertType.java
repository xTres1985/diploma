package pl.wspa.diploma.data.dao.enums;

public enum AdvertType {

    BUY("Buying"),
    SELL("Selling"),
    EXCHANGE("Will Exchange"),
    SEARCH("Searching for "),
    SERVICE("Offering Service"),
    NA("Not applicable");

    String advertTypeName;

    AdvertType(String advertTypeName) {
        this.advertTypeName = advertTypeName;
    }

    public String getAdvertTypeName() {
        return advertTypeName;
    }
}
