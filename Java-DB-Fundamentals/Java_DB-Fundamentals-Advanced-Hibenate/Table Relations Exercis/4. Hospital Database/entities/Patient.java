package entities;

import com.mysql.cj.jdbc.Blob;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Basic
    private String address;

    @Column(length = 50)
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(length = 65535)
    private byte[] picture;

    @Column(name = "medical_history")
    private int hasMedicalHistory;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

    @OneToMany(mappedBy = "patient")
    private Set<Diagnose> diagnoses;

    @OneToMany(mappedBy = "patient")
    private Set<MedicalPrescription> medicalPrescriptions;

    public Patient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getHasMedicalHistory() {
        return hasMedicalHistory;
    }

    public void setHasMedicalHistory(int hasMedicalHistory) {
        this.hasMedicalHistory = hasMedicalHistory;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<MedicalPrescription> getMedicalPrescriptions() {
        return medicalPrescriptions;
    }

    public void setMedicalPrescriptions(Set<MedicalPrescription> medicalPrescriptions) {
        this.medicalPrescriptions = medicalPrescriptions;
    }
}
