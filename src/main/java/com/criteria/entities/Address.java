package com.criteria.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AddreshDetails")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "pinCode")
	private Integer pinCode;
	@Column(name = "placeName")
	private String placeName;
	@Column(name = "streatName")
	private String streatName;
	@Column(name = "landMark")
	private String landMark;
	@Column(name = "districtName")
	private String districtName;
	@Column(name = "stateName")
	private String stateName;
	@OneToOne
	@JoinColumn(name = "fk_productId")
	@JsonBackReference
	private Product product;

}
