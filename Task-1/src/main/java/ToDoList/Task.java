package ToDoList;

public class Task {

    private int Id;    // the ID of task
    private boolean Status;    // true - done, false - not done
    private String Description;    // What should be done

    // Конструктор преобразования типов
    Task(String description_input) {
        Id = 1;
        Status = false;
        Description = description_input;
    }

    // Подготовка информации о задаче для вывода на консоль
    String Out() {
        String Out = "";
        Out = Out + Id + ". ";
        if(this.Status){
            Out += "[X] ";
        }
        else{
            Out += "[ ] ";
        }
        Out += this.Description;
        return Out;
    }

    // Задать описание задачи
    void SetDescription(String input) {
        Description = input;
    }

    // Задать статус выполнения задачи
    void SetStatus(boolean input) {
        Status = input;
    }

    // Получить статус задачи
    boolean GetStatus(){
        return Status;
    }

    // Изменение статуса задачи на противоположный
    void ChangeStatus() {
        Status = !Status;
    }

}
