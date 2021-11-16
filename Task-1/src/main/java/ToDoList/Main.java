package ToDoList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    // Разбиение пользовательского ввода на команду и ее аргумент.
    // Возвращает: [0] - команда, [1] - аргумент
    private static String[] SepInput(String input) {
        String[] out = new String[] {"", "", ""};
        String[] split_arr;
        input = input.trim();
        split_arr = input.split(" ", 2);
        for(int i = 0; i < split_arr.length; i++) {
            out[i] = split_arr[i].trim();
        }
        if(out[0].equals("edit")) {
            split_arr = out[1].split("", 2);
            out[1] = split_arr[0].trim();
            out[2] = split_arr[1].trim();
        }
        return (out);
    }

    private static void Print(String output) {
        System.out.println(output);
    }

    public static void main( String[] args ) {
        boolean working = true;

        ArrayList<Task> tasklist = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        int id_counter = 0;

        Print("ToDo List");

        while(working) {
            String input_cmd;
            String[] cmd_list = new String[2];

            System.out.print("Input Your command: ");
            input_cmd = in.nextLine();
            cmd_list = SepInput(input_cmd);

            switch(cmd_list[0]) {
                case "add":
                    if(cmd_list[1].isEmpty()) {
                        Print("Описание задачи отсутствует");
                    }
                    else {
                        tasklist.add(new Task(cmd_list[1], ++id_counter));
                    }
                    break;
                case "print":
                    if(!tasklist.isEmpty()) {
                        if (cmd_list[1].isEmpty()) {
                            long count = tasklist.stream()
                                    .filter(t -> !t.GetStatus())
                                    .count();
                            if (count != 0) {
                                tasklist.stream()
                                        .filter(t -> !t.GetStatus())
                                        .forEach(t -> Print(t.Out()));
                            } else {
                                Print("Невыполненных задач нет");
                            }
                        } else {
                            if (cmd_list[1].equals("all")) {
                                tasklist.stream()
                                        .forEach(t -> Print(t.Out()));
                            } else {
                                Print("Недопустимый аргумент для команды print");
                            }
                        }
                    } else {
                        Print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "toggle":
                    if(!tasklist.isEmpty()) {
                        try {
                            int id = Integer.parseInt(cmd_list[1]);
                            if(id > 0 && id <= id_counter) {
                                Optional<Task> task_temp = tasklist.stream()
                                                                    .filter(t -> t.GetId() == id)
                                                                    .findFirst();
                                if(task_temp.isPresent()){
                                    Task task = task_temp.get();
                                    task.ChangeStatus();
                                    tasklist.set(tasklist.indexOf(task), new Task(task));
                                } else {
                                    Print("Задача с указанным идентификатором отсутствует в списке");
                                }
                            } else {
                                Print("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch(NumberFormatException nfe) {
                            Print("Отсутствует или неверно введен идентификатор задачи");
                        }
                    } else {
                        Print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "delete":
                    if(!tasklist.isEmpty()) {
                        try {
                            int id = Integer.parseInt(cmd_list[1]);
                            if(id > 0 && id <= id_counter) {
                                Optional<Task> task_temp = tasklist.stream()
                                                                    .filter(t -> t.GetId() == id)
                                                                    .findFirst();
                                if(task_temp.isPresent()) {
                                    tasklist.remove(tasklist.indexOf(task_temp.get()));
                                } else {
                                    Print("Задача с указанным идентификатором отсутствует в списке");
                                }
                            } else {
                                Print("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch(NumberFormatException nfe) {
                            Print("Отсутствует или неверно введен идентификатор задачи");
                        }
                    } else {
                        Print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "edit":
                    if(!tasklist.isEmpty()) {
                        try {
                            int id = Integer.parseInt(cmd_list[1]);
                            if(id > 0 && id <= id_counter) {
                                Optional<Task> task_temp = tasklist.stream()
                                                                    .filter(t -> t.GetId() == id)
                                                                    .findFirst();
                                if(task_temp.isPresent()) {
                                    Task task = task_temp.get();
                                    task.SetDescription(cmd_list[2]);
                                    tasklist.set(tasklist.indexOf(task), new Task(task));
                                } else {
                                    Print("Задача с указанным идентификатором отсутствует в списке");
                                }
                            } else {
                                Print("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch (NumberFormatException nfe) {
                            Print("Отсутствует или неверно введен идентификатор задачи");
                        }
                    } else {
                        Print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "search":
                    if(!tasklist.isEmpty()) {
                        if(cmd_list[1].isEmpty()) {
                            Print("Пустой поисковый запрос недопустим");
                        } else {
                            long count = tasklist.stream()
                                    .filter(t -> !t.GetStatus())
                                    .count();
                            if (count != 0) {
                                String charline = cmd_list[1].trim();
                                tasklist.stream()
                                        .filter(t -> !t.GetStatus())
                                        .filter(t -> t.GetDescription().contains(charline))
                                        .forEach(t -> Print(t.Out()));
                            } else {
                                Print("Невыполненных задач нет");
                            }
                        }
                    } else {
                        Print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "quit":
                    working = false;
                    break;
                default:
                    Print("Пустая или неизвестная команда!");
                    break;
            }
        }
    }
}