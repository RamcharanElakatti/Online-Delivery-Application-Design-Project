package com.OnlineDeliveryApplication.deliverymanagement;

import com.OnlineDeliveryApplication.model.User;

import java.sql.Date;

import org.apache.catalina.realm.JNDIRealm.User;

import com.OnlineDeliveryApplication.ordermanagement.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "delivery_personnel_id", nullable = false)
    private User deliveryPersonnel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date assignedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getDeliveryPersonnel() {
		return deliveryPersonnel;
	}

	public void setDeliveryPersonnel(User deliveryPersonnel) {
		this.deliveryPersonnel = deliveryPersonnel;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", status=" + status + "]";
	}

    // Getters and Setters
    
}

public enum DeliveryStatus {
    ASSIGNED, IN_TRANSIT, DELIVERED, FAILED;
}