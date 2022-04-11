package ru.itmo.kotiki.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private Date birthDate;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = EAGER)
    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id && Objects.equals(name, owner.name) && Objects.equals(birthDate, owner.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
