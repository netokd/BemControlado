package com.neto.bemcontrolado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Inventory {

    @Id
    @SequenceGenerator(
            name = "inventory_id_sequence",
            sequenceName = "inventory_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,

            generator = "inventory_id_sequence"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


    @OneToMany(mappedBy = "inventory")
    private List<Product> products;

    public Inventory(Integer id, Branch branch, List<Product> products) {
        this.id = id;
        this.branch = branch;
        this.products = products;
    }

    public Inventory() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(id, inventory.id) && Objects.equals(branch, inventory.branch) && Objects.equals(products, inventory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch, products);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", branch=" + branch +
                ", products=" + products +
                '}';
    }
}
