package com.neto.bemcontrolado.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Branch {

    @Id
    @SequenceGenerator(
            name = "branch_id_sequence",
            sequenceName = "branch_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "branch_id_sequence"
    )
    private Integer id;
    private String name;
    private String address;
    private String responsible;
    private String cnpj;

    public Branch(Integer id, String name, String address, String responsible, String cnpj) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.responsible = responsible;
        this.cnpj = cnpj;
    }

    public Branch() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id) && Objects.equals(name, branch.name) && Objects.equals(address, branch.address) && Objects.equals(responsible, branch.responsible) && Objects.equals(cnpj, branch.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, responsible, cnpj);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", responsible='" + responsible + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
