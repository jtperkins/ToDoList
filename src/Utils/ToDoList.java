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
    Scanner scan = new Scanner(System.in);
    Scanner read;
    FileWriter fw;
    SimpleDateFormat parser = new SimpleDateFormat("MMM dd, yyyy");
    Date date;
    String priority;
    String description;
    boolean completed;
    String dateString;
    public ToDoList() {
        super();
        new LinkedList<Task>();
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
        } catch (IOException e)
        {
            System.out.println("failed to close out file");
        }
    }

    public ToDoList<T> loadFile()
    {
        ToDoList list = new ToDoList();
        StringBuilder sb = new StringBuilder();
        try (Scanner read = new Scanner(new FileReader("ToDoList.txt"))) {

            while (read.hasNext()) {
                sb.append(read.nextLine());
                //System.out.println(sb.toString());
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
                /*for (String split : splitup)
                {
                    System.out.println(split);
                    desc
                }*/

                list.add(new Task(description, date, priority, completed));
                sb = new StringBuilder();
            }
            //System.out.println(sb);
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }

        return list;
    }

    public int binarySearch(LinkedList<Comparable> arr, Comparable key) {
        return binarySearch(arr, 0, arr.size() - 1, key);
    }

    private int binarySearch(LinkedList<Comparable> arr, int first, int last, Comparable key) {
        if (first > last) return -1;    //looked at everything

        // find middle element
        int mid = first + (last - first) / 2;

        // check if the middle element is the key
        if (arr.get(mid).compareTo(key) == 0) return mid;
        else if(arr.get(mid).compareTo(key) < 0) return binarySearch(arr, mid + 1, last, key);
        else return binarySearch(arr, first, mid - 1, key);
    }

    public Task sortedMerge(Task a, Task b)
    {
        Task result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.getPriority().equals(b.getPriority()))
        {
            result = a;

        }
        return null;
    }


}
