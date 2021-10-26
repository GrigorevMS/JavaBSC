package ToDoList;

import java.util.Scanner;

public class Main {

    // Разбиение пользовательского ввода на команду и ее аргумент.
    // Возвращает: [0] - команда, [1] - аргумент
    private static String[] SepInput(String input) {
        String[] out = new String[2];
        int pos = 0;
        String buf = "";
        // Пропускаем пробелы в начале строки
        while(pos < input.length() && input.charAt(pos) == ' ') {
            pos += 1;
        }
        // Накапливаем команду (первое слово до пробела)
        while(pos < input.length() && input.charAt(pos) != ' ') {
            buf += input.charAt(pos);
            pos += 1;
        }
        // Пропускаем пробелы
        out[0] = buf;
        while(pos < input.length() && input.charAt(pos) == ' ') {
            pos += 1;
        }
        // Накапливаем остаток строки
        buf = "";
        while(pos < input.length()) {
            buf += input.charAt(pos);
            pos += 1;
        }
        out[1] = buf;
        return (out);
    }

    public static void main( String[] args ) {
        boolean Working = true;
        Scanner in = new Scanner(System.in);
        String input_cmd = "";
        String[] cmd_list = new String[2];

        Task ToDo = new Task();

        while(Working) {
            System.out.print("ToDo List: ");
            input_cmd = in.nextLine();
            cmd_list[0] = "";
            cmd_list[1] = "";
            cmd_list = SepInput(input_cmd);
            switch (cmd_list[0]) {
                case "add":
                    if(cmd_list[1].length() == 0) {
                        System.out.println("Описание задачи отсутствует");
                    }
                    else if(!ToDo.SetDescription(cmd_list[1])) {
                        System.out.println("Описание задачи содержит недопустимые символы");
                    }
                    if(!ToDo.SetStatus(false)) {
                        System.out.println("Не удалось установить статус задачи");
                    }
                    break;
                case "print":
                    if(cmd_list[1] != "") {
                        if(cmd_list[1].indexOf("all") == 0) {
                            System.out.println(ToDo.Out());
                        }
                        else {
                            System.out.println("Недопустимый аргумент для команды print");
                        }
                    }
                    else {
                        System.out.println(ToDo.Out());
                    }
                    break;
                case "toggle":
                    try {
                        int id = Integer.parseInt(cmd_list[1]);
                        if(id == 1) {
                            ToDo.ChangeStatus();
                        }
                        else {
                            System.out.println("Задача с указанным идентификатором отсутствует в списке");
                        }
                    }
                    catch (NumberFormatException nfe) {
                        System.out.println("Отсутствует или неверно введен идентификатор задачи");
                    }
                    break;
                case "quit":
                    Working = false;
                    break;
                default:
                    System.out.println("Пустая или неизвестная команда!");
                    break;
            }
        }
    }

}