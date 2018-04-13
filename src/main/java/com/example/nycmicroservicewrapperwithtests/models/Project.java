package com.example.nycmicroservicewrapperwithtests.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PROJECTS")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AGENCY_NAME")
    private String agencyName;

    @Column(name = "CONTACT_NAME")
    private String contactName;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "START_DATE")
    private String startDate;

    public Project(String agencyName, String contactName, String summary, String email, String tel, String startDate) {
        this.agencyName = agencyName;
        this.contactName = contactName;
        this.summary = summary;
        this.email = email;
        this.tel = tel;
        this.startDate = startDate;
    }
}
