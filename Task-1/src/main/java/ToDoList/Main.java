package ToDoList;

import java.util.Scanner;

public class Main {

    // Разбиение пользовательского ввода на команду и ее аргумент.
    // Возвращает: [0] - команда, [1] - аргумент
    private static String[] SepInput(String input) {
        String[] out = new String[] {"", ""};
        String[] split_arr;
        input = input.trim();
        split_arr = input.split(" ", 2);
        for(int i = 0; i < split_arr.length; i++) {
            out[i] = split_arr[i].trim();
        }
        return (out);
    }

    public static void main( String[] args ) {
        boolean working = true;
        boolean any_task = false;

        Task todo = null;
        Scanner in = new Scanner(System.in);

        System.out.println("ToDo List");

        while(working) {
            String input_cmd;
            String[] cmd_list = new String[2];

            System.out.print("Input Your command: ");
            input_cmd = in.nextLine();
            cmd_list = SepInput(input_cmd);

            if(any_task) {
                switch (cmd_list[0]) {
                    case "add":
                        if (cmd_list[1].isEmpty()) {
                            System.out.println("Описание задачи отсутствует");
                        } else {
                            todo.SetDescription(cmd_list[1]);
                            todo.SetStatus(false);
                        }
                        break;
                    case "print":
                        if (cmd_list[1].isEmpty()) {
                            System.out.println(todo.Out());
                        } else {
                            if (cmd_list[1].equals("all")) {
                                System.out.println(todo.Out());
                            } else {
                                System.out.println("Недопустимый аргумент для команды print");
                            }
                        }
                        break;
                    case "toggle":
                        try {
                            int id = Integer.parseInt(cmd_list[1]);
                            if (id == 1) {
                                todo.ChangeStatus();
                            } else {
                                System.out.println("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Отсутствует или неверно введен идентификатор задачи");
                        }
                        break;
                    case "quit":
                        working = false;
                        break;
                    default:
                        System.out.println("Пустая или неизвестная команда!");
                        break;
                }
            }
            else {
                if(cmd_list[0].equals("add")) {
                    if (cmd_list[1].isEmpty()) {
                        System.out.println("Описание задачи отсутствует");
                    }
                    else {
                        todo = new Task(cmd_list[1]);
                        any_task = true;
                    }
                }
                else if(cmd_list[0].equals("quit")) {
                    working = false;
                }
                else if(cmd_list[0].equals("print") || cmd_list[0].equals("toggle")) {
                    System.out.println("В списке задач не хранится ни одной задачи!");
                }
                else {
                    System.out.println("Пустая или неизвестная команда!");
                }
            }
        }

    }

}