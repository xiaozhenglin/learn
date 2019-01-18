package com.xiaozl.learn.entity1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/21 下午3:35.
 * @blog http://blog.didispace.com
 */
@Entity
@Table(name="t_id_card")
public class User implements Serializable{

    @Id
    @GeneratedValue
    private int cid;

    private String card;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}


	


}
