package cn.edu.ujn.ch9.dao;

public class Person {
    private Integer id;

    private String name;

    private Integer age;

    private Integer cardId;
    
    private IdCard card;
    
    public IdCard getCard() {
		return card;
	}

	public void setCard(IdCard card) {
		this.card = card;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
    
    @Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", cardId=" + cardId + ", card=" + card + "]";
	}
}