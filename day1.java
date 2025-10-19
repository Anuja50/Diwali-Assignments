import java.io.*;
import java.util.*;

// User-defined Exception for attendance below 60%
class LowAttendanceException extends Exception {
    public LowAttendanceException(String message) {
        super(message);
    }
}

class Student implements Serializable, Comparable<Student> {
    int rollno;
    String sname;
    String course;
    double attendance_percentage;
    double score;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int rollno, String sname, String course, double attendance_percentage, double score) {
        this.rollno = rollno;
        this.sname = sname;
        this.course = course;
        this.attendance_percentage = attendance_percentage;
        this.score = score;
    }

    // Method to calculate grade based on score
    public String calculateGrade() throws LowAttendanceException {
        if (attendance_percentage < 60.0) {
            throw new LowAttendanceException("Attendance below 60% for student: " + sname);
        }
        if (score >= 90)
            return "A";
        else if (score >= 80)
            return "B";
        else if (score >= 70)
            return "C";
        else if (score >= 60)
            return "D";
        else
            return "F";
    }

    // Implement Comparable for sorting by attendance descending
    public int compareTo(Student other) {
        return Double.compare(other.attendance_percentage, this.attendance_percentage);
    }

    @Override
    public String toString() {
        return "Student [rollno=" + rollno + ", name=" + sname + ", course=" + course +
               ", attendance=" + attendance_percentage + ", score=" + score + "]";
    }

    // Serialization helper method
    public static void saveStudentsToFile(List<Student> students, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(students);
        }
    }

    // Deserialization helper method
    public static List<Student> readStudentsFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Student>) in.readObject();
        }
    }

    // Example main to demonstrate
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        // Create 10 students with sample data
        studentList.add(new Student(1, "Alice", "Math", 95.0, 92.5));
        studentList.add(new Student(2, "Bob", "Physics", 55.0, 85.0)); // Low attendance
        studentList.add(new Student(3, "Carol", "Chemistry", 85.5, 78.0));
        studentList.add(new Student(4, "Dave", "Math", 62.0, 64.5));
        studentList.add(new Student(5, "Eve", "Physics", 90.0, 88.0));
        studentList.add(new Student(6, "Frank", "Chemistry", 75.0, 70.0));
        studentList.add(new Student(7, "Grace", "Math", 59.0, 80.0)); // Low attendance
        studentList.add(new Student(8, "Hank", "Physics", 88.0, 90.0));
        studentList.add(new Student(9, "Ivy", "Chemistry", 93.5, 95.0));
        studentList.add(new Student(10, "Jack", "Math", 68.0, 75.0));

        // Save to file
        String filename = "students.dat";

        try {
            for (Student s : studentList) {
                try {
                    System.out.println(s.sname + "'s grade: " + s.calculateGrade());
                } catch (LowAttendanceException e) {
                    System.out.println(e.getMessage());
                }
            }

            saveStudentsToFile(studentList, filename);
            System.out.println("Students saved to file.");

            // Read back and sort by attendance descending
            List<Student> loadedStudents = readStudentsFromFile(filename);
            Collections.sort(loadedStudents);

            System.out.println("\nStudents sorted by attendance (descending):");
            for (Student s : loadedStudents) {
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
