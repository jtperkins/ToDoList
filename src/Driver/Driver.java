package Driver;

import Utils.Priority;
import Utils.Task;
import Utils.ToDoList;
import java.io.IOException;
import java.text.*;
import java.util.*;

public class Driver {

    public static void main(String[] args) {
        String fileName = "";
        String userInput = "";
        String description = "";
        String dateString;
        Date date = null;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        String priority;
        Scanner scan = new Scanner(System.in);
        int input;
        boolean quit = false;
        boolean modify;
        ToDoList<Task> list = new ToDoList<>();
        ToDoList<Task> low = new ToDoList<>();
        ToDoList<Task> medium = new ToDoList<>();
        ToDoList<Task> high = new ToDoList<>();


        while(!quit)
        {
            Menu();
            input = scan.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Add a task to the list");
                    System.out.print("Enter a description for the task: ");
                    while (description.equalsIgnoreCase(""))
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
                    list.add(new Task(description, date, priority, false));
                    description = "";

                    break;
                case 2: //modify
                    // I know there's a better way to do this, there has to be
                    modify = false;
                    userInput = "";
                    int i = 0;
                    System.out.print("Enter the name of the task you wish to modify: ");
                    while (userInput.equalsIgnoreCase(""))
                    userInput = scan.nextLine();
                    while(i < list.size())
                    {
                        if (list.get(i).getDescription().trim().equalsIgnoreCase(userInput.trim()))
                        {
                            userInput = "";
                            Task temp = list.get(i);
                            list.remove(i);
                            while(!modify)
                            {
                                System.out.println("What would you like to modify? ");
                                System.out.println("\t1. description");
                                System.out.println("\t2. due date");
                                System.out.println("\t3. priority");
                                System.out.println("\t4. did you complete the task?");
                                System.out.println("\t5. quit modifying");
                                input = scan.nextInt();

                                switch (input)
                                {
                                    case 1:
                                        System.out.println("enter new description: ");
                                        while (userInput.equalsIgnoreCase(""))
                                        userInput = scan.nextLine();
                                        temp.setDescription(userInput);
                                        break;
                                    case 2:
                                        System.out.println("Enter new due date in format yyyy-mm-dd: ");
                                        userInput = scan.next();
                                        try {
                                            date = parser.parse(userInput);
                                        } catch (ParseException e)
                                        {
                                            System.out.println("you didn't put the right format in");
                                        }
                                        temp.setDueDate(date);
                                        break;
                                    case 3:
                                        System.out.println("Enter new priority level (Low, Medium, High): ");
                                        userInput = scan.next();
                                        temp.setPriority(userInput);
                                        break;
                                    case 4:
                                        System.out.println("Completed? (Y/N): ");
                                        userInput = scan.next().toUpperCase();
                                        if (userInput.equals("Y"))
                                            temp.setCompleted(true);
                                        else
                                            temp.setCompleted(false);
                                        break;
                                    case 5:
                                        modify = true;
                                        break;

                                }
                                userInput = "";
                            }
                            if (modify)
                            {
                                list.add(temp);
                                userInput = "";
                                break;
                            }

                        }
                        else
                        {
                            i++;
                        }
                    }
                    break;
                case 3: //remove

                    System.out.print("Enter the name of the task you wish to remove: ");
                    while(userInput.equalsIgnoreCase(""))
                    userInput = scan.nextLine();

                    for (Iterator<Task> iterator = list.iterator(); iterator.hasNext(); ) {
                        Task temp = iterator.next();
                        if (temp.getDescription().trim().equalsIgnoreCase(userInput)) {
                            iterator.remove();
                            System.out.println("removed task successfully");
                        }
                    }

                    System.out.println();
                    list.displayList(list);

                    System.out.println("press any key to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    userInput = "";
                    break;
                case 4: //display by priority

                    for (Iterator<Task> iterator = list.iterator(); iterator.hasNext(); ) {
                        Task temp = iterator.next();
                        if (temp.getPriority() == Priority.Low) {
                            low.add(temp);
                        }
                        else if (temp.getPriority() == Priority.Medium) {
                            medium.add(temp);
                        }
                        else
                            high.add(temp);
                    }
                    list.removeAll(list);
                    list.addAll(low);
                    list.addAll(medium);
                    list.addAll(high);
                    low.clear();
                    medium.clear();
                    high.clear();

                    System.out.println();
                    list.displayList(list);

                    System.out.println("press any key to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5: //display by dueDate

                    Collections.sort(list, new Comparator<Task>() {
                        @Override
                        public int compare(Task o1, Task o2) {
                            return o1.getDueDate().compareTo(o2.getDueDate());
                        }
                    });

                    System.out.println();
                    list.displayList(list);

                    System.out.println("press any key to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6: //filter/unFilter complete tasks

                    System.out.println();
                    for (Task temp : list)
                    {
                        if (!temp.isCompleted()) {
                            System.out.print(temp.getDescription() + " | ");
                            System.out.print(temp.getPriority() + " | ");
                            System.out.print(temp.getDueDate() + " | ");
                            if (temp.isCompleted())
                                System.out.println("completed");
                            else
                                System.out.println("not completed");
                        }
                    }

                    System.out.println();
                    System.out.println("press any key to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7: //save
                    list.saveToFile(list);
                    list.closeFile();
                    break;
                case 8: //load

                    System.out.print("Enter the name of the file you wish to load: ");
                    while (fileName.equalsIgnoreCase(""))
                    fileName = scan.nextLine();
                    list = list.loadFile(fileName);
                    list.displayList(list);
                    System.out.println("press any key to continue...");


                    try {
                        System.in.read();
                        fileName = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    quit = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, Try again.");
                    break;
            }
        }
    }

    private static void Menu() {
        System.out.println("----- TO DO LIST MENU -----");
        System.out.println("\t1. Add a new task");
        System.out.println("\t2. Modify a task");
        System.out.println("\t3. Remove a task");
        System.out.println("\t4. Display tasks by priority");
        System.out.println("\t5. Display tasks by due date");
        System.out.println("\t6. Filter/Unfilter complete tasks");
        System.out.println("\t7. Save To Do List");
        System.out.println("\t8. Load To Do List");
        System.out.println("\t9. Quit Program");
        System.out.print("Enter option (1-9): ");
    }
}
