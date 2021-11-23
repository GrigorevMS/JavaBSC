package to_do_list;

public class Task {

    private int id;    // the ID of task
    private boolean status;    // true - done, false - not done
    private String description;    // What should be done

    Task(String description_input, int id_input) {
        id = id_input;
        status = false;
        description = description_input;
    }

    Task(Task temp) {
        id = temp.id;
        description = temp.description;
        status = temp.status;
    }

    // Подготовка информации о задаче для вывода на консоль
    String out() {
        String out = "%d. [%c] %s";
        if (status) {
            out = String.format(out, id, 'X', description);
        } else {
            out = String.format(out, id, ' ', description);
        }
        return out;
    }

    // Получить id
    int getId() {
        return id;
    }

    // Задать описание задачи
    void setDescription(String input) {
        description = input;
    }

    // Получить описание задачи
    String getDescription() {
        return description;
    }

    // Получить статус задачи
    boolean getStatus(){
        return status;
    }

    // Изменение статуса задачи на противоположный
    void changeStatus() {
        status = !status;
    }

}
