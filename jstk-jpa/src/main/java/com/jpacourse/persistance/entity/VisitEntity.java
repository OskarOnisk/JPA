package com.jpacourse.persistance.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@Column
	private LocalDateTime time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private DoctorEntity doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private PatientEntity patient;

	@OneToMany(mappedBy = "visit", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicalTreatmentEntity> medicalTreatment;

	public List<MedicalTreatmentEntity> getMedicalTreatment() {
		return medicalTreatment;
	}

	public void setMedicalTreatment(List<MedicalTreatmentEntity> medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
