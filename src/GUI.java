import net.sf.clipsrules.jni.CLIPSException;
import net.sf.clipsrules.jni.CaptureRouter;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class GUI {
    static JFrame mainFrame = null;
    static String depName = "";
    static String stuName = "";
    static String fc = "";
    static String fl = "";
    static String univ = "";
    public static void startGUI() {
        mainFrame = new JFrame();
        mainFrame.setSize(3000, 3000);
        JPanel panel = new JPanel(null);


        JLabel i1 = new JLabel("Enter Department Name");
        i1.setBounds(50, 50, 200, 30);
        JTextField tf1 = new JTextField();
        tf1.setBounds(50, 100, 500, 30);

        JLabel i2 = new JLabel("Enter Student Name");
        i2.setBounds(50, 150, 200, 30);
        JTextField tf2 = new JTextField();
        tf2.setBounds(50, 200, 500, 30);

        JLabel i3 = new JLabel("Enter Faculty Name's to get its courses");
        i3.setBounds(50, 250, 500, 30);
        JTextField tf3 = new JTextField();
        tf3.setBounds(50, 300, 500, 30);

        JLabel i4 = new JLabel("Enter Faculty Name's to get its lectures");
        i4.setBounds(50, 350, 500, 30);
        JTextField tf4 = new JTextField();
        tf4.setBounds(50, 400, 500, 30);

        JLabel i5 = new JLabel("Enter University Name's to get its faculties");
        i5.setBounds(50, 450, 500, 30);
        JTextField tf5 = new JTextField();
        tf5.setBounds(50, 500, 500, 30);

        JButton startButton = new JButton("Start");
        startButton.setSize(500, 50);
        startButton.setLocation(350, 600);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                depName = tf1.getText();
                stuName = tf2.getText();
                fc = tf3.getText();
                fl = tf4.getText();
                univ = tf5.getText();
                try {
                    start();
                } catch (CLIPSException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(i1);
        panel.add(tf1);
        panel.add(i2);
        panel.add(tf2);
        panel.add(i3);
        panel.add(tf3);
        panel.add(i4);
        panel.add(tf4);
        panel.add(i5);
        panel.add(tf5);
        panel.add(startButton);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
    }

    public static void start() throws CLIPSException {
        Environment clips = new Environment();
        clips.clear();
        CaptureRouter router = new CaptureRouter(clips, new String[] {Router.STDOUT});
        clips.loadFromString("(defclass LabRoom\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot labRoomCapacity\n" +
                "\t\t(type INTEGER)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot labRoomID\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Student\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot take\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Course)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot hasExam\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Exam)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot studentID\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot studentGPA\n" +
                "\t\t(type FLOAT)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot studentAge\n" +
                "\t\t(type INTEGER)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(slot studentName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Library\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete))\n" +
                "\n" +
                "(defclass University\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot hasFaculty\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Faculty)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t\t(multislot hasLibrary\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Library)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot universityName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Exam\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot examName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Faculty\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(slot facultyName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot hasLecturer\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Lecturer)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot hasLectureHall\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes LectureHall)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot offer\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Course)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot hasLabRoom\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes LabRoom)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Course\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot hasCourseWork\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes CourseWork)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot courseName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot courseTimespan\n" +
                "\t\t(type INTEGER)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Thesis\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot thesisName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot thesisField\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass AcademicStaff\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot staffName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot staffAge\n" +
                "\t\t(type INTEGER)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot prepareThesis\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Thesis)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot teachCourse\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes Course)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot staffSalary\n" +
                "\t\t(type INTEGER)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass Lecturer\n" +
                "\t(is-a AcademicStaff)\n" +
                "\t(role concrete))\n" +
                "\n" +
                "(defclass TA\n" +
                "\t(is-a AcademicStaff)\n" +
                "\t(role concrete))\n" +
                "\n" +
                "(defclass Department\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot hasA\n" +
                "\t\t(type INSTANCE)\n" +
                "\t\t(allowed-classes TA)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(slot departmentName (allowed-values CS IS IT DS AI)))\n" +
                "\n" +
                "(defclass LectureHall\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(multislot hallCapacity\n" +
                "\t\t(type INTEGER)\n" +
                "\t\t(create-accessor read-write))\n" +
                "\t(multislot lectureHallName\n" +
                "\t\t(type STRING)\n" +
                "\t\t(create-accessor read-write)))\n" +
                "\n" +
                "(defclass CourseWork\n" +
                "\t(is-a USER)\n" +
                "\t(role concrete)\n" +
                "\t(slot courseWorkTotal (type INTEGER)))\n" +
                "\n" +
                "\n" +
                "(definstances all\n" +
                "\n" +
                "\t(CourseWork1 of  CourseWork \n" +
                "\n" +
                "\t\t(courseWorkTotal  40))\n" +
                "\n" +
                "\t(CourseWork2 of  CourseWork \n" +
                "\n" +
                "\t\t(courseWorkTotal  40))\n" +
                "\n" +
                "\t(CourseWork3 of  CourseWork \n" +
                "\n" +
                "\t\t(courseWorkTotal  40))\n" +
                "\n" +
                "\t(CourseWork4 of  CourseWork \n" +
                "\n" +
                "\t\t(courseWorkTotal  40))\n" +
                "\n" +
                "\t(Course1 of  Course \n" +
                "\n" +
                "\t\t(courseName \"Introduction to computer science\")\n" +
                "\t\t(courseTimespan  12)\n" +
                "\t\t(hasCourseWork CourseWork1))\n" +
                "\n" +
                "\t(Course2 of  Course \n" +
                "\n" +
                "\t\t(courseName \"Introduction to engineering\")\n" +
                "\t\t(courseTimespan  8)\n" +
                "\t\t(hasCourseWork CourseWork2))\n" +
                "\n" +
                "\t(Course3 of  Course \n" +
                "\n" +
                "\t\t(courseName \"Introduction to economics\")\n" +
                "\t\t(courseTimespan  6)\n" +
                "\t\t(hasCourseWork CourseWork3))\n" +
                "\n" +
                "\t(Course4 of  Course \n" +
                "\n" +
                "\t\t(courseName \"Introduction to politics\")\n" +
                "\t\t(courseTimespan  10)\n" +
                "\t\t(hasCourseWork CourseWork4))\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t(Department1 of  Department \n" +
                "\n" +
                "\t\t(departmentName \"CS\")\n" +
                "\t\t(hasA TA1)\n" +
                "\t\t(departmentName \"CS\"))\n" +
                "\n" +
                "\t(Department2 of  Department \n" +
                "\n" +
                "\t\t(departmentName \"IS\")\n" +
                "\t\t(hasA TA2)\n" +
                "\t\t(departmentName \"IS\"))\n" +
                "\n" +
                "\t(Department3 of  Department \n" +
                "\n" +
                "\t\t(departmentName \"IT\")\n" +
                "\t\t(hasA TA3)\n" +
                "\t\t(departmentName \"IT\"))\n" +
                "\n" +
                "\t(Department4 of  Department \n" +
                "\n" +
                "\t\t(departmentName \"DS\")\n" +
                "\t\t(hasA TA4)\n" +
                "\t\t(departmentName \"DS\"))\n" +
                "\n" +
                "\t(Department5 of  Department \n" +
                "\n" +
                "\t\t(departmentName \"AI\")\n" +
                "\t\t(hasA TA5)\n" +
                "\t\t(departmentName \"AI\"))\n" +
                "\n" +
                "\t(Exam1 of  Exam \n" +
                "\n" +
                "\t\t(examName \"Introduction to Computer Science Exam\"))\n" +
                "\n" +
                "\t(Exam2 of  Exam \n" +
                "\n" +
                "\t\t(examName \"Introduction to Engineering Exam\"))\n" +
                "\n" +
                "\t(Exam3 of  Exam \n" +
                "\n" +
                "\t\t(examName \"Introduction to Economics Exam\"))\n" +
                "\n" +
                "\t(Exam4 of  Exam \n" +
                "\n" +
                "\t\t(examName \"Introduction to Politics Exam\"))\n" +
                "\n" +
                "\t(Faculty1 of  Faculty \n" +
                "\n" +
                "\t\t(facultyName \"Computer Science\")\n" +
                "\t\t(hasLabRoom LabRoom1)\n" +
                "\t\t(hasLectureHall LectureHall1)\n" +
                "\t\t(hasLecturer Lecturer1)\n" +
                "\t\t(offer Course1))\n" +
                "\n" +
                "\t(Faculty2 of  Faculty \n" +
                "\n" +
                "\t\t(facultyName \"Engineering\")\n" +
                "\t\t(hasLabRoom LabRoom2)\n" +
                "\t\t(hasLectureHall LectureHall2)\n" +
                "\t\t(hasLecturer Lecturer2)\n" +
                "\t\t(offer Course2))\n" +
                "\n" +
                "\t(Faculty3 of  Faculty \n" +
                "\n" +
                "\t\t(facultyName \"Economics\")\n" +
                "\t\t(hasLecturer Lecturer3)\n" +
                "\t\t(offer Course3))\n" +
                "\n" +
                "\t(Faculty4 of  Faculty \n" +
                "\n" +
                "\t\t(facultyName \"Political Science\")\n" +
                "\t\t(hasLecturer Lecturer4)\n" +
                "\t\t(offer Course4)\n" +
                "\t\t)\n" +
                "\n" +
                "\t(LabRoom1 of  LabRoom \n" +
                "\t\t(labRoomCapacity 50)\n" +
                "\t\t(labRoomID \"fx21\"))\n" +
                "\t(LabRoom2 of  LabRoom \n" +
                "\t\t(labRoomCapacity 40)\n" +
                "\t\t(labRoomID \"fx22\"))\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t(LectureHall1 of  LectureHall \n" +
                "\n" +
                "\t\t(hallCapacity  400)\n" +
                "\t\t(lectureHallName \"Ibrahim Farag hall\"))\n" +
                "\n" +
                "\t(LectureHall2 of  LectureHall \n" +
                "\n" +
                "\t\t(hallCapacity  500)\n" +
                "\t\t(lectureHallName \"Sayed Zayan hall\"))\n" +
                "\n" +
                "\t(LectureHall3 of  LectureHall \n" +
                "\n" +
                "\t\t(hallCapacity  600)\n" +
                "\t\t(lectureHallName \"Roushdy Abaza hall\"))\n" +
                "\n" +
                "\t(LectureHall4 of  LectureHall \n" +
                "\n" +
                "\t\t(hallCapacity  700)\n" +
                "\t\t(lectureHallName \"Faten Hamama hall\"))\n" +
                "\n" +
                "\n" +
                "\t(Lecturer1 of  Lecturer \n" +
                "\n" +
                "\t\t(staffAge  33)\n" +
                "\t\t(staffName \"Ahmed Samir\")\n" +
                "\t\t(staffSalary  10000)\n" +
                "\t\t(teachCourse Course1))\n" +
                "\n" +
                "\t(Lecturer2 of  Lecturer \n" +
                "\n" +
                "\t\t(prepareThesis Thesis2)\n" +
                "\t\t(staffAge  43)\n" +
                "\t\t(staffName \"Samir Sabry\")\n" +
                "\t\t(staffSalary  12000)\n" +
                "\t\t(teachCourse Course2))\n" +
                "\n" +
                "\t(Lecturer3 of  Lecturer \n" +
                "\n" +
                "\t\t(staffAge  36)\n" +
                "\t\t(staffName \"Sara Emad\")\n" +
                "\t\t(staffSalary  15000)\n" +
                "\t\t(teachCourse Course3))\n" +
                "\n" +
                "\t(Lecturer4 of  Lecturer \n" +
                "\n" +
                "\t\t(staffAge  44)\n" +
                "\t\t(staffName \"Hana ramy\")\n" +
                "\t\t(staffSalary  17000)\n" +
                "\t\t(teachCourse Course4))\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t(Library1 of  Library )\n" +
                "\n" +
                "\t(Library2 of  Library )\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t(Student1 of  Student \n" +
                "\n" +
                "\t\t(hasExam Exam1)\n" +
                "\t\t(studentAge  19)\n" +
                "\t\t(studentGPA 2.9)\n" +
                "\t\t(studentID \"20290288\")\n" +
                "\t\t(studentName \"Hany Ramzy\")\n" +
                "\t\t(take Course1))\n" +
                "\n" +
                "\t(Student2 of  Student \n" +
                "\n" +
                "\t\t(hasExam Exam2)\n" +
                "\t\t(studentAge  21)\n" +
                "\t\t(studentGPA 3.2)\n" +
                "\t\t(studentID \"20290477\")\n" +
                "\t\t(studentName \"Harry Potter\")\n" +
                "\t\t(take Course2))\n" +
                "\n" +
                "\t(Student3 of  Student \n" +
                "\n" +
                "\t\t(hasExam Exam3)\n" +
                "\t\t(studentAge  22)\n" +
                "\t\t(studentGPA 2.7)\n" +
                "\t\t(studentID \"20290388\")\n" +
                "\t\t(studentName \"Cathie Wood\")\n" +
                "\t\t(take Course3))\n" +
                "\n" +
                "\t(Student4 of  Student \n" +
                "\n" +
                "\t\t(hasExam Exam4)\n" +
                "\t\t(studentAge  23)\n" +
                "\t\t(studentGPA 3.99)\n" +
                "\t\t(studentID \"20290001\")\n" +
                "\t\t(studentName \"Warren Buffet\")\n" +
                "\t\t(take Course4))\n" +
                "\n" +
                "\n" +
                "\t(TA1 of  TA \n" +
                "\n" +
                "\t\t(prepareThesis Thesis1)\n" +
                "\t\t(staffAge  25)\n" +
                "\t\t(staffName \"Sayed Aly\")\n" +
                "\t\t(staffSalary  7000)\n" +
                "\t\t(teachCourse Course1))\n" +
                "\n" +
                "\t(TA2 of  TA \n" +
                "\t\t(staffAge  24)\n" +
                "\t\t(staffName \"Mariam Ahmed\")\n" +
                "\t\t(staffSalary  6000)\n" +
                "\t\t(teachCourse Course2))\n" +
                "\n" +
                "\t(TA3 of  TA \n" +
                "\n" +
                "\t\t(staffAge  26)\n" +
                "\t\t(staffName \"Ahmed Mohamed\")\n" +
                "\t\t(staffSalary  5000)\n" +
                "\t\t(teachCourse Course3))\n" +
                "\n" +
                "\t(TA4 of  TA \n" +
                "\n" +
                "\t\t(staffAge  28)\n" +
                "\t\t(staffName \"Mahmoud Sayed\")\n" +
                "\t\t(staffSalary  9000))\n" +
                "\n" +
                "\t(TA5 of  TA \n" +
                "\n" +
                "\t\t(staffAge  29)\n" +
                "\t\t(staffName \"Samira Saeed\")\n" +
                "\t\t(staffSalary  10000))\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t(Thesis1 of  Thesis \n" +
                "\n" +
                "\t\t(thesisField \"AI\")\n" +
                "\t\t(thesisName \"How to optimize Neural networks\"))\n" +
                "\n" +
                "\t(Thesis2 of  Thesis \n" +
                "\n" +
                "\t\t(thesisField \"Robotics\")\n" +
                "\t\t(thesisName \"How to optimize robots\"))\n" +
                "\n" +
                "\t(Uni1 of  University \n" +
                "\n" +
                "\t\t(hasFaculty\n" +
                "\t\t\tFaculty1\n" +
                "\t\t\tFaculty2)\n" +
                "\t\t(hasLibrary Library1)\n" +
                "\t\t(universityName \"Cairo University\"))\n" +
                "\n" +
                "\t(Uni2 of  University \n" +
                "\n" +
                "\t\t(hasFaculty\n" +
                "\t\t\tFaculty3\n" +
                "\t\t\tFaculty4)\n" +
                "\t\t(hasLibrary Library2)\n" +
                "\t\t(universityName \"Helwan University\"))\n" +
                ")\n" +
                "\n" +
                "(defglobal ?*department* = \""+depName+"\")\n" +
//                "(bind ?*department* "+depName+")\n" +
                "\n" +
                "(defglobal ?*student* = \""+stuName+"\")\n" +
//                "(bind ?*student* "+stuName+")\n" +
                "\n" +
                "(defglobal ?*faculty* = \""+fc+"\")\n" +
//                "(bind ?*faculty* "+fc+")\n" +
                "\n" +
                "(defglobal ?*university* = \""+univ+"\")\n" +
//                "(bind ?*university* "+univ+")\n" +
                "\n" +
                "(defrule query1\n" +
                "\t(object (is-a Department) (hasA ?ta)(departmentName ?dn))\n" +
                "\t=>\n" +
                "\t(if (eq ?dn ?*department*)\n" +
                "\t\tthen\n" +
                "\t\t(printout t \"Department \" ?dn \" has TA \" ?ta crlf)\n" +
                "\t)\n" +
                ")\n" +
                "\n" +
                "(defrule query2\n" +
                "\t(object (is-a Student) (take ?course)(studentName ?sn))\n" +
                "\t=>\n" +
                "\t(if (eq ?sn ?*student*)\n" +
                "\t\tthen\n" +
                "\t\t(printout t \"Student \" ?sn \" take course \" ?course crlf)\n" +
                "\t)\n" +
                ")\n" +
                "\n" +
                "(defrule query3\n" +
                "\t(object (is-a Faculty) (offer ?course)(facultyName ?fn))\n" +
                "\t=>\n" +
                "\t(if (eq ?fn ?*faculty*)\n" +
                "\t\tthen\n" +
                "\t\t(printout t \"Faculty \" ?fn \" offers course \" ?course crlf)\n" +
                "\t)\n" +
                ")\n" +
                "\n" +
                "(defrule query4\n" +
                "\t(object (is-a Faculty) (hasLecturer ?lecturer)(facultyName ?fn))\n" +
                "\t=>\n" +
                "\t(if (eq ?fn ?*faculty*)\n" +
                "\t\tthen\n" +
                "\t\t(printout t \"Faculty \" ?fn \" has lecturer \" ?lecturer crlf)\n" +
                "\t)\n" +
                ")\n" +
                "\n" +
                "(defrule query5\n" +
                "\t(object (is-a University) (hasFaculty ?faculty)(universityName ?un))\n" +
                "\t=>\n" +
                "\t(if (eq ?un ?*university*)\n" +
                "\t\tthen\n" +
                "\t\t(printout t \"University \" ?un \" has faculty \" ?faculty crlf)\n" +
                "\t)\n" +
                ")");
        clips.reset();
        clips.run();
        String returnedString = router.getOutput();
        String[] list = returnedString.split("\n");
        mainFrame = new JFrame();
        mainFrame.setSize(2000, 2000);
        JPanel panel = new JPanel(new GridLayout(20, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        panel.add(new JLabel("Output", SwingConstants.CENTER));
        for (String l : list) {
            panel.add(new JLabel(l));
        }
        mainFrame.setVisible(true);
        mainFrame.add(panel);
    }

    public static void main(String[] args) {
        startGUI();
    }
}
