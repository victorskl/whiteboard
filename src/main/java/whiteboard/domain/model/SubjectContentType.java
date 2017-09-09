package whiteboard.domain.model;

public enum SubjectContentType {
    LECTURE("Lecture", "lecture"),
    WORKSHOP("Workshop", "workshop"),
    ASSESSMENT("Assessment", "assessment");

    private String longName;
    private String shortName;

    SubjectContentType(String longName, String shortName) {
        this.longName = longName;
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }
}
