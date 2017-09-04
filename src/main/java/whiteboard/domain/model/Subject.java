package whiteboard.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Subjects")
public class Subject implements Serializable {

    @Id
    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    private String department;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    private List<SubjectCoordinator> coordinators;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Subject other = (Subject) object;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((code == null) ? 0 : code.hashCode());
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        return result;
    }
}
