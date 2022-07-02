package com.vti.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "`Order`")
public class Order{
	@EmbeddedId
	private OrderPK id;
	
	@Column(name = "title", length = 50, nullable = false, unique = true)
	private String title;
	
	public void setId(OrderPK orderPK) {
		this.id = orderPK;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Embeddable
	public static class OrderPK  implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "order_id")
		private int orderId;
		
		@Column(name = "product_id")
		private int productId;

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}
	}
}
