package project.student.system.myexception;

/**
 * 自定义异常类，用于创建数组对象时对用户传入的容量进行检查
 */
public class IllegalCapacityException extends Exception{
    public IllegalCapacityException() {
        super();
    }

    public IllegalCapacityException(String message) {
        super(message);
    }
}