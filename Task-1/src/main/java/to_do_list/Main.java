package to_do_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    // Разбиение пользовательского ввода на команду и ее аргумент.
    // Возвращает: [0] - команда, [1] - аргумент
    private static String[] SepInput(String input) {
        String[] out = new String[] {"", "", ""};
        String[] splitArr;
        input = input.trim();
        splitArr = input.split(" ", 2);
        for (int i = 0; i < splitArr.length; i++) {
            out[i] = splitArr[i].trim();
        }
        if (out[0].equals("edit")) {
            if (!out[1].isEmpty()) {
                splitArr = out[1].split(" ", 2);
                for (int i = 0; i < splitArr.length; i++) {
                    out[i + 1] = splitArr[i].trim();
                }
            }
        }
        return out;
    }

    private static void Print(String output) {
        System.out.println(output);
    }

    public static void main( String[] args ) throws IOException {
        boolean working = true;

        BufferedReader inBuffer = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> taskList = new ArrayList<>();
        int idCounter = 0;

        Print("ToDo List");

        while (working) {
            String inputCmd;
            String[] cmdList;

            System.out.print("Input Your command: ");
            inputCmd = inBuffer.readLine();
            cmdList = SepInput(inputCmd);

            switch (cmdList[0]) {
                case "add":
                    if (cmdList[1].isEmpty()) {
                        Print("Описание задачи отсутствует");
                    }
                    else {
                        taskList.add(new Task(cmdList[1], ++idCounter));
                    }
                    break;
                case "print":
                    if (!taskList.isEmpty()) {
                        if (cmdList[1].isEmpty()) {
                            long count = taskList.stream()
                                    .filter(t -> !t.getStatus())
                                    .count();
                            if (count != 0) {
                                taskList.stream()
                                        .filter(t -> !t.getStatus())
                                        .forEach(t -> Print(t.out()));
                            } else {
                                Print("Невыполненных задач нет");
                            }
                        } else {
                            if (cmdList[1].equals("all")) {
                                taskList.forEach(t -> Print(t.out()));
                            } else {
                                Print("Недопустимый аргумент для команды print");
                            }
                        }
                    } else {
                        Print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "toggle":
                    if (!taskList.isEmpty()) {
                        try {
                            int id = Integer.parseInt(cmdList[1]);
                            if (id > 0 && id <= idCounter) {
                                Optional<Task> taskTemp = taskList.stream()
                                                                    .filter(t -> t.getId() == id)
                                                                    .findFirst();
                                if (taskTemp.isPresent()){
                                    Task task = taskTemp.get();
                                    task.changeStatus();
                                    taskList.set(taskList.indexOf(task), new Task(task));
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
                case "delete":
                    if (!taskList.isEmpty()) {
                        try {
                            int id = Integer.parseInt(cmdList[1]);
                            if (id > 0 && id <= idCounter) {
                                Optional<Task> taskTemp = taskList.stream()
                                                                    .filter(t -> t.getId() == id)
                                                                    .findFirst();
                                if (taskTemp.isPresent()) {
                                    taskList.remove(taskTemp.get());
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
                case "edit":
                    if (!taskList.isEmpty()) {
                        try {
                            int id = Integer.parseInt(cmdList[1]);
                            if (id > 0 && id <= idCounter) {
                                Optional<Task> taskTemp = taskList.stream()
                                                                    .filter(t -> t.getId() == id)
                                                                    .findFirst();
                                if (taskTemp.isPresent()) {
                                    Task task = taskTemp.get();
                                    if (!cmdList[2].isEmpty()) {
                                        task.setDescription(cmdList[2]);
                                        taskList.set(taskList.indexOf(task), new Task(task));
                                    } else {
                                        Print("Описание задачи отсутствует");
                                    }
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
                    if (!taskList.isEmpty()) {
                        if (cmdList[1].isEmpty()) {
                            Print("Пустой поисковый запрос недопустим");
                        } else {
                            long count = taskList.stream()
                                    .filter(t -> !t.getStatus())
                                    .count();
                            if (count != 0) {
                                String charLine = cmdList[1].trim();
                                taskList.stream()
                                        .filter(t -> !t.getStatus())
                                        .filter(t -> t.getDescription().contains(charLine))
                                        .forEach(t -> Print(t.out()));
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
        try {
            inBuffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}