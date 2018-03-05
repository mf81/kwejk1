package eu.maciejfijalkowski.kwejk2.model;

import javax.persistence.*;

@Entity
public class Mem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String url;

    @OneToOne
    private MemsCategory memsCategory;

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
