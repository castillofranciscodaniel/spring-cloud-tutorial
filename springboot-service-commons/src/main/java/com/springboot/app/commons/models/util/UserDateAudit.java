package com.springboot.app.commons.models.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = { "createdBy", "updatedBy" }, allowGetters = true)
public abstract class UserDateAudit extends DateAudit {

	public UserDateAudit() {
		super();
	}

	public UserDateAudit(Long modelId) {
		super(modelId);
	}

	@CreatedBy
	@Column(name = "CREATED_BY", updatable = true)
	@NumberFormat
	@JsonProperty("created_by")
	private Long createdBy;

	@LastModifiedBy
	@Column(name = "UPDATED_BY")
	@NumberFormat
	@JsonProperty("updated_by")
	private Long updatedBy;
}
