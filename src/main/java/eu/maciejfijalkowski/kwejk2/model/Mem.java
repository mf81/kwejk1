package eu.maciejfijalkowski.kwejk2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String url;

    @OneToMany(mappedBy = "mem")
    private List<MemComments> comments = new ArrayList();

     @OneToOne
    private MemsCategory memsCategory;

    public List<MemComments> getComments() {
        return comments;
    }
    

    public void setComments(List<MemComments> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MemsCategory getMemsCategory() {
        return memsCategory;
    }

    public void setMemsCategory(MemsCategory memsCategory) {
        this.memsCategory = memsCategory;
    }
}
