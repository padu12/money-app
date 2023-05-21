package ua.odessa.moneyApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Recording")
public class Recording {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne()
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person personId;
	
	@Column(name = "type")
	private String type;

	@Column(name = "month")
	private String month;

	@Column(name = "value")
	private int value;

	public Recording() {
		super();
	}

	public Recording(Person personId, String type, String month, int value) {
		this.personId = personId;
		this.type = type;
		this.month = month;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPersonId() {
		return personId;
	}

	public void setPersonId(Person personId) {
		this.personId = personId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
