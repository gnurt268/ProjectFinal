package org.trung.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(max=50)
    private String name;
    @ManyToOne
    @JoinColumn(name="parent_category_id")
    private Category parentCategory;

    private int level;
    public Category() {

    }
}
