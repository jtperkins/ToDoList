package Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class ToDoList<T> extends LinkedList<T> {
    private FileWriter fw;
    private SimpleDateFormat parser = new SimpleDateFormat("MMM dd, yyyy");
    private Date date;
    private String priority;
    private String description;
    private boolean completed;

    public ToDoList() {
        super();
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
                fw.write(temp.getPriority() + " | ");
                fw.write(temp.getDueDate() + " | ");
                fw.write(String.valueOf(temp.isCompleted()));
                fw.append("\r\n");

            } catch (IOException e)
            {
                System.out.println("something went wrong with saving file");
            }
        }
    }

    public void closeFile()
    {
        try
        {
            fw.close();
            System.out.println("file saved successfully");
        } catch (IOException e)
        {
            System.out.println("failed to close out file");
        }
    }

    public ToDoList<T> loadFile(String fileName)
    {
        ToDoList list = new ToDoList();
        StringBuilder sb = new StringBuilder();
        try (Scanner read = new Scanner(new FileReader(fileName))) {

            while (read.hasNext()) {
                sb.append(read.nextLine());
                String[] splitup = sb.toString().split("\\|");
                description = splitup[0].trim();
                priority = splitup[1].trim();
                try {
                    date = parser.parse(splitup[2].trim());
                } catch (ParseException e) {
                    System.out.println("date format messed up");
                }
                if (splitup[3].trim().matches("true"))
                    completed = true;
                else
                    completed = false;

                list.add(new Task(description, date, priority, completed));
                sb = new StringBuilder();
            }
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }
        if (list.size() > 0)
        {
            System.out.println("file loaded successfully");
            System.out.println();
        }
        return list;
    }

    public void displayList(ToDoList<Task> list) {
        for (Task temp : list)
        {
            System.out.print(temp.getDescription() + " | ");
            System.out.print(temp.getPriority() + " | ");
            System.out.print(temp.getDueDate() + " | ");
            if (temp.isCompleted())
            System.out.println("completed");
            else
                System.out.println("not completed");

        }
        System.out.println();
    }
}
