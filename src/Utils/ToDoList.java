package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class ToDoList<T> extends LinkedList<T> {
    Scanner scan = new Scanner(System.in);
    FileWriter fw;
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    String priority;
    String description;
    String dateString;
    public ToDoList() {
        super();
        new LinkedList<Task>();
    }

    public Task addTask() {
        System.out.println("Add a task to the list");
        System.out.println("Enter a description for the task: ");
        description = scan.nextLine();
        System.out.println("Enter the date in the format yyyy-mm-dd: ");
        dateString = scan.next();
        try {
            date = parser.parse(dateString);
        } catch (ParseException e)
        {
            System.out.println("you didn't put the right format in");
        }
        System.out.println("Enter the priority i.e. Low, Medium, or High");
        priority = scan.next();

        return new Task(description, date, priority, false);
    }

    public Task modifyTask(int index) {
        return null;
    }

    public void saveToFile(ToDoList<Task> list)
    {
        try
        {
            fw = new FileWriter("ToDoList.txt");
        } catch (IOException e)
        {
            System.out.println("something went wrong with making file");
        }
        for (Task temp : list)
        {
            try
            {
                fw.write(temp.getDescription() + " | ");
                fw.write(String.valueOf(temp.getPriority()) + " | ");
                fw.write(temp.getDueDate() + " | ");
                fw.write("Completed? " + String.valueOf(temp.isCompleted()));
                fw.close();
            } catch (IOException e)
            {
                System.out.println("something went wrong with saving file");
            }
        }
    }
}
