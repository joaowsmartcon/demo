package com.exemplo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ES01DICL")
@SequenceGenerator(name = "co01dicl_seq", sequenceName = "es01dicl_co01dicl_seq", allocationSize = 1)
public class Teste {

		@Id
		@Column(name = "CO01DICL")
		@GeneratedValue(generator = "co01dicl_seq", strategy = GenerationType.SEQUENCE)
		private Long id;

		@Column(name = "NUA1DICL")
		private Integer classSequence;

		@Column(name = "CO00DICL")
		private Long classDiary;
}
