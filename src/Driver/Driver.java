package Driver;

import Utils.Priority;
import Utils.Task;
import Utils.ToDoList;

import java.text.*;
import java.util.*;

public class Driver {

    public static void main(String[] args) {
        String userInput = "";
        String description = "";
        String dateString;
        Date date = null;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat formatter = new SimpleDateFormat("mmm dd yyyy");
        //DateFormat out = new SimpleDateFormat("dd mmm yyyy");
        String priority;
        Scanner scan = new Scanner(System.in);
        int input;
        boolean quit = false;
        boolean modify = false;
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
                    dateString = "";
                    priority = "";


                    //list.add(new Task(description, date, priority, false));
                    //parser.applyPattern("MMM dd yyyy");
                    //System.out.println(list.get(0).getPriority());

                    break;
                case 2: //modify
                    // I know there's a better way to do this, there has to be
                    int i = 0;
                    System.out.print("Enter the name of the task you wish to modify: ");
                    while (userInput.equalsIgnoreCase(""))
                    userInput = scan.nextLine();
                    while(i < list.size())
                    {
                        if (list.get(i).getDescription().trim().equalsIgnoreCase(userInput.trim()))
                        {
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
                                        userInput = scan.next();
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
                            }
                            if (modify)
                            {
                                //modify = false;
                                list.add(temp);
                                userInput = "";
                                break;
                            }

                        }
                        else
                        {
                            //System.out.println("no such task");
                            i++;
                            //modify = false;
                            continue;
                        }

                    }


                    //list.remove(list.indexOf(list.fo))
                    break;
                case 3: //remove

                    System.out.print("Enter the name of the task you wish to remove: ");
                    while(userInput.equalsIgnoreCase(""))
                    userInput = scan.nextLine();
                    //System.out.println(userInput);

                    for (Iterator<Task> iterator = list.iterator(); iterator.hasNext(); ) {
                        Task temp = iterator.next();
                        if (temp.getDescription().trim().equalsIgnoreCase(userInput)) {
                            iterator.remove();
                        }
                    }

                    /*for (Task temp : list) {

                        if (temp.getDescription().trim().equalsIgnoreCase(userInput))
                        {
                            list.remove(temp);
                            System.out.println("Removed task from list");
                        }
                        *//*else
                            System.out.println("no such task");*//*

                    }*/
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

                    for (Task temp : list)
                    {
                        System.out.print(temp.getDescription() + " | ");
                        System.out.print(temp.getPriority() + " | ");
                        System.out.print(temp.getDueDate() + " | ");
                        System.out.println(temp.isCompleted() + " | ");
                    }

                    break;
                case 5: //display by dueDate

                    Collections.sort(list, new Comparator<Task>() {
                        @Override
                        public int compare(Task o1, Task o2) {
                            return o1.getDueDate().compareTo(o2.getDueDate());
                        }
                    });
                    for (Task temp : list)
                    {
                        System.out.print(temp.getDescription() + " | ");
                        System.out.print(temp.getPriority() + " | ");
                        System.out.print(temp.getDueDate() + " | ");
                        System.out.println(temp.isCompleted() + " | ");
                    }
                    break;
                case 6: //filter/unFilter complete tasks

                    for (Task temp : list)
                    {
                        if (temp.isCompleted()) {
                            System.out.print(temp.getDescription() + " | ");
                            System.out.print(temp.getPriority() + " | ");
                            System.out.print(temp.getDueDate() + " | ");
                            System.out.println(temp.isCompleted() + " | ");
                        }
                    }
                    break;
                case 7: //save
                    list.saveToFile(list);
                    list.closeFile();
                    break;
                case 8: //load
                    list = list.loadFile();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, Try again.");
                    break;

                //case 3: list.

            }
        }
    }

    public static void Menu() {
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
