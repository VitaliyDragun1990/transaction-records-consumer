package org.vdragun.transactionconsumer.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement(name = "client")
public class ClientDto {

    private String firstName;

    private String lastName;

    private String middleName;

    private Integer inn;

    public ClientDto() {
    }

    public ClientDto(String firstName, String lastName, String middleName, Integer inn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.inn = inn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientDto clientDto = (ClientDto) o;

        return Objects.equals(firstName, clientDto.firstName)
                && Objects.equals(lastName, clientDto.lastName)
                && Objects.equals(middleName, clientDto.middleName)
                && Objects.equals(inn, clientDto.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, inn);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientDto.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("middleName='" + middleName + "'")
                .add("inn=" + inn)
                .toString();
    }
}
