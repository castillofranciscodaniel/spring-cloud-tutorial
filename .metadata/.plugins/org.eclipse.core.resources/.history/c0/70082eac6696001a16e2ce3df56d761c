package com.springboot.app.products.models.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.app.products.models.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public abstract class DateAudit extends Model {

	public DateAudit() {
		super();
	}

	public DateAudit(Long modelId) {
		super(modelId);
	}

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date createAt;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date updatedAt;

}