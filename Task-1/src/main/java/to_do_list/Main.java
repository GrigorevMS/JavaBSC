package to_do_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    // Разбиение пользовательского ввода на команду и ее аргумент.
    // Возвращает: [0] - команда, [1] - аргумент
    private static String[] sepInput(String input) {
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

    private static void print(String output) {
        System.out.println(output);
    }

    public static void main( String[] args ) throws IOException {
        boolean working = true;

        BufferedReader inBuffer = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> taskList = new ArrayList<>();
        int idCounter = 0;

        print("ToDo List");

        while (working) {
            String inputCmd;
            String[] cmdList;

            System.out.print("Input Your command: ");
            inputCmd = inBuffer.readLine();
            cmdList = sepInput(inputCmd);

            switch (cmdList[0]) {
                case "add":
                    if (cmdList[1].isEmpty()) {
                        print("Описание задачи отсутствует");
                    }
                    else {
                        taskList.add(new Task(cmdList[1], ++idCounter));
                    }
                    break;
                case "print":
                    if (!taskList.isEmpty()) {
                        if (cmdList[1].isEmpty()) {
                            List<Task> tempTaskList = taskList.stream()
                                    .filter(t -> !t.getStatus())
                                    .collect(Collectors.toList());
                            if (tempTaskList.size() != 0) {
                                tempTaskList.forEach(t -> print(t.out()));
                            } else {
                                print("Невыполненных задач нет");
                            }
                        } else {
                            if (cmdList[1].equals("all")) {
                                taskList.forEach(t -> print(t.out()));
                            } else {
                                print("Недопустимый аргумент для команды print");
                            }
                        }
                    } else {
                        print("В списке задач не хранится ни одной задачи!");
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
                                } else {
                                    print("Задача с указанным идентификатором отсутствует в списке");
                                }
                            } else {
                                print("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch (NumberFormatException nfe) {
                            print("Отсутствует или неверно введен идентификатор задачи");
                        }
                    } else {
                        print("В списке задач не хранится ни одной задачи!");
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
                                    print("Задача с указанным идентификатором отсутствует в списке");
                                }
                            } else {
                                print("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch (NumberFormatException nfe) {
                            print("Отсутствует или неверно введен идентификатор задачи");
                        }
                    } else {
                        print("В списке задач не хранится ни одной задачи!");
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
                                    } else {
                                        print("Описание задачи отсутствует");
                                    }
                                } else {
                                    print("Задача с указанным идентификатором отсутствует в списке");
                                }
                            } else {
                                print("Задача с указанным идентификатором отсутствует в списке");
                            }
                        } catch (NumberFormatException nfe) {
                            print("Отсутствует или неверно введен идентификатор задачи");
                        }
                    } else {
                        print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "search":
                    if (!taskList.isEmpty()) {
                        if (cmdList[1].isEmpty()) {
                            print("Пустой поисковый запрос недопустим");
                        } else {
                            List<Task> tempTaskList = taskList.stream()
                                    .filter(t -> !t.getStatus())
                                    .collect(Collectors.toList());
                            if (tempTaskList.size() != 0) {
                                String charLine = cmdList[1].trim();
                                tempTaskList = tempTaskList.stream()
                                        .filter(t -> t.getDescription().contains(charLine))
                                        .collect(Collectors.toList());
                                if (tempTaskList.size() != 0) {
                                    tempTaskList.forEach(t -> print(t.out()));
                                } else {
                                    print("Задачи не найдены");
                                }
                            } else {
                                print("Невыполненных задач нет");
                            }
                        }
                    } else {
                        print("В списке задач не хранится ни одной задачи!");
                    }
                    break;
                case "quit":
                    working = false;
                    break;
                default:
                    print("Пустая или неизвестная команда!");
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