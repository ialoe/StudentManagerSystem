package project.student.system.controller;

import project.student.system.bean.Student;
import project.student.system.myexception.IllegalCapacityException;
import project.student.system.myexception.OverflowArrayCapacityException;
import project.student.system.tools.StudentFilter;
import project.student.system.tools.Tools;
import project.student.system.tools.impl.MyCompareByStudentScore;

import java.util.Scanner;

/**
 * 学生管理类
 */
public class Controller {
    /*
     * 控制类的底层核心是一个学生类型的数组
     */
    private Student[] allStu = null;

    /*
     * 定义数组的默认容量为10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /*
     * 定义数组的最大容量为2^31-1
     *
     * 8需要留一部分空间保存其他相关数据
     */
    private static final int MAX_ARRAY_CAPACITY = Integer.MAX_VALUE - 8;

    // 表示当前数组中学生的数量，区别于数组的长度
    private int size = 0;

    /*
     * 无参构造方法，使用默认容量初始化
     */
    public Controller() {
        allStu = new Student[DEFAULT_CAPACITY];
    }

    /*
     * 有参构造，参数是我们手动指定的数组容量
     * 需要对其进行合法性判断
     */
    public Controller(int initCapacity) throws IllegalCapacityException {

        if (initCapacity < 0 || initCapacity > MAX_ARRAY_CAPACITY) {
            throw new IllegalCapacityException("传入了非法的数组容量");
        }

        allStu = new Student[initCapacity];
    }

    /**
     * 通过id获取对应学生在数组中的下标
     *
     * @param id 传入一个学生的id
     * @return 返回一个学生对应的下标，如果id不存在，返回-1
     */
    private int findIndexById(int id) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (id == allStu[i].getId()) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * 通过id获取对应的学生对象
     *
     * @param id 传入学生的id
     * @return 返回对应的学生对象，如果该学生不存在则返回null
     */
    public Student get(int id) {
        // 通过findIndexById方法得到id对应的学生下标
        int index = findIndexById(id);

        // 判断id是否存在，如果说id对应的下标小于0，说明学生不存在
        return 0 <= index ? allStu[index] : null;
    }

    /**
     * 添加方法，需要传入一个学生对象，成功返回true，否则返回false
     * @param student 学生对象
     * @return 成功返回true，否则返回false
     * @throws OverflowArrayCapacityException 超出数组的容量时会抛出此异常
     */
    public boolean add(Student student) throws OverflowArrayCapacityException {
        // 如果有效元素的个数等于当前数组的容量，需要扩容
        if (size == allStu.length) {
            // 扩容
            grow(size + 1);
        }

        // 自动生成一个id
        student.setId(creatId());

        // 添加学生对象到数组中，默认从0开始
        allStu[size] = student;

        // 有效元素的个数+1，代表我们的学生个数加1
        size++;

        return true;
    }

    /**
     * 扩容方法，对数组进行扩容，大约50%的容量
     * @param minCapacity 传入最小的容量
     * @throws OverflowArrayCapacityException 超出数组最大长度时抛出此异常
     */
    private void grow(int minCapacity) throws OverflowArrayCapacityException {
        // 获取原数组的容量
        int oldCapacity = allStu.length;

        // 定义新数组的容量
        int newCapacity = oldCapacity + oldCapacity / 2;

        // 新容量与最小容量比较，如果小于最小容量，就把最小容量的值传给新容量
        if (minCapacity > newCapacity) {
            newCapacity = minCapacity;
        }

        // 新容量与最大容量比较，如果超过了最大容量，就抛出异常
        if (newCapacity > MAX_ARRAY_CAPACITY) {
            throw new OverflowArrayCapacityException("超出最大容量异常");
        }

        // 根据新容量创建新数组
        Student[] temp = new Student[newCapacity];

        // 将原数组复制到新数组中
        System.arraycopy(allStu, 0, temp, 0, oldCapacity);

        // 将新数组的地址赋值给原数组
        allStu = temp;

        System.out.println("扩容完成，新的数组容量为：" + newCapacity);
    }

    /**
     * 获取数组中学生的个数
     * @return 返回学生的个数
     */
    public int size() {
        return this.size;
    }

    /**
     * 删除方法，通过传入的id删除对应的学生对象
     *
     * @param id 传入需要被删除学生的id
     * @return 删除成功返回true，否则返回false
     */
    public boolean remove(int id) {
        // 通过findIndexById()方法得到id所在的下标
        int index = findIndexById(id);

        /*
         * 如果下标为-1，说明没有找到id所在的学生对象，提供友好性提示并返回false
         */
        if (-1 == index) {
            System.out.println("查无此人!");
            return false;
        }

        /*
         * 后续学生对象前移覆盖前一个对象
         */
        for (int i = index; i < size - 1; i++) {
            allStu[i] = allStu[i + 1];
        }

        // 删除最后一个学生对象
        allStu[size - 1] = null;

        // 有效元素减1
        size--;

        return true;
    }

    /**
     * 修改方法，通过id修改对应的学生信息
     *
     * @param id 用户指定的id
     * @return 修改成功返回true，否则返回false
     */
    public boolean modify(int id) {
        // 用户友好性提示
        System.out.println("执行修改方法~~~");

        // 通过id获取对应的对象
        Student student = get(id);

        // 如果对象为null，返回false
        if (student == null) {
            System.out.println("Not Found！！！");
            return false;
        }

        // 定义一个变量接收控制台的录入
        int choose = 0;

        // 定义一个扫描器
        Scanner scanner = new Scanner(System.in);

        // 当控制台录入不等于5，循环执行，choose为5时退出循环
        while (choose != 5) {

            // 展示学生信息
            System.out.println(student);

            // 友好性提示
            System.out.println("1. 修改学生姓名");
            System.out.println("2. 修改学生年龄");
            System.out.println("3. 修改学生性别");
            System.out.println("4. 修改学生成绩");
            System.out.println("5. 退出");

            // 接收控制台录入
            choose = scanner.nextInt();
            // 控制台换行
            scanner.nextLine();

            // 根据 choose 来执行不同的修改项
            switch (choose) {
                case 1:
                    System.out.println("请输入学生姓名：");
                    student.setName(scanner.next());
                    scanner.nextLine();
                    break;

                case 2:
                    System.out.println("请输入学生年龄：");
                    student.setAge(scanner.nextInt());
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("请输入学生性别：");
                    student.setGender(scanner.nextLine().charAt(0));
                    break;

                case 4:
                    System.out.println("请输入学生成绩：");
                    student.setScore(scanner.nextFloat());
                    scanner.nextLine();
                    break;

                case 5:
                    System.out.println("退出");
                    break;

                default:
                    System.out.println("Input parameter is valied!!!");
                    break;
            }
        }

        scanner.close();

        System.out.println("修改成功");

        // 展示学生信息
        System.out.println(student);

        return true;
    }

    /**
     * 根据学生的成绩进行升序排序
     * 为了不影响原来的数组内容，需要创建一个新的数组对其进行操作
     */
    public void sortOfTools() {
        // 根据有效元素个数为容量创建新数组
        Student[] sortScoreArray = new Student[size];

        // 复制学生元素到新数组中
        for (int i = 0; i < sortScoreArray.length; i++) {
            sortScoreArray[i] = allStu[i];
        }

        /*
         * 此工具类中的静态方法用于比较学生的成绩并对其进行排序，算法使用选择排序
         *
         *  传入一个数组和对应的比较器，要求数组的类型和比较的类型必须一致
         */
        Tools.selectSort(sortScoreArray, new MyCompareByStudentScore());

        // 展示元素
        show(sortScoreArray);
    }

    /**
     * 过滤信息展示
     *
     * @param filter 传入一个接口实现类
     */
    public void accept(StudentFilter filter) {
        for (int i = 0; i < size; i++) {
            if(filter.accept(allStu[i])) {
                System.out.println(allStu[i]);
            }
        }
    }

    /**
     * 生成一个学生的id，用于给数组添加学生元素前的学生id赋值 如果数组中没有元素，则返回1，如果有元素，则返回最后一个有效元素的 id + 1
     *
     * @return 返回学生id
     */
    private int creatId() {
        // 初始id为1
        int id = 1;

        // 如果当前数组中的学生个数不为0，则获取到最后一个学生的id进行+1操作后作为新的id
        if (0 != this.size) {
            id = allStu[this.size - 1].getId() + 1;
        }

        return id;
    }

    /**
     * 展示所有学生元素
     */
    public void show() {
        for (int i = 0; i < size; i++) {
            System.out.println(allStu[i]);
        }
    }

    /**
     * 传入一个数组，展示数组内所有元素
     *
     * @param student 传入一个学生数组
     */
    private void show(Student[] student) {
        // 增强for循环
        for (Student student1 : student) {
            System.out.println(student1);
        }
    }
}
