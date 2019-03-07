package Driver;

import Utils.Task;
import Utils.ToDoList;

import java.text.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        String userInput;
        String description;
        String dateString;
        Date date = null;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("mmm dd yyyy");
        DateFormat out = new SimpleDateFormat("dd mmm yyyy");
        String priority;
        Scanner scan = new Scanner(System.in);
        int input;
        boolean quit = false;
        boolean modify = false;
        ToDoList<Task> list = new ToDoList<>();


        while(!quit)
        {
            Menu();
            input = scan.nextInt();

            switch (input) {
                case 1: //add
                    list.add(list.addTask());
                    //list.add(new Task(description, date, priority, false));
                    //parser.applyPattern("MMM dd yyyy");

                    //System.out.println(list.get(0).getPriority());

                    break;
                case 2: //modify
                    // I know there's a better way to do this, there has to be
                    int i = 0;
                    System.out.print("Enter the name of the task you wish to modify: ");
                    userInput = scan.next();
                    while(i < list.size())
                    {
                        if (list.get(i).getDescription().equalsIgnoreCase(userInput))
                        {
                            Task temp = list.get(i);
                            list.remove(temp);
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
                                break;
                            }

                        }
                        else
                        {
                            System.out.println("no such task");
                            //modify = false;
                            break;
                        }

                    }


                    //list.remove(list.indexOf(list.fo))
                    break;
                case 3: //remove
                    //int index = 0;
                    System.out.print("Enter the name of the task you wish to remove: ");
                    userInput = scan.next();
                    for (Task temp : list) {

                        if (temp.getDescription().equalsIgnoreCase(userInput))
                        {
                            list.remove(temp);
                            System.out.println("Removed task from list");
                        }
                        else
                            System.out.println("no such task");
                    }
                    break;
                case 4: //display by priority
                    break;
                case 5: //display by dueDate
                    break;
                case 6: //filter/unFilter complete tasks
                    break;
                case 7: //save
                    list.saveToFile(list);
                    System.out.println("Saved to file");
                    break;
                case 8: //load
                    break;
                case 9: System.exit(0);
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
