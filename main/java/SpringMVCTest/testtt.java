package SpringMVCTest;

public class testtt {
String name ;
Integer age ;
	public testtt(){}
	public testtt(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "testtt [name=" + name + ", age=" + age + "]";
	}
	
}
