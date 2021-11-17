package to_do_list;

public class Task {

    private int Id;    // the ID of task
    private boolean Status;    // true - done, false - not done
    private String Description;    // What should be done

    Task(String description_input, int id_input) {
        Id = id_input;
        Status = false;
        Description = description_input;
    }

    Task(Task temp) {
        Id = temp.Id;
        Description = temp.Description;
        Status = temp.Status;
    }

    // Подготовка информации о задаче для вывода на консоль
    String out() {
        String out = "%d. [%c] %s";
        if (Status) {
            out = String.format(out, Id, 'X', Description);
        } else {
            out = String.format(out, Id, ' ', Description);
        }
        return out;
    }

    // Получить id
    int getId() {
        return Id;
    }

    // Задать описание задачи
    void setDescription(String input) {
        Description = input;
    }

    // Получить описание задачи
    String getDescription() {
        return Description;
    }

    // Получить статус задачи
    boolean getStatus(){
        return Status;
    }

    // Изменение статуса задачи на противоположный
    void changeStatus() {
        Status = !Status;
    }

}
