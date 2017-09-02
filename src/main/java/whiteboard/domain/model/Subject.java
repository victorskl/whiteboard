package whiteboard.domain.model;

import java.util.List;

public class Subject {

    private String name;
    private String code;
    private List<SubjectCoordinator> coordinators;
    private String department;
    private List<Content> contents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SubjectCoordinator> getCoordinators() {
        return coordinators;
    }

    public void setCoordinators(List<SubjectCoordinator> coordinators) {
        this.coordinators = coordinators;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
