package br.com.achaweb.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_groupcategory")
public class GroupCategory  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "groupCategories")
    private Set<Region> regions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_groupcategory_category",
            joinColumns = @JoinColumn(name = "groupcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<GroupCategory> groupCategories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public Set<GroupCategory> getGroupCategories() {
        return groupCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCategory that = (GroupCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
