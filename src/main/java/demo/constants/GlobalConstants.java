package demo.constants;

public class GlobalConstants {

    public static final String PEOPLE_FILE_PATH = "src/main/resources/files/json/people.json";
    public static final String MAILS_FILE_PATH = "src/main/resources/files/json/mails.json";
    public static final String ADDRESSES_FILE_PATH = "src/main/resources/files/json/addresses.json";

    public static final String SUCCESSFULLY_IMPORTED = "Successfully imported %s ";

    public static final String NUMBER_VALIDATE_REGEX = "^\\d{10}$";
    public static final String NAME_VALIDATE_REGEX = "^[A-Za-z\\s\\-]+$";
    public static final String NAME_CYRILLIC_VALIDATE_REGEX = "[\\p{Cyrillic}]";

    public static final String EMAIL_VALIDATE_REGEX = "(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&" +
            "'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
            "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" +
            "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|" +
            "2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\" +
            "x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

}
