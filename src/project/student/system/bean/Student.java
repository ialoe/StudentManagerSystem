package project.student.system.bean;

/**
 * 学生类
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private char gender;
    private float score;

    public Student() {
    }

    public Student(String name, int age, char gender, float score) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", score=" + score +
                '}';
    }
}









