package eu.maciejfijalkowski.kwejk2.model;

import javax.persistence.*;

@Entity
public class MemComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Mem mem;

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
