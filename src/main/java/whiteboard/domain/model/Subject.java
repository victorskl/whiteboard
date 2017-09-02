package whiteboard.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Subjects")
public class Subject {

    @Id
    private String code;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<SubjectCoordinator> coordinators;
    private String department;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
